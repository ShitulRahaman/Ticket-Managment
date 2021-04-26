$(document).ready(function () {
   $("#nav-placeholder").load("navbar.html");
   $("#top_bar").load("topbar.html");
   $.ajax({
      url: "../api/shop/list",
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
               '                            '+item.shopName+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.ownerName+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.discount+'\n' +
               '                          </td>\n' +
               '                          <td >\n' +
               '                            '+item.deliveryFree+'\n' +
               '                          </td>\n' +
               '                           <td >\n' +
               '                              '+item.latitude+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.longitude+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.address+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.user+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.active+'\n' +
               '                           </td>\n' +
               '                           <td>\n' +
               '                              <button type="button" onclick="productDelete('+item.id+');"   class="btn btn-primary pull-center">Food Item</button>\n' +
               '                                <div class="clearfix"></div>\n' +
               '                           </td>\n'+
               '                        </tr>');


            //role_drop_down.add(option, role_drop_down[index+1]);
         }
      }
   });





});

function productDelete(ctl) {
   window.id=ctl;

  console.log("s"+ctl);
   sessionStorage.setItem('currentShopId', ctl);
   window.location.replace("../item_list");
}



