package com.rhb.backendassessment.application.controller;

import com.rhb.backendassessment._shared.constant.CodeResponse;
import com.rhb.backendassessment._shared.response.ApiPageResponse;
import com.rhb.backendassessment._shared.response.ApiResponse;
import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.application.form.MovieUpdateRequest;
import com.rhb.backendassessment.application.mapper.MovieApplicationMapper;
import com.rhb.backendassessment.application.view.MovieView;
import com.rhb.backendassessment.model.MovieModel;
import com.rhb.backendassessment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MovieController is used for CRUD movie
 * @author panhoudom
 */
@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieApplicationMapper movieApplicationMapper;

    @GetMapping
    public ResponseEntity<ApiPageResponse<MovieView>> list(@RequestParam(value = "limit", defaultValue = "20") Integer limit,
                                                           @RequestParam(value = "offset", defaultValue = "0") Integer offset) {
        List<MovieModel> movieModels = this.movieService.list(limit, offset);
        List<MovieView> viewList = this.movieApplicationMapper.from(movieModels);
        ApiPageResponse<MovieView> response = new ApiPageResponse<>(CodeResponse.SUCCESS, "", viewList,viewList.size(),limit,offset);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * delete movie by ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MovieView>> delete(@PathVariable("id") Long id) {
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
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

    /**
     * update movie by ID
     * @param request
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<MovieView>> update(@RequestBody MovieUpdateRequest request, @PathVariable("id") Long id) {
        MovieModel movieModel = this.movieService.update(request, id);
        MovieView view = this.movieApplicationMapper.from(movieModel);
        ApiResponse<MovieView> response = new ApiResponse<>(CodeResponse.SUCCESS, "", view);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
