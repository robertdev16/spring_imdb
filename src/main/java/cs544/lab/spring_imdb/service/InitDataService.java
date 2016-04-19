package cs544.lab.spring_imdb.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.spring_imdb.dao.IActorDao;
import cs544.lab.spring_imdb.dao.IMovieDao;
import cs544.lab.spring_imdb.dao.IUserDao;
import cs544.lab.spring_imdb.model.Actor;
import cs544.lab.spring_imdb.model.ArtistPhoto;
import cs544.lab.spring_imdb.model.Comment;
import cs544.lab.spring_imdb.model.Director;
import cs544.lab.spring_imdb.model.Genre;
import cs544.lab.spring_imdb.model.Movie;
import cs544.lab.spring_imdb.model.MovieChar;
import cs544.lab.spring_imdb.model.User;
import cs544.lab.spring_imdb.model.UserRole;
import cs544.lab.spring_imdb.model.Writer;



@Service
public class InitDataService {
	private static final String READ_IMG_PATH = "img/";
	private static final Logger logger = LoggerFactory.getLogger(InitDataService.class);

	@Resource
	private IMovieDao movieDao;
	
	@Resource
	private IActorDao actorDao;
	
	@Resource
	private IUserDao userDao;
	
	
	@PostConstruct
	@Transactional
	public void init(){
		
		logger.info("Initialize data...");
		
		User userMike = new User("Mike", "123456");
		userMike.getRoleSet().add(UserRole.ROLE_USER);
		User userJack = new User("Jack", "123456");
		userJack.getRoleSet().add(UserRole.ROLE_USER);
		User userLucy = new User("Lucy", "123456");
		userLucy.getRoleSet().add(UserRole.ROLE_USER);
		
		Actor actorAngelina = new Actor("Angelina Jolie", "06/04/1975", "Los Angeles, California, USA");
		actorAngelina.setBiography("Angelina Jolie is an Oscar-winning actress who became popular after playing the title role in the 'Lara Croft' blockbuster movies, as well as Mr. & Mrs. Smith (2005), Wanted (2008), Salt (2010) and Maleficent (2014).");
		actorAngelina.getPhotoList().add(new ArtistPhoto(readPic("Angelina Jolie_01.jpg")));
		actorAngelina.getPhotoList().add(new ArtistPhoto(readPic("Angelina Jolie_02.jpg")));
		actorAngelina.getPhotoList().add(new ArtistPhoto(readPic("Angelina Jolie_03.jpg")));
		
		Actor actorBrad = new Actor("Brad Pitt", "12/18/1963", "Shawnee, Oklahoma, USA");
		actorBrad.setBiography("An actor and producer known as much for his versatility as he is for his handsome face, Golden Globe-winner Brad Pitt's most widely recognized role may be Tyler Durden in Fight Club (1999).");
		actorBrad.getPhotoList().add(new ArtistPhoto(readPic("Brad Pitt_01.jpg")));
		actorBrad.getPhotoList().add(new ArtistPhoto(readPic("Brad Pitt_02.jpg")));

		Director directorDoug = new Director("Doug Liman", "07/24/1965", "New York City, New York, USA");
		directorDoug.setBiography("Doug Liman was born on July 24, 1965 in New York City, New York, USA. He is a producer and director, known for Edge of Tomorrow (2014), The Bourne Identity (2002) and The Bourne Ultimatum (2007).");
		directorDoug.getPhotoList().add(new ArtistPhoto(readPic("Doug Liman.jpg")));
		
		Writer writerSimon = new Writer("Simon Kinberg", "08/02/1973", "London, England, UK");
		writerSimon.setBiography("Simon Kinberg was born on August 2, 1973 in London, England. He is a producer and writer, known for X-Men: Days of Future Past (2014), Sherlock Holmes (2009) and X-Men: The Last Stand (2006).");
		writerSimon.getPhotoList().add(new ArtistPhoto(readPic("Simon Kinberg.jpg")));
		
		Movie movieSmith = new Movie("Mr. & Mrs. Smith", 2005, 120, 6.5f);
		movieSmith.setBrief("A bored married couple is surprised to learn that they are both assassins hired by competing agencies to kill each other.");
		movieSmith.setPoster(readPic("Mr. & Mrs. Smith.jpg"));
		movieSmith.getGenreSet().add(Genre.Action);
		movieSmith.getGenreSet().add(Genre.Comedy);
		movieSmith.getGenreSet().add(Genre.Crime);
		movieSmith.getGenreSet().add(Genre.Romance);
		movieSmith.getGenreSet().add(Genre.Thriller);
		movieSmith.addDirector(directorDoug);
		movieSmith.addWriter(writerSimon);
		new MovieChar("Jane Smith", movieSmith, actorAngelina);
		new MovieChar("John Smith", movieSmith, actorBrad);
		new Comment(userMike, movieSmith, "09/25/2006", "11:35 am", 7.0f, "Nice Surprise!", "I saw an advance screening of this movie tonight, and I must say it far exceeded my expectations...");
		new Comment(userJack, movieSmith, "11/03/2006", "5:22 pm", 6.0f, "Complete Hollywood Rubbish", "Cheesy story, boring as-if action that's been done a million times, what else can I say, just lame Hollywood garbage at its finest...");

		Actor actorJames = new Actor("James McAvoy", "04/21/1979", "Port Glasgow, Scotland, UK");
		actorJames.setBiography("McAvoy was born on 21 April 1979 in Glasgow, Scotland, to Elizabeth, a nurse, and James McAvoy senior, a bus driver.");
		actorJames.getPhotoList().add(new ArtistPhoto(readPic("James McAvoy_01.jpg")));
		actorJames.getPhotoList().add(new ArtistPhoto(readPic("James McAvoy_02.jpg")));

		Actor actorMorgan = new Actor("Morgan Freeman", "06/01/1937", "Memphis, Tennessee, USA");
		actorMorgan.setBiography("With an authoritative voice and calm demeanor, this ever popular American actor has grown into one of the most respected figures in modern US cinema.");
		actorMorgan.getPhotoList().add(new ArtistPhoto(readPic("Morgan Freeman_01.jpg")));
		actorMorgan.getPhotoList().add(new ArtistPhoto(readPic("Morgan Freeman_02.jpg")));
		
		Director directorTimur = new Director("Timur Bekmambetov", "06/25/1961", "Guryev, Kazakh SSR, USSR");
		directorTimur.setBiography("Timur Bekmambetov is a Russian-Kazakh film director known for vampire franchise Night Watch (2004) and Day Watch (2006).");
		directorTimur.getPhotoList().add(new ArtistPhoto(readPic("Timur Bekmambetov.jpg")));

		Writer writerJones = new Writer("J.G. Jones", "01/01/1901", "Unknown");
		writerJones.setBiography("J.G. Jones is known for his work on Wanted (2008), Fox (2016) and Grant Morrison: Talking with Gods (2010).");
		writerJones.getPhotoList().add(new ArtistPhoto(readPic("J.G. Jones.jpg")));
		
		Movie movieWanted = new Movie("Wanted", 2008, 110, 6.7f);
		movieWanted.setBrief("A frustrated office worker learns that he is the son of a professional assassin, and that he shares his father's superhuman killing abilities.");
		movieWanted.setPoster(readPic("Wanted.jpg"));
		movieWanted.getGenreSet().add(Genre.Action);
		movieWanted.getGenreSet().add(Genre.Crime);
		movieWanted.getGenreSet().add(Genre.Fantasy);
		movieWanted.getGenreSet().add(Genre.Thriller);
		movieWanted.addDirector(directorTimur);
		movieWanted.addWriter(writerJones);
		new MovieChar("Wesley", movieWanted, actorJames);
		new MovieChar("Fox", movieWanted, actorAngelina);
		new MovieChar("Sloan", movieWanted, actorMorgan);
		new Comment(userMike, movieWanted, "06/20/2008", "10:08 am", 6.0f, "To The Extreme", "I just got back from an advanced screening of Wanted. I have to admit not being a fan of the Director, Tim Bekmambetov. Somewhat disappointed in his Russian Films Nightwatch and Daywatch...");
		new Comment(userJack, movieWanted, "06/18/2008", "7:51 pm", 6.8f, "The Matrix with a great sense of humor", "I am a huge fan of Mark Millar's Wanted. And so, when I entered the theater with a friend on an advance screening pass, fear gripped me...");
		new Comment(userLucy, movieWanted, "01/03/2009", "9:15 pm", 7.3f, "What's wrong with those comments?", "I guess this is my first moment I want to comment on the movie. The reason for that is all those negative comments...");
		
		Director directorMarc = new Director("Marc Forster", "11/30/1969", "Illertissen, Bavaria, Germany");
		directorMarc.setBiography("Marc Forster was born on November 30, 1969 in Illertissen, Bavaria, Germany. He is a producer and director, known for World War Z (2013), Quantum of Solace (2008) and Finding Neverland (2004).");
		directorMarc.getPhotoList().add(new ArtistPhoto(readPic("Marc Forster.jpg")));
		
		Writer writerMatthew = new Writer("Matthew Michael Carnahan", "01/01/1901", "Unknown");
		writerMatthew.setBiography("Matthew Michael Carnahan is a writer and producer, known for World War Z (2013), State of Play (2009) and The Kingdom (2007).");
		writerMatthew.getPhotoList().add(new ArtistPhoto(readPic("Matthew Michael Carnahan.jpg")));

		Writer writerDrew = new Writer("Drew Goddard", "02/26/1975", "Los Alamos, New Mexico, USA");
		writerDrew.setBiography("Drew Goddard was raised in Los Alamos, New Mexico. He attended Los Alamos High School in Los Alamos, New Mexico and graduated in 1993. He then attended the University of Colorado, and worked as a production assistant in L.A. after graduation.");
		writerDrew.getPhotoList().add(new ArtistPhoto(readPic("Drew Goddard.jpg")));
		
		Actor actorMireille = new Actor("Mireille Enos", "09/22/1975", "Houston, Texas, USA");
		actorMireille.setBiography("Mireille Enos was born in Kansas City, Missouri. She attended The High School for the Performing and Visual Arts. She then went to Brigham Young University.");
		actorMireille.getPhotoList().add(new ArtistPhoto(readPic("Mireille Enos.jpg")));
		
		Movie movieWarZ = new Movie("World War Z", 2013, 116, 7.0f);
		movieWarZ.setBrief("Former United Nations employee Gerry Lane traverses the world in a race against time to stop the Zombie pandemic that is toppling armies and governments, and threatening to destroy humanity itself.");
		movieWarZ.setPoster(readPic("World War Z.jpg"));
		movieWarZ.getGenreSet().add(Genre.Action);
		movieWarZ.getGenreSet().add(Genre.Adventure);
		movieWarZ.getGenreSet().add(Genre.Horror);
		movieWarZ.getGenreSet().add(Genre.SciFi);
		movieWarZ.getGenreSet().add(Genre.Thriller);
		movieWarZ.addDirector(directorMarc);
		movieWarZ.addWriter(writerMatthew);
		movieWarZ.addWriter(writerDrew);
		new MovieChar("Gerry Lane", movieWarZ, actorBrad);
		new MovieChar("Karin Lane", movieWarZ, actorMireille);
		new Comment(userLucy, movieWarZ, "01/03/2009", "9:15 pm", 7.3f, "Fast, scary and much better than expected!", "I was dubious! The rating, the early reviews, my love of gory George Romero movies had all led me to deciding not to see this. But, my wife wanted to go... Well, two breathless hours later I turned to her...");

		logger.info("Persist entities...");
		movieDao.save(movieSmith);
		movieDao.save(movieWanted);
		movieDao.save(movieWarZ);
		actorDao.save(actorAngelina);
		actorDao.save(actorBrad);
		actorDao.save(actorJames);
		actorDao.save(actorMorgan);
		actorDao.save(actorMireille);
		userDao.save(userMike);
		userDao.save(userJack);
		userDao.save(userLucy);
		
	}

	
	private byte[] readPic(String filename) {
		InputStream input = null;
		try {
			input = (new ClassPathResource(READ_IMG_PATH + filename)).getInputStream();
		} catch (IOException e1) {
			logger.error("readPic ERROR: filename: {}", filename);
		}
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[4096];
	    int n;

	    try {
			while ((n=input.read(buffer))>0) {
			    output.write(buffer, 0, n);
			}
		} catch (IOException e) {
			logger.error("readPic ERROR: filename: {}", filename);
		}
	    return output.toByteArray();
	}

}
