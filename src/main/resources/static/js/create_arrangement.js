var locationSelector = document.getElementById("location");
var ticketTypeSelector = document.getElementById("ticketType");
var seatName = document.getElementById("seatName");
var seatDescription = document.getElementById("seatDescription");
var seatFare = document.getElementById("seatFare");
var locationIds = [];
var locationsDetails = [];
var seats = [];
$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");
    LoadLocation();
    LoadTicketType();

});


$("#addLocation").click(function () {

    var id = parseFloat(locationSelector.options[locationSelector.selectedIndex].value);
    if (id != 0) {
        locationIds.push(id);
        locationSelector.selectedIndex = "0";
        console.log(locationsDetails);
        var item = findLocationObject(locationsDetails, id);

        console.log(item);

        var deatils = item.area + "," + item.street + "," + item.city + "," + item.country;
        $("#locationList").append(' <tr>\n' +
            '                          <td>\n' +
            '                            ' + item.name + '\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            ' + deatils + '\n' +
            '                          </td>\n' +

            '                        </tr>');
        console.log(locationIds);
        notification("Add Location Successfully", 3);
    }
});
$("#removeLocation").click(function () {
    if (locationIds.length != 0) {
        locationIds.pop();
        locationSelector.selectedIndex = "0";
        console.log(locationIds);
        $("#locationList tr").remove();

    }
    notification("Clear Location Successfully", 4);
});

$("#addSeat").click(function () {

    var seat = {
        name: seatName.value,
        description: seatDescription.value,
        fare: parseFloat(seatFare.value)
    }
    seats.push(seat);
    seatName.value = "";
    seatDescription.value = "";
    seatFare.value = "";

    $("#seatList").append(' <tr>\n' +
        '                          <td>\n' +
        '                            ' + seat.name + '\n' +
        '                          </td>\n' +
        '                          <td>\n' +
        '                            ' + seat.description + '\n' +
        '                          </td>\n' +
        '                          <td>\n' +
        '                            ' + seat.fare + '\n' +
        '                          </td>\n' +
        '                          <td>\n' +
        '                            ' + '<button onclick="seatDelete(this);" type="button" class="btn btn-primary pull-right">Remove</button>' + '\n' +
        '                          </td>\n' +
        '                        </tr>');

    notification("Seat Add Successfully", 3);
});

$("#create-arrangement-form").submit(function (event) {
    event.preventDefault();

    var $form = $(this);
    var formData = {
        name: $form.find('input[name="name"]').val(),
        description: $form.find('input[name="description"]').val(),
        totalSeats: seats.length,
        reportingDateTime: $form.find('input[name="reporting"]').val(),
        startDateTime: $form.find('input[name="start"]').val(),
        endDateTime: $form.find('input[name="end"]').val(),
        type: parseFloat(ticketTypeSelector.options[ticketTypeSelector.selectedIndex].value),
        locations: locationIds,
        seats: seats
    };
    console.log(JSON.stringify(formData));
    SaveArrangement(formData);

});

function SaveArrangement(formData) {
    $.ajax({
        url: "/api/arrangement",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data, textStatus, jqXHR) {
            notification("Arrangement Add SuccessFully", 3);
            //sessionStorage.setItem('token', data.id_token);
            window.location.replace("../create_arrangement");

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
            ticketTypeSelector.add(option, ticketTypeSelector[0]);
            data.body.forEach(myFunction);

            function myFunction(item, index) {
                var option = document.createElement("option");
                option.text = item.name;
                option.value = item.id;
                ticketTypeSelector.add(option, ticketTypeSelector[index + 1]);
            }
        }


    });
}

function LoadLocation() {
    $.ajax({
        url: "../api/location",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            var option = document.createElement("option");
            option.text = "SELECT_LOCATION";
            option.value = 0;
            locationSelector.add(option, locationSelector[0]);
            data.body.forEach(myFunction);
            locationsDetails = data.body;

            function myFunction(item, index) {
                var option = document.createElement("option");
                option.text = item.name;
                option.value = item.id;
                locationSelector.add(option, locationSelector[index + 1]);
            }
        }


    });
}

function notification(message, typeIndex) {
    type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary'];
    $.notify({
        icon: "add_alert",
        message: message

    }, {
        type: type[typeIndex],
        timer: 2000,
        placement: {
            from: 'top',
            align: 'center'
        }
    });


}

function seatDelete(row) {
    $(row).parents("tr").remove();
    RemoveSeatObject($(row).parents("tr").find("td:first").html());
    notification("Remove Seat Successfully", 4);
    //console.log(seats);
}

function findLocationObject(array, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i].id === value) {
            return array[i];
        }
    }
    return null;
}

function RemoveSeatObject(value) {

    for (var i = 0; i < seats.length; i++) {

        if (seats[i].name === value.trim()) {

            // console.log("remove"+value);
            seats.splice(i, 1);
            if (seats == null)
                seats = [];

            // console.log("remove"+seats);


        }
    }
    return null;
}