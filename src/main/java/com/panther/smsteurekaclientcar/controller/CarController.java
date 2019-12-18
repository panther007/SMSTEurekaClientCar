package com.panther.smsteurekaclientcar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private RestTemplate restTemp;
	
	@Autowired
	private DiscoveryClient discClient;
	
	@GetMapping("/get")
	public String getCars() {
		return "Cars List";
	}
	
	@GetMapping("/get/{id}")
	public String getCar_PathVar(@PathVariable String id) {
		List<ServiceInstance> instance = discClient.getInstances("user-service");
		ServiceInstance userUrl = instance.get(0);
		URI newUri = userUrl.getUri();
		
//		String user = "U101";
		String user = restTemp.getForObject(newUri+"/user/get/101", String.class);
		String sResp = "Car " + id + " is allocated to " + user;
		return sResp;
	}

}
