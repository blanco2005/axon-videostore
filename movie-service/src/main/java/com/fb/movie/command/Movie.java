package com.fb.movie.command;

import com.fb.api.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Movie {

    private final static Logger logger = LoggerFactory.getLogger(Movie.class);

    @AggregateIdentifier
    private String serialNumber;
    private String title;

    private boolean isAvailable;

    public Movie() {
    }

    @CommandHandler
    public Movie(RegisterMovieCommand command) {
        apply(new MovieRegisteredEvent(command.getSerialNumber(), command.getTitle()));
        logger.info("Generated MovieRegisteredEvent");
    }

    @CommandHandler
    public void on(RentMovieCommand command) {
        if (!isAvailable) {
            throw new RuntimeException("Movie already rented!");
        }
        apply(new MovieRentedEvent(command.getSerialNumber(), title, command.getCustomer()));
        logger.info("Generated MovieRentedEvent");
    }

    @CommandHandler
    public void on(ReturnMovieCommand command) {
        apply(new ReturnedMovieEvent(command.getSerialNumber(), title, command.getCustomer()));
        logger.info("Generated ReturnedMovieEvent");
    }

    @EventSourcingHandler
    private void handle(MovieRegisteredEvent event) {
        logger.info("handling MovieRegisteredEvent");
        this.serialNumber = event.getSerialNumber();
        this.isAvailable = true;
        this.title = event.getTitle();
    }

    @EventSourcingHandler
    private void handle(MovieRentedEvent event) {
        logger.info("handling MovieRentedEvent");
        this.isAvailable = false;
    }

    @EventSourcingHandler
    private void handle(ReturnedMovieEvent event) {
        logger.info("handling ReturnedMovieEvent");
        this.isAvailable = true;
    }
}
