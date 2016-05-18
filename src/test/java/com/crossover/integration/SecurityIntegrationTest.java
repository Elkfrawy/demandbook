package com.crossover.integration;

import com.crossover.DemandbookApplication;
import com.crossover.domain.CurrentUser;
import com.crossover.domain.Role;
import com.crossover.domain.User;
import com.crossover.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


/**
 * Created by Ayman Elkfrawy on 5/17/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemandbookApplication.class)
@WebIntegrationTest
public class SecurityIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    public void testSecurityRedirectToLogin() throws Exception {
        String loginPage = "http://localhost/login";
        String[] testLinks = {"/book", "/book/141451", "/demand"};

        for (String link : testLinks) {
            mockMvc.perform(MockMvcRequestBuilders.get(link))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl(loginPage));
        }
    }


    @Test
    public void testSecurity_LoginAndRegistration_ShouldNotRedirect() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/user/create").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testSecurity_RegistrationWithoutCSRF_ShouldDenied() throws Exception {
        mockMvc.perform(post("/user/create"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testSecurity_ShouldAllowHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterUser_Correctly() throws Exception {
        String username = "ayman";
        String password ="123456";
        String email = "ayman@crossover.com";
        Role role = Role.USER;

        doRegistration(username,password,password,email,role)
                .andExpect(status().is3xxRedirection()) // Redirect to home
                .andExpect(model().hasNoErrors());
        // TODO Not Authenticated Yet

        // Make sure database has the new user
        User user = userService.getUserByUsername(username);
        assertNotNull(user);
        assertEquals(user.getUsername(), username);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getRole(), role);
        assertEquals(user.getDemands().size(), 0); // No demands yet
        assertTrue(new BCryptPasswordEncoder().matches(password, user.getPassword())); // Check hashed password
        userService.deleteUser(user);
    }

    @Test
    public void testRegistration_DuplicateUsername() throws Exception {
        String username = "ayman";
        String password ="123456";
        String email = "ayman@crossover.com";
        Role role = Role.USER;

        doRegistration(username,password,password,email,role).andExpect(status().is3xxRedirection()); // Redirect to home
        // Second registration
        doRegistration(username,password,password,email,role)
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1));

        userService.deleteUser(userService.getUserByUsername(username));
    }

    @Test
    public void testRegisterUser_WithEmptyParameters() throws Exception {
        doRegistration("","","","",null)
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(5));
    }

    @Test
    public void testRegisterUser_WithExistingUsername() throws Exception {
        doRegistration("user","1234","1234","user@user.com",Role.USER)
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1));
    }

    private ResultActions doRegistration(String username, String password, String repassword,
                                         String email, Role role) throws  Exception{
        return mockMvc.perform(post("/user/create")
                .param("username", username)
                .param("password", password)
                .param("passwordRepeated", repassword)
                .param("role", role==null?"":role.name())
                .param("email", email)
                .with(csrf()));
    }
}
