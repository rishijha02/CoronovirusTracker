package com.rs.corona.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rs.corona.models.locationstat;
import com.rs.corona.service.coronaservice;

@Controller
public class control {
	
	@Autowired
	coronaservice corona;
	
	@GetMapping("/")
	public String home(Model model)
	
	{
		List<locationstat>all=corona.getAllstat();
		int totalcases=all.stream().mapToInt(stat->stat.getLatestcases()).sum();
		model.addAttribute("totalcases", totalcases);
		
		model.addAttribute("cor", corona.getAllstat());
		return "home";
	}

}
