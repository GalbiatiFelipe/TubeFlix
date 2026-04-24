package com.tubetv.repository;

import com.tubetv.entity.Category;
import com.tubetv.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List findMovieByCategoriesId(Long id);

}
