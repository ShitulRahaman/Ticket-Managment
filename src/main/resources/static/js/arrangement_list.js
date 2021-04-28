var ticketType = document.getElementById("ticketType");
$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");
    loadArrangement();
    LoadTicketType();

});

function LoadTicketType() {
    $.ajax({
        url: "../api/ticketType",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            var option = document.createElement("option");
            option.text = "SELECT_LOCATION";
            option.value = 0;
            ticketType.add(option, ticketType[0]);
            data.body.forEach(myFunction);

            function myFunction(item, index) {
                var option = document.createElement("option");
                option.text = item.name;
                option.value = item.id;
                ticketType.add(option, ticketType[index + 1]);
            }
        }


    });
}

function loadArrangement() {
    $.ajax({
        url: "../api/arrangement",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {

            console.log("" + data);

            TicketShow(data)
        }
    });
}



$("#arrangement-find-form").submit(function (event) {
    event.preventDefault();
    var $form = $(this);
    var formData = {
        ticketType: parseFloat(ticketType.options[ticketType.selectedIndex].value),
        dateTime: $form.find('input[name="date"]').val()
    };

    console.log(formData);
    searchArrangement(formData);
});

function searchArrangement(formData) {

    $.ajax({
        url: "/api/arrangement/search",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {

            console.log(data);
            console.log(data.length);

            TicketShow(data);


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

function TicketShow(data) {
    $('#arrangmentList tr').remove();
    data.forEach(function (item, index) {
        var reporting = new Date(item.reportingDateTime);
        var start = new Date(item.startDateTime);
        var end = new Date(item.endDateTime);
        var location = item.locations[0].name + ',' + item.locations[0].area + '' + item.locations[0].street + "," + item.locations[0].city + "," + item.locations[0].country;
        var destination = item.locations[1];
        if (destination == null) {
            destination = location;
        } else {
            destination = item.locations[1].name + ',' + item.locations[1].area + '' + item.locations[1].street + "," + item.locations[1].city + "," + item.locations[1].country;

        }
        $("#arrangmentList").append(' <tr id="' + item.id + '">\n' +
            '                          <td >\n' +
            '                            ' + item.name + '\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            ' + reporting + '\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            ' + start + '\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            ' + end + '\n' +
            '                          </td>\n' +
            '                          <td >\n' +
            '                            ' + location + '\n' +
            '                          </td>\n' +
            '                           <td >\n' +
            '                              ' + destination + '\n' +
            '                           </td>\n' +

            '                        </tr>');
    });

}

