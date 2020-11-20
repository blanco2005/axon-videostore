package com.fb.videostore.service;

import com.fb.movie.projections.MovieAvailability;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MovieService {
    void register(String serialNumber, String title);

    void rent(String serialNumber, String customer);

    List<MovieAvailability> getAllMovieAvailability();
}
