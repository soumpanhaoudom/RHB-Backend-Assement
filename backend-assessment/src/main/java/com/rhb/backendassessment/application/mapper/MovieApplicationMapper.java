package com.rhb.backendassessment.application.mapper;

import com.rhb.backendassessment.application.view.MovieView;
import com.rhb.backendassessment.model.MovieModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface MovieApplicationMapper {
    default MovieView from (MovieModel model) {
        return MovieView.of(
                model.getId(),
                model.getTitle(),
                model.getCategory().toString(),
                model.getStarRating(),
                model.getCreatedAt().toString(),
                model.getUpdatedAt().toString()
        );
    };

    default List<MovieView> from (List<MovieModel> models) {
        List<MovieView> movieViewList = new ArrayList<>();
        for (MovieModel model : models){
            MovieView view = MovieView.of(
                    model.getId(),
                    model.getTitle(),
                    model.getCategory().toString(),
                    model.getStarRating(),
                    model.getCreatedAt().toString(),
                    model.getUpdatedAt().toString()
            );
            movieViewList.add(view);
        }


        return movieViewList;
    };
}
