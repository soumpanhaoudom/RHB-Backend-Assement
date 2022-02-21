package com.rhb.backendassessment.repository.mapper;

import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.application.form.MovieUpdateRequest;
import com.rhb.backendassessment.model.MovieModel;
import com.rhb.backendassessment.model.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface MovieRepositoryMapper {
    /**
     * Map MovieCreateRequest form to MovieEntity
     * @param request
     * @return
     */
    MovieEntity from(MovieCreateRequest request);

    /**
     * Map MovieEntity to MovieModel
     * @param entity
     * @return
     */
    MovieModel from(MovieEntity entity);

    /**
     * Map List<MovieEntity> to List<MovieModel>
     * @param entity
     * @return
     */
    List<MovieModel> from(List<MovieEntity> entity);

    /**
     * Map MovieUpdateRequest and MovieEntity to MovieEntity
     * @param request
     * @param entity
     * @return
     */
    default MovieEntity from(MovieUpdateRequest request, MovieEntity entity){
        if (request.getTitle().isPresent() && request.getTitle() != null) {
            entity.setTitle(request.getTitle().get());
        }

        if (request.getCategory().isPresent() && request.getCategory() != null) {
            entity.setCategory(request.getCategory().get());
        }

        if (request.getStarRating().isPresent() && request.getStarRating() != null) {
            entity.setStarRating(request.getStarRating().get());
        }

        return entity;
    };
}
