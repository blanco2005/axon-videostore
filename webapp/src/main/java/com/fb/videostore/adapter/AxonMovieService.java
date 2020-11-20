package com.fb.videostore.adapter;

import com.fb.api.AllMovieAvailabilityQuery;
import com.fb.api.RegisterMovieCommand;
import com.fb.api.RentMovieCommand;
import com.fb.movie.projections.MovieAvailability;
import com.fb.videostore.service.MovieService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
}
