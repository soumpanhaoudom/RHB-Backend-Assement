package com.rhb.backendassessment.repository;

import com.rhb.backendassessment.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MovieRepository is used for manipulate MovieEntity
 * @author panhaoudom
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}