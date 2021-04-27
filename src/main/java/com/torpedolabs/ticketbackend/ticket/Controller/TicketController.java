package com.torpedolabs.ticketbackend.ticket.Controller;


import com.torpedolabs.ticketbackend.ticket.Model.Request.SearchTicketForBuyRequest;
import com.torpedolabs.ticketbackend.ticket.Model.Request.StatusRequest;
import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketRequest;
import com.torpedolabs.ticketbackend.ticket.Service.TicketService;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody TicketRequest ticketRequest){
        return ticketService.Save(ticketRequest);
    }
    @GetMapping
    public ResponseEntity<?> Gets(){
        return ticketService.Gets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id){
        return  ticketService.Get(id);
    }
    @PutMapping
    public ResponseEntity<?> Update(@RequestBody TicketRequest ticketRequest){
        return  ticketService.Update(ticketRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        return ticketService.Delete(id);
    }

    @PostMapping("/searchTicketBy")
    public ResponseEntity<?> SearchTicketBuy(@RequestBody SearchTicketForBuyRequest searchTicketForBuyRequest){
        return ticketService.TicketSearchForBuy(searchTicketForBuyRequest);
    }
    @PostMapping("/searchTicketByStatus")
    public ResponseEntity<?> SearchTicketByStatus(@RequestBody StatusRequest statusRequest){
        return ticketService.FoundTicket(ProcessStatus.SOLD.getProcessStatus(statusRequest.getStatus()));
    }
    @GetMapping("/soldTicket")
    public ResponseEntity<?> SearchTicket(){
        return  ticketService.TicketSearchByBuyerPhone();
    }
    @GetMapping("/refund/{id}")
    public ResponseEntity<?> RefundTicket(@PathVariable Long id){
        return  ticketService.TicketRefund(id);
    }
    @GetMapping("/refund/{ticketId}/{seatId}")
    public ResponseEntity<?> RefundTicketSeat(@PathVariable  Long ticketId,@PathVariable Long seatId){
        return  ticketService.SeatRefund(ticketId,seatId);
    }
    @GetMapping("/totalSale")
    public ResponseEntity<?> TotalSale(){
        return  ticketService.TicketTotalSale();
    }
    @GetMapping("/totalSoldTicket")
    public ResponseEntity<?> TotalSoldTicket(){
        return  ticketService.TicketSoldCount();
    }
    @GetMapping("/totalTicket")
    public ResponseEntity<?> TotalTicket(){
        return  ticketService.TicketCount();
    }
    @GetMapping("/totalRefundTicket")
    public ResponseEntity<?> TicketRefundCount(){
        return  ticketService.TicketRefundCount();
    }
}
