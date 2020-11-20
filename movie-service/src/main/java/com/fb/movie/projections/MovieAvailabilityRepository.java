package com.fb.movie.projections;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieAvailabilityRepository extends JpaRepository<MovieAvailability, String> {
}
