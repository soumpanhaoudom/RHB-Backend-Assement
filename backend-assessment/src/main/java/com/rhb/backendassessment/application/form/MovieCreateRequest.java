package com.rhb.backendassessment.application.form;

import com.rhb.backendassessment._shared.constant.movie.MovieCategory;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class MovieCreateRequest {

    @NonNull
    @NotNull
    @Length(min = 1, max = 128)
    private String title;

    @NonNull
    @NotNull
    private MovieCategory category;

    @NonNull
    @NotNull
    @DecimalMin(value = "0.5", message = "minimum starRating is 0.5")
    @DecimalMax(value = "5.0", message = "maximum starRating is 5.0")
    @Digits(integer=1, fraction=1)
    private double starRating;
}
