var selectedTicketId;
var tickets=[]
$(document).ready(function () {

    $("#nav-placeholder").load("navbar.html");
    SearchTicket();

});

$("#ticket-find-form").submit(function (event) {
    event.preventDefault();

    var $form = $(this);

    var phone= $form.find('input[name="phone"]').val();

    if (phone=="") {

        notification("Please Fill Phone Field", 2);
        return;
    }
    console.log(phone);
    //SearchTicket(phone);
});

function SearchTicket() {
    $.ajax({
        url: "../api/ticket/soldTicket",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            tickets=data;
            data.forEach(myFunction);
            function myFunction(item, index) {
                $("#ticket").append(' <tr id="' + item.id + '">\n' +
                    '                          <td >\n' +
                    '                            ' + item.serial + '\n' +
                    '                          </td>\n' +
                    '                          <td>\n' +
                    '                            ' + item.totalFare + '\n' +
                    '                          </td>\n' +
                    '                           <td >\n' +
                    '                            ' + '<button onclick="SelectTicket(this);" type="button" class="btn btn-primary pull-right">Select Ticket</button>' + '\n' +
                    '                           </td>\n' +
                    '                           <td >\n' +
                    '                            ' + '<button onclick="RefundTicket(this);" type="button" class="btn btn-primary pull-right">Refund</button>' + '\n' +
                    '                           </td>\n' +

                    '                        </tr>');
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
function RefundTicket(row) {
    var id= parseFloat($(row).parents("tr").attr('id'));
    TicketRefund(id);
}
function SelectTicket(row) {
    selectedTicketId= parseFloat($(row).parents("tr").attr('id'));
    var ticket=findTicket(tickets,selectedTicketId);
    $("#seatList").parents("tr").remove();
    ticket.seats.forEach(function (item,index) {

        $("#seatList").append(' <tr id="' + item.id + '">\n' +
            '                          <td >\n' +
            '                            ' + item.name + '\n' +
            '                          </td>\n' +
            '                          <td>\n' +
            '                            ' + item.fare + '\n' +
            '                          </td>\n' +
            '                           <td >\n' +
            '                            ' + '<button onclick="RefundSeatClick(this);" type="button" class="btn btn-primary pull-right">Refund</button>' + '\n' +
            '                           </td>\n' +

            '                        </tr>');
    });
}
function RefundSeatClick(row) {
    var seatId= parseFloat($(row).parents("tr").attr('id'));
    RefundSeat(seatId);
}
function RefundSeat(seatId) {
    $.ajax({
        url: "../api/ticket/refund/"+selectedTicketId+"/"+seatId,
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            notification("Refund SuccessFully",3);
            window.location.replace("../refund")

        }

    });
}

function TicketRefund(id) {
    $.ajax({
        url: "../api/ticket/refund/"+id,
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            notification("Refund SuccessFully",3);
            window.location.replace("../refund")

        }

    });
}
function findTicket(array, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i].id === value) {
            return array[i];
        }
    }
    return null;
}