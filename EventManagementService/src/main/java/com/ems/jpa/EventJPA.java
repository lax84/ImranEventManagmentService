package com.ems.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entities.Event;

public interface EventJPA extends JpaRepository<Event,Integer> {

}
