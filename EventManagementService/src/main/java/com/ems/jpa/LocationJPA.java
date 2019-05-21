package com.ems.jpa;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ems.entities.Location;

public interface LocationJPA extends JpaRepository<Location,Integer> {

	
	public List<Location> findByCountry(String country);//here for finder method the naming should
													//be like this...findBy___ or readBy___ or getBy___ 
		//findByCountryOrCity is also possible// depends on the requirement
	
	public List<Location> findByCountryAndCity(String country,String city);
	
	
	public List<Location> findByCountryOrCityAllIgnoreCase(String country,String city);
	
	@Query(name="bycity") //the query is written in Location.java in entities
	public List<Location> findByCities(String cities);
	
	
	
	@Transactional
	public boolean deleteByCity(String city);
	
	
	@Transactional
	@Query(value="update location set city=:cityName where location_id=:locationId",nativeQuery=true)
	@Modifying //Indicates a query method should be considered as 
	//modifying query as that changes the way it needs to be executed
	public void updateByCityName(@Param("locationId") int locationId, @Param("cityName") String cityName);
	
}
