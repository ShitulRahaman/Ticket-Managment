var ticketTypeSelector = document.getElementById("ticketType");
var totalFareInput = document.getElementById("totalFare");
var arrangements=[];
var seats=[];
var seatSelected=[];
var arrangementSelected;
$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");
    LoadTicketType();
});


$("#create-ticket-find-form").submit(function (event) {
    event.preventDefault();

    var $form = $(this);

    var formData = {
        ticketType: parseFloat(ticketTypeSelector.options[ticketTypeSelector.selectedIndex].value),
        dateTime: $form.find('input[name="date"]').val()



    };
    if (formData.ticketType==0||formData.dateTime=="") {

        notification("Please Fill * ALl Field", 2);
        return;
    }
    console.log(formData);
    searchTicket(formData);
});

function searchTicket(formData) {
    arrangements=[];
    $.ajax({
        url: "/api/ticket/searchTicketBy",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
           // notification("User Save SuccessFully", 3);
            console.log(data);
            console.log(data.length);
            arrangements=[];
            arrangements=data;
            TicketShow(data);
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

function TicketShow(data){
    data.forEach(function (item, index) {
        $('#arrangmentList tr').remove();
        console.log("Tr cal")
       var reporting= new Date(item.reportingDateTime);
       var start= new Date(item.startDateTime);
       var end= new Date(item.endDateTime);
       var location=item.locations[0].name+','+item.locations[0].area+''+item.locations[0].street+","+item.locations[0].city+","+item.locations[0].country;
       var destination=item.locations[1];
       if(destination==null){
           destination=location;
       }else{
           destination=item.locations[1].name+','+item.locations[1].area+''+item.locations[1].street+","+item.locations[1].city+","+item.locations[1].country;

       }
        $("#arrangmentList").append(' <tr id="'+item.id+'">\n' +
            '                          <td >\n' +
            '                            '+item.name+'\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            '+reporting+'\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            '+start+'\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            '+end+'\n' +
            '                          </td>\n' +
            '                          <td >\n' +
            '                            '+location+'\n' +
            '                          </td>\n' +
            '                           <td >\n' +
            '                              '+destination+'\n' +
            '                           </td>\n' +
            '                           <td >\n' +
            '                            ' + '<button onclick="selectArrangment(this);" type="button" class="btn btn-primary pull-right">Select</button>' + '\n' +
            '                           </td>\n' +

            '                        </tr>');
    });

}

function selectArrangment(row) {

    var id= parseFloat($(row).parents("tr").attr('id'));
   console.log(id);
    var arrangement= findArrangementObject(arrangements,id);
    arrangementSelected=id;
    $("#seatList").parents("tr").remove();
    $('#seatList tr').remove();
    seats=arrangement.seats;
    var availableSeat=0;
    arrangement.seats.forEach(function (item, index) {
       if( item.status=="OPEN") {
           availableSeat++;
           $("#seatList").append(' <tr id="' + item.id + '">\n' +
               '                          <td >\n' +
               '                            ' + item.name + '\n' +
               '                          </td>\n' +
               '                          <td>\n' +
               '                            ' + item.fare + '\n' +
               '                          </td>\n' +
               '                           <td >\n' +
               '                            ' + '<button onclick="selectSeat(this);" type="button" class="btn btn-primary pull-right">Select</button>' + '\n' +
               '                           </td>\n' +

               '                        </tr>');
       }
    })
    if(availableSeat==0){
        notification("Seat Not Available",2);
    }
}

function selectSeat(row) {
    var id= parseFloat($(row).parents("tr").attr('id'));
    var seat= findArrangementObject(seats,id);
    $(row).parents("td").html( '<button onclick="RemoveSeat(this);" type="button" class="btn btn-primary pull-right">Remove</button>' );
    totalFareInput.value=parseFloat(totalFareInput.value)+seat.fare;
    seatSelected.push(id);
    console.log(seatSelected);
}
function RemoveSeat(row) {
    var id= parseFloat($(row).parents("tr").attr('id'));
    $(row).parents("td").html( '<button onclick="selectSeat(this);" type="button" class="btn btn-primary pull-right">Select</button>' );
    console.log(row);
    var seat= findArrangementObject(seats,id);
    totalFareInput.value=parseFloat(totalFareInput.value)-seat.fare;
    RemoveSeatObject(id);

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
function findArrangementObject(array, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i].id === value) {
            return array[i];
        }
    }
    return null;
}

function RemoveSeatObject(value) {

    for (var i = 0; i < seats.length; i++) {

        if (seatSelected[i] === value) {

            // console.log("remove"+value);
            seatSelected.splice(i, 1);
            if (seatSelected == null)
                seatSelected = [];

            // console.log("remove"+seats);


        }
    }
    return null;
}
$("#create-ticket-form").submit(function (event) {
    event.preventDefault();

    var $form = $(this);
    var buyer={
        name:$form.find('input[name="buyerName"]').val(),
        phone:$form.find('input[name="phone"]').val(),
        email:$form.find('input[name="email"]').val(),
    }
    var formData = {
        ticketType: parseFloat(ticketTypeSelector.options[ticketTypeSelector.selectedIndex].value),
        comment: $form.find('input[name="comment"]').val(),
        totalFare:parseFloat(totalFareInput.value),
        seats:seatSelected,
        arrangement:arrangementSelected,
        buyerRequest:buyer
    };
    if (formData.totalFare==0||buyer.name==""||buyer.phone==""||buyer.email=="") {

        notification("Please Fill * ALl Field And Select seat", 2);
        return;
    }
    console.log(formData);
    BuyTicket(formData);
});

function BuyTicket(formData) {
    arrangements=[];
    $.ajax({
        url: "/api/ticket",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
             notification("Ticket Buy SuccessFully", 3);
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