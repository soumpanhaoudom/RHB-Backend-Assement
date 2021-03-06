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

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRepositoryMapper movieRepositoryMapper;

    @Override
    public List<MovieModel> list(Integer limit, Integer offset) {
        List<MovieModel> movieModels = this.movieRepositoryMapper.from(
                this.movieRepository.findAllByPaging(limit, offset)
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
    public MovieModel get(Long id) throws EntityNotFoundException {
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
        MovieEntity entity = this.movieRepository.getById(id);

        MovieEntity modifiedEntity = this.movieRepositoryMapper.from(request, entity);

        MovieModel movieModel = this.movieRepositoryMapper.from(
                this.movieRepository.save(modifiedEntity)
        );

        return movieModel;
    }
}
