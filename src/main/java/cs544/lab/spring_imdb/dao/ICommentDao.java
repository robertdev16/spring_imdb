package cs544.lab.spring_imdb.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.spring_imdb.model.Comment;



@Repository
@Transactional
@Cacheable
public interface ICommentDao extends JpaRepository<Comment, Integer> {
	
	@Query("FROM Comment c WHERE c.title LIKE :title")
	List<Comment> findByTitleLike(@Param("title") String title);

}