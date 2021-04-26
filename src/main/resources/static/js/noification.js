



function connect() {

   // Create and init the SockJS object
   var socket = new SockJS('/ws');
   var stompClient = Stomp.over(socket);
   //console.log(" lll");
   // Subscribe the '/notify' channell
   stompClient.connect({}, function(frame) {
      stompClient.subscribe('/queue/reply', function(notification) {
           // console.log(" l"+notification);
         ;
         // Call the notify function when receive a notification
         notify(JSON.parse(notification.body));

      });
   },function(error) {
      alert("STOMP error " + error);
   });

   return;
} // function connect

/**
 * Display the notification message.
 */
function notify(message) {
   console.log("ss"+ message.count);

   $("#notificationNo").append('<span  class="notification">'+message.length+'</span>') ;
   message.forEach(myFunction);
   function myFunction(item, index) {
   $("#notificationrow").append('<a class="dropdown-item" href="../food_oder">'+item.name+'</a>') ;}


}

/**
 * Init operations.
 */
$(document).ready(function() {

   // Start the web socket connection.
   connect();

});
