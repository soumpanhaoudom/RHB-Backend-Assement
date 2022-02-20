package com.rhb.backendassessment.application.controller;

import com.rhb.backendassessment._shared.constant.CodeResponse;
import com.rhb.backendassessment._shared.response.ApiResponse;
import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.application.mapper.MovieApplicationMapper;
import com.rhb.backendassessment.application.view.MovieView;
import com.rhb.backendassessment.model.MovieModel;
import com.rhb.backendassessment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieApplicationMapper movieApplicationMapper;

    @GetMapping
    public ResponseEntity<MovieView> list() {
        return null;
    }

    /**
     * get movie by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovieView>> get(@PathVariable("id") Long id) {
        MovieModel movieModel = this.movieService.get(id);
        MovieView view = this.movieApplicationMapper.from(movieModel);
        ApiResponse<MovieView> response = new ApiResponse<>(CodeResponse.SUCCESS, "", view);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * delete movie by ID
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.movieService.delete(id);
    }

    /**
     * create movie
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse<MovieView>> create(@RequestBody MovieCreateRequest request) {
        MovieModel movieModel = this.movieService.create(request);
        MovieView view = this.movieApplicationMapper.from(movieModel);
        ApiResponse<MovieView> response = new ApiResponse<>(CodeResponse.SUCCESS, "", view);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
