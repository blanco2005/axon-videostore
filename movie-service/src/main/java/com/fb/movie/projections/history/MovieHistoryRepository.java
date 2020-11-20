package com.fb.movie.projections.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieHistoryRepository extends JpaRepository<MovieHistory, Long> {

    List<MovieHistory> findBySerialNumber(String serialNumber);

}
