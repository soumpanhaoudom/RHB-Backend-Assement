package com.rhb.backendassessment.service;

import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.model.MovieModel;

public interface MovieService {
    MovieModel create(MovieCreateRequest request);
}
