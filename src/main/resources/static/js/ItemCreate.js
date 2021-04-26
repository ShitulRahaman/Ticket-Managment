var shop_drop_down = document.getElementById("selectShop");
var imageUrl="";

$(document).ready(function () {
   $("#nav-placeholder").load("navbar.html");
   $("#top_bar").load("topbar.html");
      $.ajax({
         url: "../api/shop/list",
         type: "GET",
         beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
         },
         success: function (data) {
            var option = document.createElement("option");
            option.text = "SELECT_USER";
            option.value = 0;
            shop_drop_down.add(option, shop_drop_down[0]);
            data.forEach(myFunction);

            function myFunction(item, index) {
               var option = document.createElement("option");
               option.text = item.shopName;
               option.value = item.id;

               shop_drop_down.add(option, shop_drop_down[index + 1]);
            }
         }
      });
});
$("#uploadfile").submit(function (event) {
   $.ajax({
      type: 'POST',
      enctype: 'multipart/form-data',
      url: '/api/uploadFile',
      data: new FormData(this),
      contentType: false,
      cache: false,
      processData: false,
      beforeSend: function (xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function (response) {
         console.log(response);
         document.getElementById('uploadresult').src = response.fileDownloadUri;
         imageUrl=response.fileDownloadUri;
      },
      error: function (error) {
         console.log(error);
      }
   });
   event.preventDefault();
});

$("#create-item-form").submit(function (event) {
   event.preventDefault();

   if(imageUrl==""){

      notification("Please Add Item Image",2);
      return;
   }

   var $form = $(this);

   var formData = {
      name: $form.find('input[name="FoodName"]').val(),
      price: $form.find('input[name="FoodPrice"]').val(),
      amount: $form.find('input[name="perPerson"]').val(),
      description: $form.find('textarea[name="description"]').val(),
      shop: shop_drop_down.options[shop_drop_down.selectedIndex].value,
      imageUrl:imageUrl

   };
   console.log(formData);
   SaveShop(formData);
});

function SaveShop(formData) {
   $.ajax({
      url: "/api/shop/createItem",
      type: "POST",
      data: JSON.stringify(formData),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      beforeSend: function(xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function (data, textStatus, jqXHR) {
         notification("Shop Item Save SuccessFully",3);
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
