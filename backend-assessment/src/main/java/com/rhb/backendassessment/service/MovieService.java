package com.rhb.backendassessment.service;

import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.application.form.MovieUpdateRequest;
import com.rhb.backendassessment.model.MovieModel;

/**
 * MovieService is used for manipulate movie
 * @author panhaoudom
 */
public interface MovieService {

    /**
     * create movie
     * @param request
     * @return
     */
    MovieModel create(MovieCreateRequest request);

    /**
     * get movie by ID
     * @param id
     * @return
     */
    MovieModel get(Long id);

    /**
     * delete movie by ID
     * @param id
     */
    void delete(Long id);

    /**
     * update movie by ID
     * @param request
     * @param id
     * @return
     */
    MovieModel update(MovieUpdateRequest request, Long id);
}
