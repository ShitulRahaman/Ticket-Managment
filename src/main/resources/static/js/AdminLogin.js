$("#loginForm").submit(function (event) {
   event.preventDefault();

   var $form = $(this);
   var formData = {
      username: $form.find('input[name="username"]').val(),
      password: $form.find('input[name="password"]').val()
   };

   doLogin(formData);
});

function doLogin(loginData) {
   $.ajax({
      url: "/api/authenticate",
      type: "POST",
      data: JSON.stringify(loginData),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function (data, textStatus, jqXHR) {

         sessionStorage.setItem('token', data.id_token);
        // redirectpage(data);

         window.location.replace("../"+data.url);
        // console.log(sessionStorage.getItem('token'));
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
