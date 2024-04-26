package com.example.ltn_admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ltn_admin.entity.Detail;
import com.example.ltn_admin.service.DetailService;

import org.springframework.ui.Model;


@Controller
public class MainController {
	
	@Autowired
	private DetailService detailService;
	
	
	@GetMapping("/api")
	public String DetailTestControl(RedirectAttributes redirectAttributes, @RequestParam("q") String licensePlate) {
	    System.out.println(licensePlate);
	    try {
	        Detail detail = detailService.getDetailByLicensePlate(licensePlate);
	        redirectAttributes.addFlashAttribute("detail", detail);
//	        model.addAttribute("detail", detail);
	        System.out.println(detail);
	        return "redirect:/admin";

	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return "redirect:/admin";
	    }
	}

	
	
	@GetMapping(value =  "/admin")
	public String cameraControl() {
		return "/camera";
	}
	@GetMapping(value =  "/history")
	public String history() {
		return "/history";
	}
}
