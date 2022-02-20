package com.rhb.backendassessment.application.form;

import com.rhb.backendassessment._shared.constant.movie.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class MovieUpdateRequest {
    private String title;
    private MovieCategory category;
    private double starRating;
}
