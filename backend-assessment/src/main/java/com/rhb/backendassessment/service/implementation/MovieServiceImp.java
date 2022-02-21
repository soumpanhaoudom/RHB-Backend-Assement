package com.rhb.backendassessment.service.implementation;

import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.application.form.MovieUpdateRequest;
import com.rhb.backendassessment.model.MovieModel;
import com.rhb.backendassessment.model.entity.MovieEntity;
import com.rhb.backendassessment.repository.MovieRepository;
import com.rhb.backendassessment.repository.mapper.MovieRepositoryMapper;
import com.rhb.backendassessment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRepositoryMapper movieRepositoryMapper;

    @Override
    public List<MovieModel> list() {
        List<MovieModel> movieModels = this.movieRepositoryMapper.from(
                this.movieRepository.findAll()
        );
        return movieModels;
    }

    @Override
    public MovieModel create(MovieCreateRequest request) {
        MovieEntity entity = this.movieRepositoryMapper.from(request);

        MovieModel movieModel = this.movieRepositoryMapper.from(
                this.movieRepository.save(entity)
        );

        return movieModel;
    }

    @Override
    public MovieModel get(Long id) {
        MovieModel movieModel = this.movieRepositoryMapper.from(
                this.movieRepository.getById(id)
        );

        return movieModel;
    }

    @Override
    public void delete(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public MovieModel update(MovieUpdateRequest request, Long id) {
        return null;
    }
}
