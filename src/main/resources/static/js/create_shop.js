var user_drop_down = document.getElementById("selectUser");
var imageUrl="";
$(document).ready(function () {
   $("#nav-placeholder").load("navbar.html");
   $("#top_bar").load("topbar.html");
   $.ajax({
      url: "../api/users",
      type: "GET",
      beforeSend: function (xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function (data) {
         var option = document.createElement("option");
         option.text = "SELECT_USER";
         option.value = 0;
         user_drop_down.add(option, user_drop_down[0]);
         data.forEach(myFunction);

         function myFunction(item, index) {
            var option = document.createElement("option");
            option.text = item.username;
            option.value = item.id;

            user_drop_down.add(option, user_drop_down[index + 1]);
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
         //console.log(response)
         imageUrl=response.fileDownloadUri;
         document.getElementById('uploadresult').src = response.fileDownloadUri;
      },
      error: function (error) {
         console.log(error);
      }
   });
   event.preventDefault();
});


$("#create-shop-form").submit(function (event) {
   event.preventDefault();

   if(imageUrl==""){

      notification("Please Add Shop Image",2);
      return;
   }

   var $form = $(this);

   var formData = {
      shopName: $form.find('input[name="ShopName"]').val(),
      ownerName: $form.find('input[name="OwnerName"]').val(),
      latitude: $form.find('input[name="latitude"]').val(),
      longitude: $form.find('input[name="longitude"]').val(),
      discount: $form.find('input[name="Discount"]').val(),
      deliveryFree: $form.find('input[name="DeliveryFree"]').prop("checked"),
      flatNo: $form.find('input[name="FlatNo"]').val(),
      houseNo: $form.find('input[name="HouseNo"]').val(),
      roadNo: $form.find('input[name="RoadNo"]').val(),
      areaName: $form.find('input[name="AreaName"]').val(),
      thanaName: $form.find('input[name="ThanaName"]').val(),
      districtName: $form.find('input[name="District"]').val(),
      user: user_drop_down.options[user_drop_down.selectedIndex].value,
      imageUrl:imageUrl

   };
   console.log(formData);
   SaveShop(formData);
});


function SaveShop(formData) {
   $.ajax({
      url: "/api/shop/createShop",
      type: "POST",
      data: JSON.stringify(formData),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      beforeSend: function(xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function (data, textStatus, jqXHR) {
         notification("Shop Save SuccessFully",3);
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
