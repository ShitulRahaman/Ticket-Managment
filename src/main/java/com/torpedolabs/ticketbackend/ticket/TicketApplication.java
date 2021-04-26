package com.torpedolabs.ticketbackend.ticket;

import com.torpedolabs.ticketbackend.ticket.Dao.Authority;
import com.torpedolabs.ticketbackend.ticket.Repository.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

}
