package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.EventDAO;
import com.ems.entities.Location;
import com.ems.exceptions.CityNameNotFoundException;
import com.ems.entities.Event;

@Service
public class EventService {
	
	@Autowired
	EventDAO eventDAO;
	
	public boolean deleteLocationCity(int locationId) {
		
	return eventDAO.deleteLocation(locationId);	
	
	}
	
	public boolean updateLocationCity(int locationId,String cityName) {
	
		
		return eventDAO.updateLocationCity(locationId, cityName);
	}
	
	
	public List<Location> searchBylocationCountry (String countryName)throws CityNameNotFoundException{
		return eventDAO.searchByLocationCountry(countryName);
	}
	
	public boolean insertLocation(List<Location> locations) {
		
		return eventDAO.insertLocation(locations);
	}
	
	public Event getEventDetails(int eventId) {
		return eventDAO.getEventDetails(eventId);
	}
	
	
	public boolean insertEventDetails() {
	
return eventDAO.insertEventDetails();	
	
	}
	
	}


