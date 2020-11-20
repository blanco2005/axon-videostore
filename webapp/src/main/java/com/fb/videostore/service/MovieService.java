package com.fb.videostore.service;

import com.fb.movie.projections.availability.MovieAvailability;
import com.fb.movie.projections.history.MovieHistory;

import java.util.List;

public interface MovieService {
    void register(String serialNumber, String title);

    void rent(String serialNumber, String customer);

    void returnMovie(String serialNumber, String customer);

    List<MovieAvailability> getAllMovieAvailability();

    List<MovieHistory> getMovieHistory(String serialNumber);
}
