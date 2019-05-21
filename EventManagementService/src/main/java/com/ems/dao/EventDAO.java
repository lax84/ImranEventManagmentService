package com.ems.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import com.ems.entities.*;
import com.ems.entities.Event;
import com.ems.entities.Location;

import com.ems.entities.Voters;
import com.ems.exceptions.CityNameNotFoundException;
import com.ems.jpa.EventJPA;
import com.ems.jpa.LocationJPA;
import com.ems.jpa.VotersJPA;
import com.ems.jpa.SessionJPA;


/*import com.ems.model.Event;
import com.ems.model.Location;
import com.ems.model.Session;
import com.ems.pojo.EventLocation;*/
@Repository
//@Transactional(propagation=Propagation.REQUIRES_NEW)
public class EventDAO {
	
	//@Autowired
	JdbcTemplate jdbcTemplate;
	
	//@Autowired
	SessionFactory sessionFactory;// ORM replacement for jdbc template
	
	
	@Autowired
	EventJPA eventJPA;
	
	@Autowired
	LocationJPA locationJPA;
	
	@Autowired
	SessionJPA sessionJPA;
	
	@Autowired
	VotersJPA votersJPA;
	
	
	public List<Location> searchByLocationCountry(String countryName) 
			throws CityNameNotFoundException{
		
		
		List<Location> locations=locationJPA.findByCities(countryName);
		if(locations.isEmpty())
			throw new CityNameNotFoundException("Hi User sorry The given city name is not found");
		return locations;
		
	}
	
	
	//@Transactional
	public boolean deleteLocation(int locationId) {
		
		locationJPA.deleteById(locationId);		//default method of JPA
		
		
		return true;
	}
	
	
	@Transactional
	public boolean updateLocationCity(int locationId,String cityName) {
		
		locationJPA.updateByCityName(locationId, cityName);
		return true;
	}
	
	@Transactional
	public boolean insertLocation(List<Location> locations) {
		
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		
		locations.forEach(l -> session.save(l));
		
		return true;
	}
	
	//@Transactional
	public boolean insertEventDetails() {
		
	/*	org.hibernate.Session session=sessionFactory.getCurrentSession();*/
		
        Location location1 = new Location(201,"1057 DT","London","England");
        Location location2 = new Location(202,"The Excalibur","Las Vegas","USA");
        Location location3 = new Location(203,"The Palatial America Hotel","Salt Lake City","USA");
        Location location4 = new Location(204,"The UN Angular Center","New York","USA");

        Voters voter1 = new Voters(1,"bradgreen");
        Voters voter2 = new Voters(2,"igorminar");
        Voters voter3 = new Voters(3,"martinfowler");
        Voters voter4 = new Voters(4,"johnpapa");
		
		
		
		
	//	session.beginTransaction();
		  Event event = new Event(101,"Angular Connect",LocalDate.of(2036,9,26),"10:00 am",599.99f,"assetsimagesangularconnect-shield.png");

	        event.setLocation(location1);
	        
	      
	        
	        Session session1 = new Session(301,"UsingAngular4Pipes","Peter Bacon Darwin",1,"Intermediate","Learn all about the new pipes in Angular 4, bothhow to write them");
	        session1.setEvent(event);
	        
	        List<Voters> votersList1=new ArrayList<>();
	        votersList1.add(voter1);
	        votersList1.add(voter2);
	        votersList1.add(voter3);
	        session1.setVoters(votersList1);
	        
	        Session session2 = new Session(302,"Getting the most","Jeff Cross",1,"Intermediate","We all know that our dev teams work hard, but with the right management they can be even more productive, without overworking them.");
	        session2.setEvent(event);
	        
	        List<Voters> votersList2=new ArrayList<>();
	        votersList2.clear();
	        votersList2.add(voter2);
	        votersList2.add(voter3);
	        session2.setVoters(votersList2);
	        
	        
	        Session session3 = new Session(303,"Angular4Performance","Rob Wormald",1,"Advanced","Angular 4 Performance is hot. In this session, we'll see how Angular gets such great performance by preloadi");
	        session3.setEvent(event);
	      
	        
	        Session session4 = new Session(304,"Angular 5","Brad Green",2,"Advanced","Even though Angular 5 is still 6 years away, we all want to know all about it so that we can spend ");
	        session4.setEvent(event);
	       
	        
	        
	        Session session5 = new Session(305,"BasicsofAngular4","John Papa",2,"Beginner","It's time to learn the basics of Angular 4. This talk will give you everything you need to know about Angular 4 to ");
	        session5.setEvent(event);
	        List<Voters> votersList5=new ArrayList<>();
	        votersList5.clear();
	        votersList5.add(voter2);
	        votersList5.add(voter3);
	        votersList5.add(voter4);
	        session5.setVoters(votersList5);
	        
			//Transaction tx = session.beginTransaction();
			
			locationJPA.save(location1);
			locationJPA.save(location2);
			locationJPA.save(location3);
			locationJPA.save(location4);
			
		votersJPA.save(voter1);
		votersJPA.save(voter2);
		votersJPA.save(voter3);
		votersJPA.save(voter4);
			
			eventJPA.save(event);
			
			sessionJPA.save(session1);
			sessionJPA.save(session2);
			sessionJPA.save(session3);
			sessionJPA.save(session4);
			sessionJPA.save(session5);
			
		return true;
	}
	
