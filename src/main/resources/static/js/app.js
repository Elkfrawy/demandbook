/**
 * Created by Ayman on 5/16/2016.
 */
//"use strict";
function addDemand(bookid) {
    $.ajax({
        url: '/demand',
        type: 'POST',
        data: 'bookid=' + bookid
    })
        .done(function (state) {
            if (state == 'success') {
                bootstrap_alert.warning('Demand added' , 'success', 4000);
            } else {
                bootstrap_alert.warning('Reply: ' + state , 'danger', 4000);
            }
        })
        .fail(function () {
            bootstrap_alert.warning('Problem occured!' , 'danger', 4000);
        });

}

bootstrap_alert = function () {
}
bootstrap_alert.warning = function (message, alert, timeout) {
    $('<div id="floating_alert" class="alert alert-' + alert + ' fade in"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + message + '&nbsp;&nbsp;</div>').appendTo('body');


    setTimeout(function () {
        $(".alert").alert('close');
    }, timeout);

}