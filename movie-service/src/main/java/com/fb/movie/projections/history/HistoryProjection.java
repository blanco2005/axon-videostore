package com.fb.movie.projections.history;

import com.fb.api.MovieHistoryQuery;
import com.fb.api.MovieRegisteredEvent;
import com.fb.api.MovieRentedEvent;
import com.fb.api.ReturnedMovieEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryProjection {

    private final MovieHistoryRepository movieHistoryRepository;

    public HistoryProjection(MovieHistoryRepository movieHistoryRepository) {
        this.movieHistoryRepository = movieHistoryRepository;
    }

    @EventHandler
    public void on(MovieRegisteredEvent event) {
        movieHistoryRepository.save(new MovieHistory(event.getSerialNumber(), "Movie has been purchased"));
    }

    @EventHandler
    public void on(MovieRentedEvent event) {
        movieHistoryRepository.save(new MovieHistory(event.getSerialNumber(), "Movie has been rented by " + event.getCustomer()));
    }

    @EventHandler
    public void on(ReturnedMovieEvent event) {
        movieHistoryRepository.save(new MovieHistory(event.getSerialNumber(), "Movie has been rented by " + event.getCustomer()));
    }

    @QueryHandler
    public List<MovieHistory> on(MovieHistoryQuery query) {
        return movieHistoryRepository.findBySerialNumber(query.getSerialNumber());
    }
}
