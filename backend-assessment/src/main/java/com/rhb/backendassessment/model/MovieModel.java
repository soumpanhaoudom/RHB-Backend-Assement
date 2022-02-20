package com.rhb.backendassessment.model;

import com.rhb.backendassessment._shared.constant.movie.MovieCategory;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
public class MovieModel {
    @NonNull
    @NotNull
    private Long id;

    @NonNull
    @NotNull
    private String title;

    @NonNull
    @NotNull
    private MovieCategory category;

    @NonNull
    @NotNull
    private double starRating;

    @NonNull
    @NotNull
    private LocalDateTime createdAt;

    @NonNull
    @NotNull
    private LocalDateTime updatedAt;
}
