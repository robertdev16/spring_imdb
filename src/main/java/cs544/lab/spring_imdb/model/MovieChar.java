package cs544.lab.spring_imdb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MovieChar {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int charId;

	private String name;
	
	@ManyToOne
	@JoinColumn(name="movieId")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name="artistId")
	private Actor actor;


	public MovieChar() {
		super();
	}

	public MovieChar(String name, Movie movie, Actor actor) {
		super();
		this.name = name;
		setMovie(movie);
		setActor(actor);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
		movie.addMovieChar(this);
	}


	public Actor getActor() {
		return actor;
	}


	public void setActor(Actor actor) {
		this.actor = actor;
		actor.addMovieChar(this);
	}


	public int getCharId() {
		return charId;
	}

}
