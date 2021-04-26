var role_drop_down = document.getElementById("mySelect");
$(document).ready(function () {
   $("#nav-placeholder").load("navbar.html");
   $("#top_bar").load("topbar.html");
   $.ajax({
      url: "../api/admin/getallRole",
      type: "GET",
      beforeSend: function(xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function(data) {

         var option = document.createElement("option");
         option.text = "SELECT_ROLE";
         option.value =0;
         role_drop_down.add(option, role_drop_down[0]);
         data.forEach(myFunction);
         function myFunction(item, index) {
            var option = document.createElement("option");
            option.text = item.name;
            option.value =item.id;

            role_drop_down.add(option, role_drop_down[index+1]);
         }
      }
   });
});

$("#create-user-form").submit(function (event) {
   event.preventDefault();

   var $form = $(this);
   var formData = {
      fullName: $form.find('input[name="FullName"]').val(),
      username: $form.find('input[name="UserName"]').val(),
      password: $form.find('input[name="Password"]').val(),
      email: $form.find('input[name="Email"]').val(),
      mobile: $form.find('input[name="Phone"]').val(),
      role: role_drop_down.options[role_drop_down.selectedIndex].value
   };

   doLogin(formData);
});

function doLogin(loginData) {
   $.ajax({
      url: "/api/createuser",
      type: "POST",
      data: JSON.stringify(loginData),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      beforeSend: function(xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function (data, textStatus, jqXHR) {
         notification("User Add SuccessFully",3);
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
function  notification(message,typeIndex) {
   type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary'];
   $.notify({
      icon: "add_alert",
      message: message

   },{
      type: type[typeIndex],
      timer: 2000,
      placement: {
         from: 'top',
         align: 'center'
      }
   });
}
