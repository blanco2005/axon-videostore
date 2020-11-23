package com.fb.videostore.controller;

import com.fb.movie.projections.availability.MovieAvailability;
import com.fb.movie.projections.history.MovieHistory;
import com.fb.videostore.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PutMapping("/movies/{serialNumber}")
    public ResponseEntity<String> register(@PathVariable("serialNumber") String serialNumber,
                                   @RequestParam("title") String title) {

        movieService.register(serialNumber, title);
        return ResponseEntity.ok(format("Movie with sn %s and title %s registered", serialNumber, title));
    }

    @PostMapping("/movies/{serialNumber}/return")
    public ResponseEntity<String> returnMovie(@PathVariable("serialNumber") String serialNumber,
                                       @RequestParam("customer") String customer) {

        movieService.returnMovie(serialNumber, customer);
        return ResponseEntity.ok(format("Movie with sn %s returned by %s", serialNumber, customer));
    }

    @GetMapping("/movies")
    public ResponseEntity<String> moviesAvailability() {
        List<MovieAvailability> allMovieAvailability = movieService.getAllMovieAvailability();
        String result = allMovieAvailability.stream().map(m -> m.toString()).collect(Collectors.joining("\n"));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/movies/{serialNumber}/history")
    public ResponseEntity<String> movieHistory(@PathVariable("serialNumber") String serialNumber) {

        List<MovieHistory> movieHistory = movieService.getMovieHistory(serialNumber);
        String result = movieHistory.stream().map(h -> h.getDescription()).collect(Collectors.joining("\n"));
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(RuntimeException e) {
        return new ResponseEntity<String>("Movie already rented", HttpStatus.NOT_FOUND);
    }
}
