package com.fb.movie.projections.availability;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieAvailabilityRepository extends JpaRepository<MovieAvailability, String> {
}
