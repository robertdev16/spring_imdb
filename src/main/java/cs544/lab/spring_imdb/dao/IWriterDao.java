package cs544.lab.spring_imdb.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.spring_imdb.model.Writer;


@Repository
@Transactional
@Cacheable
public interface IWriterDao extends JpaRepository<Writer, Integer> {
	
	List<Writer> findByName(String name);

}