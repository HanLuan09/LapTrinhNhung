package com.example.ltn_admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ltn_admin.entity.History;
import com.example.ltn_admin.service.DetailService;
import com.example.ltn_admin.service.HistoryService;
import com.example.ltn_admin.service.MqttService;


@CrossOrigin
@RestController
@RequestMapping("api")
public class MainController {
	
	@Autowired
	private MqttService mqttService;
	
	
	@GetMapping("/camera")
	public String DetailTestControl(RedirectAttributes redirectAttributes, @RequestParam("q") String licensePlate) {
	    System.out.println(licensePlate);
	    
	    mqttService.MqttPublisher(licensePlate);
	    return "licensePlate";
	}
}
