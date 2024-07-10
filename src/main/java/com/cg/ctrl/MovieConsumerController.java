package com.cg.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.Catlog;
import com.cg.dto.MovieInfo;
import com.cg.service.MovieConsumerService;

@RestController
@RequestMapping("/movConsumerCtrl")
public class MovieConsumerController 
{
	@Autowired
	MovieConsumerService movieConServ;

	//http://localhost:9092/movConsumerCtrl/findmovieinfobyname/abc
	@GetMapping("/findmovieinfobyname/{movname}")
	public MovieInfo getMovieInfoByName(@PathVariable("movname") String mnm)
	{
		MovieInfo  mInfo=movieConServ.getMovieInfoByName(mnm);
		return mInfo;
	}
	//---------------------------
	// http://localhost:9092/movConsumerCtrl/findAllMovInfo
		@GetMapping("/findAllMovInfo")
		public List<MovieInfo> findAllMovieInfo()
		{
			return  movieConServ.getAllMovieInfo();
		}
		
		//----------------------------
		//http://localhost:9092/movConsumerCtrl/addmoviefromclientapp/
		@PostMapping(value ="/addmoviefromclientapp/")		
		public String addMovieFromClientApp(@RequestBody  Catlog cat ) 
		{		
			
					return movieConServ.addMovie(cat);
		}
		
	//-----------------------
	// http://localhost:9092/movConsumerCtrl/findmovieinfo/1003
		 
		@RequestMapping("/findmovieinfo/{movid}")
		public List<MovieInfo> getAllMovieInfoById(
				@PathVariable("movid") int mid)
		{
			List<MovieInfo>  movList=movieConServ.getAllMovieInfoById(mid);
			return movList;
		}}


//----------------------------------------

