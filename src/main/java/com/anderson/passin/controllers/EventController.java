package com.anderson.passin.controllers;

import com.anderson.passin.dto.attendee.AttendeeIdDTO;
import com.anderson.passin.dto.attendee.AttendeeListResponseDTO;
import com.anderson.passin.dto.attendee.AttendeeRequestDTO;
import com.anderson.passin.dto.event.EventIdDTO;
import com.anderson.passin.dto.event.EventRequestDTO;
import com.anderson.passin.dto.event.EventResponseDTO;
import com.anderson.passin.services.AttendeeService;
import com.anderson.passin.services.EventServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventServices service;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.service.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO event = this.service.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(event.eventId()).toUri();

        return ResponseEntity.created(uri).body(event);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(
            @PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO attendeeIdDTO = this.service.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }


    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeeListResponseDTO> getEventAttendess(@PathVariable String id){
        AttendeeListResponseDTO attendeeListResponse = this.attendeeService.getEventsAttendees(id);
        return ResponseEntity.ok(attendeeListResponse);
    }

}
