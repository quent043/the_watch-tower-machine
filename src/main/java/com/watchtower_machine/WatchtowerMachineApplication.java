package com.watchtower_machine;

import com.watchtower_machine.conf.Connmanagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchtowerMachineApplication {

	public static void main(String[] args) {

		SpringApplication.run(WatchtowerMachineApplication.class, args);
		System.out.println("Yo dude");
//		Connmanagement.createConnection(); <----- Géré dans classe DatabaseInit
	}

}
