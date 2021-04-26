package com.torpedolabs.ticketbackend.ticket.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**** That Only For Run Htlm as Other Fornt End Server Other hand it host for Other server *****/
@Controller
public class FrontendController {

    @GetMapping("/")
    public String Login() {
        return "home.html";
    }

    @GetMapping("/login")
    public String GetLogin() {
        return "admin/AdminLogin.html";
    }

    @GetMapping("/dashboard")
    public String GetDashBoard() {
        return "dashboard.html";
    }

    @GetMapping("/create_arrangement")
    public String GetCreateUser() {
        return "admin/CreateArrangement.html";
    }

    @GetMapping("/create_ticket_type")
    public String GetUserShop() {
        return "admin/CreateTicketType.html";
    }

    @GetMapping("/create_location")
    public String GetShopItem() {
        return "admin/CreateLocation.html";
    }
    @GetMapping("/create_ticket")
    public String GetTicket() {
        return "admin/CreateLocation.html";
    }

    @GetMapping("/user_list")
    public String GetUserList() { return "admin/UserList.html"; }

    @GetMapping("/shop_list")
    public String GetShopList() { return "admin/ShopList.html"; }

    @GetMapping("/item_list")
    public String GetItemList() {
        return "admin/ItemList.html";
    }

    @GetMapping("/food_user")
    public String GetFoodUserList() {
        return "admin/FoodUserList.html";
    }

    @GetMapping("/food_oder")
    public String FoodOderList() {
        return "admin/OderList.html";
    }

    @GetMapping("/oder_item_list")
    public String OderItemList() {
        return "admin/OderItemList.html";
    }

    @GetMapping("/maps")
    public String getPerson9() {
        return "example/maps.html";
    }

}
