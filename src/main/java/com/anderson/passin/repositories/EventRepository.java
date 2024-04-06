package com.anderson.passin.repositories;

import com.anderson.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String>{
}
