$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");
    $("#top_bar").load("topbar.html");
    // $.ajax({
    //     url: "../api/shop/list",
    //     type: "GET",
    //     beforeSend: function (xhr) {
    //         xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
    //     },
    //     success: function (data) {
    //         var option = document.createElement("option");
    //         option.text = "SELECT_USER";
    //         option.value = 0;
    //         shop_drop_down.add(option, shop_drop_down[0]);
    //         data.forEach(myFunction);
    //
    //         function myFunction(item, index) {
    //             var option = document.createElement("option");
    //             option.text = item.shopName;
    //             option.value = item.id;
    //
    //             shop_drop_down.add(option, shop_drop_down[index + 1]);
    //         }
    //     }
    // });
});