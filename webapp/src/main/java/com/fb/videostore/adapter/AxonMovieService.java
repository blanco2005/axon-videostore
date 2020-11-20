package com.fb.videostore.adapter;

import com.fb.api.*;
import com.fb.movie.projections.availability.MovieAvailability;
import com.fb.movie.projections.history.MovieHistory;
import com.fb.videostore.service.MovieService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AxonMovieService implements MovieService {

  private static final Logger logger = LoggerFactory.getLogger(AxonMovieService.class);

  @Autowired private CommandGateway commandGateway;

  @Autowired private QueryGateway queryGateway;

  public void register(String serialNumber, String title) {
    logger.info("Sending register movie command...");
    commandGateway.send(new RegisterMovieCommand(serialNumber, title));
    logger.info("Register movie command sent");
  }

  public void rent(String serialNumber, String customer) {
    logger.info("Sending rent movie command...");
    try {
      commandGateway.send(new RentMovieCommand(serialNumber, customer)).get();
    } catch (Exception e) {
      throw new RuntimeException("");
    }

    logger.info("Rent movie command sent");
  }

  @Override
  public void returnMovie(String serialNumber, String customer) {
    logger.info("Sending return movie command...");
    commandGateway.send(new ReturnMovieCommand(serialNumber, customer));
    logger.info("Return movie command sent");
  }

  public List<MovieAvailability> getAllMovieAvailability() {
    CompletableFuture<List<MovieAvailability>> query =
        queryGateway.query(
            new AllMovieAvailabilityQuery(),
            ResponseTypes.multipleInstancesOf(MovieAvailability.class));
    try {
      return query.get();
    } catch (Exception e) {
      throw new RuntimeException("Future problem");
    }
  }

  public List<MovieHistory> getMovieHistory(String serialNumber) {
    CompletableFuture<List<MovieHistory>> query =
            queryGateway.query(
                    new MovieHistoryQuery(serialNumber),
                    ResponseTypes.multipleInstancesOf(MovieHistory.class));
    try {
      return query.get();
    } catch (Exception e) {
      throw new RuntimeException("Future problem");
    }
  }
}
