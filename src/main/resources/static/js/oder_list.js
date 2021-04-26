var user_drop_down = document.getElementById("selectUser");
$(document).ready(function () {
   $("#nav-placeholder").load("navbar.html");
   $("#top_bar").load("topbar.html");
   $.ajax({
      url: "../oder/list",
      type: "GET",
      beforeSend: function(xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function(data) {

         console.log(""+data);

         data.forEach(myFunction);
         function myFunction(item, index) {
            //var option = document.createElement("option");
          //  option.text = item.name;
           // option.value =item.id;
            console.log(item);
            $("#userList").append(' <tr>\n' +
               '                          <td>\n' +
               '                            '+item.id+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.foodUser+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.user+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.deliveryCharge+'\n' +
               '                          </td>\n' +
               '                          <td >\n' +
               '                            '+item.itemPrice+'\n' +
               '                          </td>\n' +
               '                           <td >\n' +
               '                              '+item.totalPrice+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.status+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.address+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.active+'\n' +
               '                           </td>\n' +
               '                           <td>\n' +
               '                              <button type="button" onclick="productDelete('+item.id+');"   class="btn btn-primary pull-center">Food Item</button>\n' +
               '                                <div class="clearfix"></div>\n' +
               '                             <button type="button" class="btn btn-primary pull-center" data-toggle="modal" data-target="#exampleModal" onclick="addResponsible('+item.id+');" data-whatever="@mdo">Add Responsible</button>\n' +
               '                                <div class="clearfix"></div>\n' +
               '                           </td>\n'+
               '                        </tr>');


            //role_drop_down.add(option, role_drop_down[index+1]);
         }
      }
   });





});

function productDelete(ctl) {
  // window.id=ctl;

  console.log("s"+ctl);
   sessionStorage.setItem('currentOderId', ctl);
   window.location.replace("../oder_item_list");
}
function addResponsible(ctl) {
   window.id=ctl;

   console.log("s"+ctl);
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
  // sessionStorage.setItem('currentOderId', ctl);
   //window.location.replace("../oder_item_list");
}
$('#exampleModal').on('show.bs.modal', function (event) {
   var button = $(event.relatedTarget) // Button that triggered the modal
   var recipient = button.data('whatever') // Extract info from data-* attributes
   // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
   // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
   var modal = $(this)
   console.log("f"+2)
   modal.find('.modal-title').text('New message to ' + recipient)
   modal.find('.modal-body input').val(recipient)
});
function SubmitResponsible() {

   var formData = {
      oderId:window.id,
      userId: user_drop_down.options[user_drop_down.selectedIndex].value
   };
   console.log(formData);
   $.ajax({
      url: "/oder/responsible",
      type: "POST",
      data: JSON.stringify(formData),
      contentType: "application/json",
    //  dataType: "json",
      beforeSend: function(xhr) {
         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
      },
      success: function (data, textStatus, jqXHR) {
       //  notification("Shop Save SuccessFully",3);
         console.log("d "+data)
         location.reload();


      },
      error: function (jqXHR, textStatus, errorThrown) {
       //  console.log(jqXHR.status);
         if (jqXHR.status === 401) {
            $('#message')
               .modal("show")
               .find(".modal-body")
               .empty()
               .html("<p>" + jqXHR.responseJSON.message + "</p>");
         } else {
          //  throw new Error("an unexpected error occured: " + errorThrown);
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


