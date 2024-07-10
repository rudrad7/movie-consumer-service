package com.cg.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.cg.dto.Catlog;
import com.cg.dto.MovieInfo;
import com.cg.dto.Rating;
@Service
public class MovieConsumerService 
{	
	@Autowired	(required = false)
    private CircuitBreakerFactory circuitBreakerFactory;
	@Autowired	
	RestTemplate restTemplate;	
	//-----Fech  MovieInfo m By name
	public MovieInfo getMovieInfoByName(
			@PathVariable("movName")String mnm)
	{
		
		 CircuitBreaker circuitBreaker =
				 circuitBreakerFactory.create("circuitbreaker");
		Catlog cat=restTemplate.getForObject
			//	("http://movie-catalog-service/movCatCtrl/fetchMovieCatByName/"+mnm, Catlog.class);
				("http://localhost:8082/movCatCtrl/fetchMovieCatByName/"+mnm, Catlog.class);
		 //System.out.println(" id......."+cat.getMovieId());
		 /*Rating rat=restTemplate.getForObject(
				"http://movie-rating-service/movRatCtrl/fetchMovieById/"+
						cat.getMovieId(),Rating.class);
						
				 MovieInfo movieInfo=new MovieInfo(cat.getMovieId(),
						cat.getMovieName(),rat.getMovieRating());
						return 	 movieInfo;	
						
		*/
		 if(cat!=null)
		 {
			 return circuitBreaker.run(() ->{ 
				 Rating rat= restTemplate.
					 getForObject(
							// "http://movie-rating-service/movRatCtrl/fetchMovieById/"+
							 "http://localhost:8083/movRatCtrl/fetchMovieById/"+
								cat.getMovieId(),Rating.class);
				 MovieInfo movieInfo=new MovieInfo(cat.getMovieId(),
							cat.getMovieName(),rat.getMovieRating());
				 return movieInfo;
						 }, 
				     throwable -> new MovieInfo(cat.getMovieId(),
				    		 cat.getMovieName(),0));	
		 
		 }
		
				return  new MovieInfo(0,"Movie Not Present",0);
		 
		
	}
	//------------------------------------------
	public List<MovieInfo> getAllMovieInfo()
	{
		System.out.println("inside consumer controlller....");		

		ArrayList<MovieInfo> movieList=new ArrayList<MovieInfo>();
		ResponseEntity<Set<Catlog>> responseCat =	
				restTemplate.exchange(
						"http://localhost:8082/movCatCtrl/fetchAllMovieCat",
						//"http://movie-catalog-service/movCatCtrl/fetchAllMovieCat",
						HttpMethod.GET, 
						null,new ParameterizedTypeReference<Set<Catlog>>(){});

		Set<Catlog> catSet = responseCat.getBody();
		System.out.println("...got.... "+	(HashSet<Catlog>)catSet);		

		for(Catlog cg:catSet)
		{
			Rating movRating=restTemplate.getForObject(
					"http://localhost:8083/movRatCtrl/fetchMovieById/"+cg.getMovieId(),
				//"http://movie-rating-service/movRatCtrl/fetchMovieById/"+cg.getMovieId(),
					Rating.class);
			MovieInfo mi=new MovieInfo(cg.getMovieId(),cg.getMovieName(),
					movRating.getMovieRating());
			movieList.add(mi);
		}

		return movieList;
	}

	//------------------------------------------------
	public String addMovie(Catlog cat) 
	{		
	HashSet<Catlog> result = restTemplate.
				postForObject(
			//"http://movie-catalog-service/movCatCtrl/addmoviefrmpostman/",
     "http://localhost:8082/movCatCtrl/addmoviefrmpostman/",
						cat,HashSet.class);

	System.out.println("  mov added ......"+result); 
	return " mov added............."+result;

	}
	//--------------------------------
	public List<MovieInfo> getAllMovieInfoById(	int mid)
	{
		System.out.println("inside consumer controlller.getAllMovieInfoById...");
		Rating movRating=restTemplate.
				getForObject(
						"http://localhost:8083/movRatCtrl/fetchMovieById/"+
						//"http://movie-rating-service/movRatCtrl/fetchMovieById/"+
				mid,
						Rating.class);
		System.out.println("  consumer ctrl....."+movRating);

		Catlog cat=restTemplate.getForObject(
				//"http://movie-catalog-service/movCatCtrl/fetchMovieCatById/"+mid,
				"http://localhost:8082/movCatCtrl/fetchMovieCatById/"+mid,
				Catlog.class);

		System.out.println("  consumer ctrl....."+cat);
		MovieInfo m1=new MovieInfo(mid,cat.getMovieName(),	movRating.getMovieRating());
		ArrayList<MovieInfo> movList=new ArrayList<MovieInfo>();
		movList.add(m1);
		return movList;
	}
}







