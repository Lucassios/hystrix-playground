package com.lucasmarques.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by lucascmarques on 07/05/17.
 */
@Service
public class BookService {

    private final RestTemplate restTemplate;

    public BookService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String readingList() {
        URI uri = URI.create("http://localhost:8090/recommended");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }

}
