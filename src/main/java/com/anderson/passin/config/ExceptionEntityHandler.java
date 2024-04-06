package com.anderson.passin.config;

import com.anderson.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.anderson.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import com.anderson.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import com.anderson.passin.domain.event.exceptions.EventFullException;
import com.anderson.passin.domain.event.exceptions.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handlerEventNotFound(EventNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity handlerEventFull(EventFullException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handlerAttendeeNotFoundException(AttendeeNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handlerAttendeeAlreadyExistException(AttendeeAlreadyExistException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handlerCheckInAlreadyExistException(CheckInAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