	public com.ems.entities.Event getEventDetails(int eventId) {
		
		//Findermethods(Readymade finder methods available
		//Inside JPA Repositories
		
		
		Optional<Event> optionalEvent=eventJPA.findById(eventId);
		
		return optionalEvent.get();
	}
	
	
	/*public Event getEventDetails(int eventId) {
		
		EventLocation eventLocation=jdbcTemplate.queryForObject("select \r\n" + 
				"eventalias.event_id , eventalias.date ,eventalias.imageUrl , \r\n" + 
				"\r\n" + 
				"eventalias.location_Id , eventalias.name , eventalias.price , \r\n" + 
				"\r\n" + 
				"eventalias.time , locationalias.loc_id, locationalias.address ,\r\n" + 
				" \r\n" + 
				"locationalias.city , locationalias.country \r\n" + 
				"from Event eventalias \r\n" + 
				"left outer join Location locationalias on \r\n" + 
				"eventalias.location_Id=locationalias.loc_id\r\n" + 
				"where eventalias.event_id=?", new Object[] {eventId},new EventLocationMapper());
		
		Location location=new Location();
		location.setId(eventLocation.getLoc_id());
		location.setAddress(eventLocation.getAddress());
		location.setCity(eventLocation.getCity());
		location.setCountry(eventLocation.getCountry());
		
		
		Event event=new Event();
		event.setId(eventLocation.getEvent_id());
		event.setName(eventLocation.getName());
		event.setImageUrl(eventLocation.getImageUrl());
		event.setTime(eventLocation.getTime());
		
		
		
		
		
		List<Session> sessions=jdbcTemplate.query("\r\n" + 
				"\r\n" + 
				"select sessionsalias.event_id , sessionsalias.id , sessionsalias.description , \r\n" + 
				"\r\n" + 
				"sessionsalias.duration, \r\n" + 
				"sessionsalias.level , sessionsalias.name , \r\n" + 
				"\r\n" + 
				"sessionsalias.presenter  from Session sessionsalias where sessionsalias.event_id=?;\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"",new Object[] {event.getId()},new SessionMapper());
		
		List<Session> tempSessions=new ArrayList<>();
		
		for(Session session : sessions) {
			List<Voters> voters=jdbcTemplate.query("select sesvotalias.session_id , sesvotalias.voter_id, votersalias.voterId , votersalias.name \r\n" + 
					"from Session_Voters sesvotalias\r\n" + 
					" inner join Voters votersalias on sesvotalias.voter_id=votersalias.voterId \r\n" + 
					"where sesvotalias.session_id=?", new Object[] {session.getId()},
					new VoterMapper());
					
					String votes[]=new String[voters.size()];
					int i=0;
					for(Voters vote : voters) {
						votes[i]=vote.getVoter_name();
					}
					session.setVoters(votes);
					tempSessions.add(session);
					}
					
		event.setList(tempSessions);
		return event;
		}*/
		
	}


