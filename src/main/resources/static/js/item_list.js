$(document).ready(function () {
   $("#nav-placeholder").load("navbar.html");
   $("#top_bar").load("topbar.html");
      console.log("w "+sessionStorage.getItem('currentShopId'));
   $.ajax({
      url: "../api/shop/itemList/"+sessionStorage.getItem('currentShopId')+"",
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
               '                            '+item.name+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.price+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.amount+'\n' +
               '                          </td>\n' +
               '                          <td >\n' +
               '                            '+item.description+'\n' +
               '                          </td>\n' +
               '                           <td >\n' +
               '                              '+item.imageUrl+'\n' +
               '                           </td>\n' +
               '                           <td >\n' +
               '                              '+item.active+'\n' +
               '                           </td>\n' +

               '                        </tr>');


            //role_drop_down.add(option, role_drop_down[index+1]);
         }
      }
   });
});
