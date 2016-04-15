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
import cs544.lab.spring_imdb.dao.IMovieDao;
import cs544.lab.spring_imdb.dao.IUserDao;


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
	private IUserDao userDao;
	
	
	@RequestMapping("/")
	public String redirectRoot() {
		return "redirect:/movies";
	}

	@Transactional
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public String getAll(Model model) {
		logger.info("Get all movies:");

		model.addAttribute("movies", movieDao.findAll(new Sort(Direction.ASC, "title")));
		
		return "movieList";
	}
	
	@Transactional
	@RequestMapping(value = "/titleFilter", method = RequestMethod.GET)
	public String filterByTitle(@RequestParam("titleSearch") String titleSearch, Model model) {
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
	
}
