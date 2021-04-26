$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");

});

$("#create-location-form").submit(function (event) {
    event.preventDefault();

    var $form = $(this);

    var formData = {
        name: $form.find('input[name="locationName"]').val(),
        area: $form.find('input[name="locationArea"]').val(),
        street: $form.find('input[name="locationStreet"]').val(),
        city: $form.find('input[name="locationCity"]').val(),
        country: $form.find('input[name="locationCountry"]').val()

    };
    if (formData.name==""||formData.area==""||formData.city==""||formData.country=="") {

        notification("Please Fill * ALl Field", 2);
        return;
    }
    console.log(formData);
    SaveShop(formData);
});

function SaveShop(formData) {
    $.ajax({
        url: "/api/location",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data, textStatus, jqXHR) {
            notification("Location Save SuccessFully", 3);
            //sessionStorage.setItem('token', data.id_token);
            //window.location.replace("../"+data.url);

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
