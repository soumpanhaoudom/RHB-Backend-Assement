package com.rhb.backendassessment.application.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieView {
    private Long id;
    private String title;
    private String category;
    private Double rating;
    private String createdAt;
    private String updatedAt;
}
