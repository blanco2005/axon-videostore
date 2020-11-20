package com.fb.videostore.adapter;

import com.fb.api.RegisterMovieCommand;
import com.fb.api.RentMovieCommand;
import com.fb.videostore.service.MovieService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AxonMovieService implements MovieService {

    private final static Logger logger = LoggerFactory.getLogger(AxonMovieService.class);

    @Autowired
    private CommandGateway commandGateway;

    public void register(String serialNumber, String title) {
        logger.info("Sending register movie command...");
        commandGateway.send(new RegisterMovieCommand(serialNumber, title));
        logger.info("Register movie command sent");
    }

    public void rent(String serialNumber, String customer) {
        logger.info("Sending rent movie command...");
        commandGateway.send(new RentMovieCommand(serialNumber, customer));
        logger.info("Rent movie command sent");
    }
}
