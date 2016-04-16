package cs544.lab.spring_imdb.dao;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.spring_imdb.model.Artist;


@Repository
@Transactional
@Cacheable
public interface IArtistDao extends JpaRepository<Artist, Integer> {

}