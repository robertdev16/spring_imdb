package cs544.lab.spring_imdb.service;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cs544.lab.spring_imdb.dao.IActorDao;
import cs544.lab.spring_imdb.dao.IArtistDao;
import cs544.lab.spring_imdb.dao.IDirectorDao;
import cs544.lab.spring_imdb.dao.IMovieDao;
import cs544.lab.spring_imdb.dao.IUserDao;
import cs544.lab.spring_imdb.dao.IWriterDao;
import cs544.lab.spring_imdb.model.Actor;
import cs544.lab.spring_imdb.model.Director;
import cs544.lab.spring_imdb.model.Writer;


/**
 * Handles requests for the application home page.
 */
@Controller
public class MovieService {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	@Resource
	private IMovieDao movieDao;
	
	@Resource
	private IActorDao actorDao;
	
	@Resource
	private IDirectorDao directorDao;
	
	@Resource
	private IWriterDao writerDao;
	
	@Resource
	private IArtistDao artistDao;
	
	@Resource
	private IUserDao userDao;
	
	
	@RequestMapping("/")
	public String redirectRoot() {
		return "redirect:/movies";
	}

	@Transactional
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public String movieList(Model model) {
		logger.info("Get all movies:");

		model.addAttribute("movies", movieDao.findAll(new Sort(Direction.ASC, "title")));
		
		return "movieList";
	}
	
	@Transactional
	@RequestMapping(value = "/titleFilter", method = RequestMethod.POST)
	public String movieFilterByTitle(@RequestParam("titleSearch") String titleSearch, Model model) {
		if (titleSearch.isEmpty())
			return "redirect:/movies";
		
		logger.info("Get movies filtered by keyword: {}", titleSearch);

		model.addAttribute("movies", movieDao.findByTitleLike("%" + titleSearch + "%"));
		model.addAttribute("lastSearchTitle", titleSearch);
		
		return "movieList";
	}
	
	@Transactional
	@RequestMapping(value="/moviepic/{movieId}", method=RequestMethod.GET, produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getMoviePic(@PathVariable int movieId){
		return movieDao.findOne(movieId).getPoster();
	}
	
	@Transactional
	@RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
	public String movieDetail(@PathVariable int movieId, Model model) {
		logger.info("Get movie for movieId: {}", movieId);

		model.addAttribute("movie", movieDao.findOne(movieId));
		
		return "movieDetail";
	}
	
	@Transactional
	@RequestMapping(value = "/director/{artistId}", method = RequestMethod.GET)
	public String directorDetail(@PathVariable int artistId, Model model) {
		logger.info("Get director for artistId: {}", artistId);

		Director d = directorDao.findOne(artistId);
		model.addAttribute("director", d);
		model.addAttribute("picNum", d.getPhotoList().size()-1);
		
		return "directorDetail";
	}
	
	@Transactional
	@RequestMapping(value = "/writer/{artistId}", method = RequestMethod.GET)
	public String writerDetail(@PathVariable int artistId, Model model) {
		logger.info("Get writer for artistId: {}", artistId);

		Writer w = writerDao.findOne(artistId);
		model.addAttribute("writer", w);
		model.addAttribute("picNum", w.getPhotoList().size()-1);
		
		return "writerDetail";
	}
	
	@Transactional
	@RequestMapping(value = "/actor/{artistId}", method = RequestMethod.GET)
	public String actorDetail(@PathVariable int artistId, Model model) {
		logger.info("Get actor for artistId: {}", artistId);

		Actor a = actorDao.findOne(artistId);
		model.addAttribute("actor", a);
		model.addAttribute("picNum", a.getPhotoList().size()-1);
		
		return "actorDetail";
	}
	
	@Transactional
	@RequestMapping(value="/artistpic/{artistId}/{idx}", method=RequestMethod.GET, produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getArtistPic(@PathVariable("artistId") int artistId, @PathVariable("idx") int idx){
		return artistDao.findOne(artistId).getPhotoList().get(idx).getPhotoBytes();
	}
}
