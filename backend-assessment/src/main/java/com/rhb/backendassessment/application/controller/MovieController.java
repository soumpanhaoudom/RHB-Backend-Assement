package com.rhb.backendassessment.application.controller;

import com.rhb.backendassessment.application.view.MovieView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {
    @GetMapping
    ResponseEntity<MovieView> list() {
        return null;
    }
}
