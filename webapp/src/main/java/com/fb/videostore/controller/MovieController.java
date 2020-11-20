package com.fb.videostore.controller;

import com.fb.videostore.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/movies/{serialNumber}/rent")
    public ResponseEntity<String> rent(@PathVariable("serialNumber") String serialNumber,
                                   @RequestParam("customer") String customer) {

        movieService.rent(serialNumber, customer);
        return ResponseEntity.ok(format("Movie with sn %s rented to %s", serialNumber, customer));
    }
}
