package com.sample.corona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sample.corona.Resource.Fetchdata;
import com.sample.corona.model.LocationStats;

@Controller
public class HomeContoller {

	@Autowired
	private Fetchdata Fetchdata;
	@GetMapping("/add")
	public String home(Model model) {
        List<LocationStats> allStats = Fetchdata.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }

	@GetMapping("/html")
	public String h()
	{
		return "home";
	}
	
}

