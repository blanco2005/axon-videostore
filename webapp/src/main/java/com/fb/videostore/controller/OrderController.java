package com.fb.videostore.controller;

import com.fb.videostore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/movies/{serialNumber}/rent")
    public ResponseEntity<String> rent(@PathVariable("serialNumber") String serialNumber,
                                       @RequestParam("customer") String customer) {

        orderService.createOrder(serialNumber, customer);
        return ResponseEntity.ok(format("Movie with sn %s rented to %s", serialNumber, customer));
    }
}
