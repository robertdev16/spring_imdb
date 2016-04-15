package cs544.lab.spring_imdb.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.spring_imdb.model.Movie;


@Repository
@Transactional
@Cacheable
public interface IMovieDao extends JpaRepository<Movie, Integer> {
	
	List<Movie> findByTitle(String title);
	
	@Query("FROM Movie m WHERE m.title LIKE :title")
	List<Movie> findByTitleLike(@Param("title") String title);

}
