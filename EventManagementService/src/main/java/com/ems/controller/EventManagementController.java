package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entities.Location;
import com.ems.exceptions.CityNameNotFoundException;
import com.ems.entities.*;
import com.ems.service.EventService;

@Controller
@RequestMapping("/root")
//@RestController
public class EventManagementController {
	
	@Autowired
	EventService eventService;
	
	@GetMapping("/searchlocation/{countryName}")
	@ResponseBody
	public List<Location> searchBylocationCountry(@PathVariable String countryName)
	throws CityNameNotFoundException{
		return eventService.searchBylocationCountry(countryName);
	}
	
	@GetMapping("/deletelocation/{locationId}")
	@ResponseBody
	public boolean deleteLocationCity(@PathVariable int locationId) {
		
		return eventService.deleteLocationCity(locationId);	
		
		}
	
	@GetMapping("/updatelocation/{locationId}/{cityName}")
	@ResponseBody
		public boolean updateLocationCity(@PathVariable int locationId,@PathVariable String cityName) {
		
			
			return eventService.updateLocationCity(locationId, cityName);
		}
		
	
	@PostMapping("/locations")
	@ResponseBody
	public boolean insertLocation(@RequestBody List<Location> locations) {
		return eventService.insertLocation(locations);
	}
	
	//Handler Method(EndPoint)
	//@RequestMapping(value="/welcome",method=RequestMethod.GET)
	@GetMapping("/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome To Spring World!!!";
	}
	
	@GetMapping("/insert")
	@ResponseBody
	public boolean insertEventDetails() {
		return eventService.insertEventDetails();
	}
	//URI Templating Feature -->URI Template Variable
	@GetMapping("/eventdetails/{eventId}")
	@ResponseBody
	public Event getEventDetails(@PathVariable int eventId) {  //or @PathVariable("eventId") int eid
		return eventService.getEventDetails(eventId);
	}

}
