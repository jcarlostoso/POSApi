package com.jtorresDev.posAPI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PosApiApplication {
@Autowired


	public static void main(String[] args) {
		SpringApplication.run(PosApiApplication.class, args);
	System.out.print("direccion swagger: http://localhost:8080/posapi/swagger-ui/index.html");
	}


}
