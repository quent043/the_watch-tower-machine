package com.watchtower_machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchtowerMachineApplication {

	public static void main(String[] args) {

		SpringApplication.run(WatchtowerMachineApplication.class, args);
		System.out.println("Yo dude");
//		Connmanagement.createConnection(); <----- Géré dans classe DatabaseInit

//		User user1 = new BasicUser("Bohdi", "", "Bohdi", "swayze");
//		Admin admin1 = new Admin("DC", "Quentin", "Sangoku16", "swayze", true);
//		System.out.println(user1);
//		System.out.println(admin1);
//		Session session = Connmanagement.getSession();
//		session.beginTransaction();
//		session.save(user1);
//		session.save(admin1);
//		session.getTransaction().commit();
//		session.close();
//		session = null;

	}
}
