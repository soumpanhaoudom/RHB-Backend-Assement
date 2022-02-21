package com.rhb.backendassessment.service;

import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.application.form.MovieUpdateRequest;
import com.rhb.backendassessment.model.MovieModel;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * MovieService is used for manipulate movie
 * @author panhaoudom
 */
public interface MovieService {

    /**
     * get list of movies
     * @param limit
     * @param offset
     * @return
     */
    List<MovieModel> list(Integer limit, Integer offset);
    /**
     * create movie
     * @param request
     * @return
     */
    MovieModel create(MovieCreateRequest request);

    /**
     * get movie by ID
     * @param id
     * @throws EntityNotFoundException
     * @return
     */
    MovieModel get(Long id) throws EntityNotFoundException;

    /**
     * delete movie by ID
     * @param id
     */
    void delete(Long id);

    /**
     * update movie by ID
     * @param request
     * @param id
     * @throws EntityNotFoundException
     * @return
     */
    MovieModel update(MovieUpdateRequest request, Long id) throws EntityNotFoundException;
}
