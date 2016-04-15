package cs544.lab.spring_imdb.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.spring_imdb.model.Actor;


@Repository
@Transactional
@Cacheable
public interface IActorDao extends JpaRepository<Actor, Integer> {
	
	List<Actor> findByName(String name);

}
