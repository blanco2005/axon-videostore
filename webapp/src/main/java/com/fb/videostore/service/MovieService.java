package com.fb.videostore.service;

public interface MovieService {
    void register(String serialNumber, String title);

    void rent(String serialNumber, String customer);
}
