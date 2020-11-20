package com.fb.movie.projections.availability;

import com.fb.api.AllMovieAvailabilityQuery;
import com.fb.api.MovieRegisteredEvent;
import com.fb.api.MovieRentedEvent;
import com.fb.api.ReturnedMovieEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StoreProjection {

    private final MovieAvailabilityRepository movieAvailabilityRepository;

    public StoreProjection(MovieAvailabilityRepository movieAvailabilityRepository) {
        this.movieAvailabilityRepository = movieAvailabilityRepository;
    }

    @EventHandler
    public void on(MovieRegisteredEvent event) {
        Optional<MovieAvailability> result = movieAvailabilityRepository.findById(event.getTitle());
        MovieAvailability movieAvailability;
        if (result.isPresent()) {
            movieAvailability = result.get();
            movieAvailability.setNumberOfCopiesAvailable(movieAvailability.getNumberOfCopiesAvailable() + 1);
            movieAvailability.setNumberOfCopiesPurchased(movieAvailability.getNumberOfCopiesPurchased() + 1);
        }
        else {
            movieAvailability = new MovieAvailability();
            movieAvailability.setTitle(event.getTitle());
            movieAvailability.setNumberOfCopiesPurchased(1);
            movieAvailability.setNumberOfCopiesAvailable(1);
        }
        movieAvailabilityRepository.save(movieAvailability);
    }

    @EventHandler
    public void on(MovieRentedEvent event) {
        Optional<MovieAvailability> result = movieAvailabilityRepository.findById(event.getTitle());
        result.ifPresent( m -> {
            m.setNumberOfCopiesAvailable(m.getNumberOfCopiesAvailable() -1);
            movieAvailabilityRepository.save(m);
        });
    }

    @EventHandler
    public void on(ReturnedMovieEvent event) {
        Optional<MovieAvailability> result = movieAvailabilityRepository.findById(event.getTitle());
        result.ifPresent( m -> {
            m.setNumberOfCopiesAvailable(m.getNumberOfCopiesAvailable() +1);
            movieAvailabilityRepository.save(m);
        });
    }

    @QueryHandler
    public List<MovieAvailability> handle(AllMovieAvailabilityQuery query) {
        return movieAvailabilityRepository.findAll();
    }
}
