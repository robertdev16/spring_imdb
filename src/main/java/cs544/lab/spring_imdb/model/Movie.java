package cs544.lab.spring_imdb.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int movieId;

	private String title;
	private int year;
	private int runtime;
	private String brief;
	private float rating;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<Genre> genreSet = new HashSet<Genre>();

	@Lob
	private byte[] poster;

	@ManyToMany(mappedBy="movieList", cascade=CascadeType.ALL)
	private List<Director> directorList = new ArrayList<Director>();
	
	@ManyToMany(mappedBy="movieList", cascade=CascadeType.ALL)
	private List<Writer> writerList = new ArrayList<Writer>();
	
	@OneToMany(mappedBy="movie")
	private List<MovieChar> movieCharList = new ArrayList<MovieChar>();
		
	@OneToMany(mappedBy="movie")
	private List<Comment> commentList = new ArrayList<Comment>();
	
	
	public Movie() {
		super();
	}

	public Movie(String title, int year, int runtime, float rating) {
		super();
		this.title = title;
		this.year = year;
		this.runtime = runtime;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Set<Genre> getGenreSet() {
		return genreSet;
	}

	public void setGenreSet(Set<Genre> genreSet) {
		this.genreSet = genreSet;
	}

	public byte[] getPoster() {
		return poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}

	public int getMovieId() {
		return movieId;
	}

	public void addDirector(Director d){
		directorList.add(d);
		d.addMovie(this);
	}

	public void removeDirector(Director d){
		d.removeMovie(this);
		directorList.remove(d);
	}
	
	public void addWriter(Writer w){
		writerList.add(w);
		w.addMovie(this);
	}

	public void removeWriter(Writer w){
		w.removeMovie(this);
		writerList.remove(w);
	}
	
	public void addMovieChar(MovieChar mc){
		movieCharList.add(mc);
	}

	public void removeMovieChar(MovieChar mc){
		movieCharList.remove(mc);
	}
	
	public void addComment(Comment c){
		commentList.add(c);
	}

	public void removeComment(Comment c){
		commentList.remove(c);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n\nmovieId: " + movieId + "   ==================================================================");
		sb.append("==================================================================\n");
		sb.append(title + " (" + year + ")\n");
		sb.append("Genre: ");
		for (Genre genre : genreSet){
			sb.append(genre + " | ");
		}
		sb.deleteCharAt(sb.length() - 2);
		sb.append("\nRuntime: " + runtime + "\n");
		sb.append("Rating: " + rating + "\n");
		sb.append("Poster: " + title + ".jpg\n");
		sb.append("Brief: " + brief + "\n");

		sb.append("Director:\n");
		for (Director d : directorList){
			sb.append(d.getName() + ", " + d.getBirthDate() + ", " + d.getBirthPlace() + "\n");
		}

		sb.append("Writer:\n");
		for (Writer w : writerList){
			sb.append(w.getName() + ", " + w.getBirthDate() + ", " + w.getBirthPlace() + "\n");
		}

		sb.append("Cast:\n");
		int num = movieCharList.size();
		MovieChar mc;
		for (int i=0; i<num; i++){
			mc = movieCharList.get(i);
			sb.append("Actor: " + mc.getActor().getName());
			sb.append("  -  Character: " + mc.getName() + "\n");
		}

		sb.append("Comment:\n");
		commentList.forEach(sb::append);
		
		return sb.toString();
	}

}
