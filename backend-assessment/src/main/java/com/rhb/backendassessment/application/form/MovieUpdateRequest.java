package com.rhb.backendassessment.application.form;

import com.rhb.backendassessment._shared.constant.movie.MovieCategory;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class MovieUpdateRequest {

    @NonNull
    private Optional<String> title;

    @NonNull
    private Optional<MovieCategory> category;

    @NonNull
    private Optional<Double> starRating;
}
