package com.rhb.backendassessment.repository.mapper;

import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.model.MovieModel;
import com.rhb.backendassessment.model.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface MovieRepositoryMapper {
    MovieEntity from(MovieCreateRequest request);

    MovieModel from(MovieEntity entity);
}
