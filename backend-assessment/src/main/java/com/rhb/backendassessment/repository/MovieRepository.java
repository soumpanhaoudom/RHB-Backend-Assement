package com.rhb.backendassessment.repository;

import com.rhb.backendassessment.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MovieRepository is used for manipulate MovieEntity
 * @author panhaoudom
 */
@Repository
public interface
MovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query(value = "select * from movie limit :limit offset :offset", nativeQuery = true)
    List<MovieEntity> findAllByPaging(Integer limit, Integer offset);

    @Query(value = "delete from movie where id = :id", nativeQuery = true)
    @Modifying
    void deleteById(Long id);
}
