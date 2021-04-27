var roleSelector = document.getElementById("role");
$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");
    LoadRole();

});

$("#create-location-form").submit(function (event) {
    event.preventDefault();

    var $form = $(this);

    var formData = {
        userName: $form.find('input[name="userName"]').val(),
        password: $form.find('input[name="password"]').val(),
        email: $form.find('input[name="email"]').val(),
        mobile: $form.find('input[name="mobile"]').val(),
        role: parseFloat(roleSelector.options[roleSelector.selectedIndex].value)

    };
    if (formData.userName==""||formData.password==""||formData.email==""||formData.mobile==""||formData.role==0) {

        notification("Please Fill * ALl Field", 2);
        return;
    }
    console.log(formData);
    SaveUser(formData);
});

function SaveUser(formData) {
    $.ajax({
        url: "/api/user/signUp",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data, textStatus, jqXHR) {
            notification("User Save SuccessFully", 3);
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
function LoadRole() {
    $.ajax({
        url: "../api/authority",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            var option = document.createElement("option");
            option.text = "SELECT_LOCATION";
            option.value = 0;
            roleSelector.add(option, roleSelector[0]);
            data.body.forEach(myFunction);
            locationsDetails = data.body;

            function myFunction(item, index) {
                var option = document.createElement("option");
                option.text = item.name;
                option.value = item.id;
                roleSelector.add(option, roleSelector[index + 1]);
            }
        }


    });
}