package com.rhb.backendassessment.application.form;

import com.rhb.backendassessment._shared.annotation.validation.startrating.StarRatingValidation;
import com.rhb.backendassessment._shared.annotation.validation.title.TitleValidation;
import com.rhb.backendassessment._shared.constant.movie.MovieCategory;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class MovieUpdateRequest {

    @NonNull
    @TitleValidation(message = "invalid title")
    private Optional<String> title;

    @NonNull
    private Optional<MovieCategory> category;

    @NonNull
    @StarRatingValidation(message = "invalid starRating")
    private Optional<Double> starRating;
}
