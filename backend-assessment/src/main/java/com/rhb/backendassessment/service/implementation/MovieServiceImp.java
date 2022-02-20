package com.rhb.backendassessment.service.implementation;

import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.model.MovieModel;
import com.rhb.backendassessment.model.entity.MovieEntity;
import com.rhb.backendassessment.repository.MovieRepository;
import com.rhb.backendassessment.repository.mapper.MovieRepositoryMapper;
import com.rhb.backendassessment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRepositoryMapper movieRepositoryMapper;

    @Override
    public MovieModel create(MovieCreateRequest request) {
        MovieEntity entity = this.movieRepositoryMapper.from(request);

        System.out.println(entity.toString());
        MovieModel movieModel = this.movieRepositoryMapper.from(
                this.movieRepository.saveAndFlush(entity)
        );
        System.out.println(movieModel.toString());

        return movieModel;
    }
}
