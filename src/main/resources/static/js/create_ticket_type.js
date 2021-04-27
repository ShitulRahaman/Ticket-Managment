var program_drop_down = document.getElementById("programSelect");

$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");


});


$("#create-ticket-type-form").submit(function (event) {
    event.preventDefault();


    var $form = $(this);

    var formData = {

        name: $form.find('input[name="typename"]').val(),
        program: parseInt(program_drop_down.options[program_drop_down.selectedIndex].value),
        description: $form.find('input[name="description"]').val()


    };

    if (formData.name == "") {
        notification("Please Add Type Name", 2);
        return;
    }
    console.log(JSON.stringify(formData));
    SaveTicketType(formData);
});


function SaveTicketType(formData) {
    $.ajax({
        url: "/api/ticketType",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data, textStatus, jqXHR) {
            notification("Ticket Type Save SuccessFully", 3);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 401) {
                $('#message')
                    .modal("show")
                    .find(".modal-body")
                    .empty()
                    .html("<p>" + jqXHR.responseJSON.message + "</p>");
            } else {
                throw new Error("an unexpected error occured: " + errorThrown);
            }
        }
    });
}

function notification(message, typeIndex) {
    type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary'];
    $.notify({
        icon: "add_alert",
        message: message

    }, {
        type: type[typeIndex],
        timer: 2000,
        placement: {
            from: 'top',
            align: 'center'
        }
    });
}
