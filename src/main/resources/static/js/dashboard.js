var totalSale = document.getElementById("totalSale");
var totalTicket = document.getElementById("totalTicket");
var totalArrangement = document.getElementById("totalArrangement");
var totalSoldTicket = document.getElementById("totalSoldTicket");
var totalRefundTicket = document.getElementById("totalRefundTicket");
var satatusSelected = document.getElementById("ticketType");
$(document).ready(function () {
    $("#nav-placeholder").load("navbar.html");
    findTotalSale();
    findTotalSoldTicket();
    findTotalRefundTicket();
    findTotalTicketCount();
    findTotalArrangementCount();
    findALLTicket();

});

function findTotalSale() {
    $.ajax({
        url: "../api/ticket/totalSale",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            console.log("totalSale");
            if (data == "") {
                totalSale.innerHTML = "0.00TK";
            } else {
                totalSale.innerHTML = data+".00TK";
            }
        }
    });
}

function findTotalSoldTicket() {
    $.ajax({
        url: "../api/ticket/totalSoldTicket",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            console.log("totalSoldTicket");
            if (data == "") {
                totalSoldTicket.innerHTML = "0";
            } else {
                totalSoldTicket.innerHTML = data+"";
            }
        }
    });
}

function findTotalRefundTicket() {
    $.ajax({
        url: "../api/ticket/totalRefundTicket",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            console.log("totalRefundTicket");
            if (data == "") {
                totalRefundTicket.innerHTML = "0";
            } else {
                totalRefundTicket.innerHTML = data+"";
            }
        }
    });
}

function findTotalTicketCount() {
    $.ajax({
        url: "../api/ticket/totalTicket",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            console.log("totalTicket");
            if (data == "") {
                totalTicket.innerHTML = "0";
            } else {
                totalTicket.innerHTML = data;
            }
        }
    });
}

function findALLTicket() {
    $.ajax({
        url: "../api/ticket",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            console.log("ticket");
            data.forEach(myFunction);
            function myFunction(item, index) {
                $("#ticket").append(' <tr id="' + item.id + '">\n' +
                    '                          <td >\n' +
                    '                            ' + item.serial + '\n' +
                    '                          </td>\n' +
                    '                          <td>\n' +
                    '                            ' + item.totalFare + '\n' +
                    '                          </td>\n' +
                    '                          <td>\n' +
                    '                            ' + item.status + '\n' +
                    '                          </td>\n' +


                    '                        </tr>');
            }
        }
    });
}

function findTotalArrangementCount() {
    $.ajax({
        url: "../api/arrangement/count",
        type: "GET",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        success: function (data) {
            console.log(data);
            console.log("count");
            if (data == "") {
                totalArrangement.innerHTML = "0";
            } else {
                totalArrangement.innerHTML = data;
            }
        }
    });
}
$("#ticket-find-form").submit(function (event) {
    event.preventDefault();



    var formData = {
        status: parseFloat(satatusSelected.options[satatusSelected.selectedIndex].value)
    };

    console.log(formData);
    searchTicket(formData);
});

function searchTicket(formData) {

    $.ajax({
        url: "/api/ticket/searchTicketByStatus",
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
            //$("#ticket").parents("tr").remove();
            $('#ticket tr').remove();
            data.forEach(myFunction);
            function myFunction(item, index) {
                $("#ticket").append(' <tr id="' + item.id + '">\n' +
                    '                          <td >\n' +
                    '                            ' + item.serial + '\n' +
                    '                          </td>\n' +
                    '                          <td>\n' +
                    '                            ' + item.totalFare + '\n' +
                    '                          </td>\n' +
                    '                          <td>\n' +
                    '                            ' + item.status + '\n' +
                    '                          </td>\n' +


                    '                        </tr>');
            }

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