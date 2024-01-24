package com.example.licensemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
	I struggled quite a bit with this :) the good news is that I feel good about the process and even the outcome even
	though it's incomplete, and stitched/plastered all over

	Note:
	1. there is no DataBase as I kept it simple and created Lists of objects in the Repository classes
	2. I did not have time to finish, so I only focused on the Match Service and Controller - also only started
	implementing Testing for the Match Service and Controller - they don't currently work

	Stages:
	I spent 2h setting up basic classes (Customer, License, Tournament, Match, Player)
	I was then influenced by what I knew so far about Streaming:
		- licenses usually run for a fixed period (1Jan-31Dec)
		- start/end dates are selected manually - tedious and error-prone. (had not realised there are defaults in ALC)
		- I decided to create a Period object (at that stage I didn't know that the Streams code had Licensing Period!)
		- but then I spent a whole working session refactoring things to incorporate this
		- I had not even gotten an API working end-to-end and was over-engineering the domains...
		- so Noe pulled me back to what the requirement was and I backed out of the Period object
	I spent quite a bit of time setting the repository up because I was trying to use Streams
	I then got working on the other parts... comments in the files

	Oh! and I tried to use logging but not very sure what I'm doing!
*/

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LicenseMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseMicroserviceApplication.class, args);
		System.out.println("test");
	}
}
