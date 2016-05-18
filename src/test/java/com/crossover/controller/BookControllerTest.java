package com.crossover.controller;

import com.crossover.DemandbookApplication;
import com.crossover.domain.Book;
import com.crossover.domain.CurrentUser;
import com.crossover.domain.Role;
import com.crossover.domain.User;
import com.crossover.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ayman Elkfrawy on 5/17/2016.
 */

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = DemandbookApplication.class)
@WebAppConfiguration
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @Mock
    private Authentication authentication;


    private List<Book> books;
    private String query;

    private CurrentUser currentUser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();


        books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());
        query = "test";
        /*
//        AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
//        this.authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("user", "password"));
        when(authentication.getPrincipal()).thenReturn(currentUser);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("TestAuth: " + authentication);
        */
    }

    @Test
    public void testSearchBooksByAny_ShouldReturnArrayOfBooks() throws Exception {
        when(bookService.searchBooksByAny(query)).thenReturn(books);

        mockMvc.perform(get("/book").param("q",query))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", hasSize(2)));
        verify(bookService, times(1)).searchBooksByAny(query);
    }

    @Test
    public void testSearchBooksByAuthor_ShouldNotSearchAny() throws Exception{
        when(bookService.searchBooksByAuthor(query)).thenReturn(books);

        mockMvc.perform(get("/book").param("author", query))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", hasSize(2)));
        verify(bookService, times(1)).searchBooksByAuthor(query);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void testSearchBooksByPublisher_ShouldNotSearchAnyOrAuthor() throws Exception{
        when(bookService.searchBooksByPublisher(query)).thenReturn(books);

        mockMvc.perform(get("/book").param("publisher", query))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", hasSize(2)));
        verify(bookService, times(1)).searchBooksByPublisher(query);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void testSearchWithoutQuery_ShouldReturnAllBooks() throws Exception{
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", hasSize(2)));
        verify(bookService, times(1)).getAllBooks();
        verifyNoMoreInteractions(bookService);
    }

    private User createUser() {
        User user = new User();
        user.setDemands(new ArrayList<>());
        user.setRole(Role.USER);
        user.setUsername("ayman");
        user.setPassword("123");

        currentUser = new CurrentUser(user);

        return user;
    }
}
