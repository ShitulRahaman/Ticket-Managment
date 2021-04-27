var totalSale = document.getElementById("totalSale");
var totalTicket = document.getElementById("totalTicket");
var totalArrangement = document.getElementById("totalArrangement");
var totalSoldTicket = document.getElementById("totalSoldTicket");
var totalRefundTicket = document.getElementById("totalRefundTicket");
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

function searchTicket(formData) {

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

            arrangements = data;
            TicketShow();
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