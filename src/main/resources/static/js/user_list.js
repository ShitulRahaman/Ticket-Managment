$(document).ready(function () {
   $("#nav-placeholder").load("navbar.html");
   $("#top_bar").load("topbar.html");
   $.ajax({
      url: "../api/users",
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
               '                            '+item.fullName+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.username+'\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            '+item.email+'\n' +
               '                          </td>\n' +
               '                          <td >\n' +
               '                            '+item.mobile+'\n' +
               '                          </td>\n' +
               '                           <td >\n' +
               '                              '+item.role+'\n' +
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
