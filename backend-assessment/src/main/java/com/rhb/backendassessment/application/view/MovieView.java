package com.rhb.backendassessment.application.view;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class MovieView {
    private Long id;
    private String title;
    private String category;
    private Double rating;
}
