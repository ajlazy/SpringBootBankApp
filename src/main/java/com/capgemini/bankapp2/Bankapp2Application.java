package com.capgemini.bankapp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@EnableAutoConfiguration
@ComponentScan({"com.capgemini.bankapp2"})
@SpringBootApplication(scanBasePackages="com.capgemini.bankapp2")
public class Bankapp2Application {

	public static void main(String[] args) {
		SpringApplication.run(Bankapp2Application.class, args);
	}
}
