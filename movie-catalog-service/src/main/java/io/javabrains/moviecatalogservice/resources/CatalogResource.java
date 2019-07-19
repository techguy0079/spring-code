package io.javabrains.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;
    
    @Autowired
    private Environment env;
   // @Autowired
  //  WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
   // @HystrixCommand(fallbackMethod="getFallbackCatalog") not required as below methods handle it
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
    	System.out.println("Hello from Catalog Service "+ " User ID:" +userId + " : "+ env.getProperty("local.server.port"));
    	//return Collections.singletonList(new CatalogItem("Transformers", "Science Fiction movie", 4));
    	//restTemplate = new RestTemplate();
    	// UserRating userRating = restTemplate.getForObject("http://localhost:8087/ratingsdata/user/" + userId, UserRating.class);
    	//UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
    	 UserRating userRating = userRatingInfo.getUserRating(userId);
         
    	//List<Rating> ratingsList  = Arrays.asList(new Rating("003",5),new Rating("004",4),new Rating("006",3));
    	return userRating.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating)//{
    												
    												//using direct urls without Eureka
    												//Movie movie = restTemplate.getForObject("http://localhost:8086/movies/" + rating.getMovieId(), Movie.class);
    												//using Euerka service discovery
    												//Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
    												//Using Reactive programming
    												//Movie movie = webClientBuilder.build().get().uri("http://localhost:8086/movies/"+ rating.getMovieId())
    												//		.retrieve().bodyToMono(Movie.class).block();
    											//	return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
													//return getCatalogItem(rating);
    											//  }
    											).collect(Collectors.toList());
    	/*
    	//For each movie id, call movie info service
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
        
        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());
*/
    }
    
   /* @HystrixCommand(fallbackMethod="getFallbackCatalogItem")
	private CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
    
    private CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie Name not Found", "", rating.getRating());
	}*/

/*    @HystrixCommand(fallbackMethod="getFallbackUserRating")
	private UserRating getUserRating(@PathVariable("userId")String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
	}
    
    private UserRating getFallbackUserRating(@PathVariable("userId")String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setRatings(Arrays.asList(new Rating("0",0)));
    	return userRating;
	}
*/    
	
    
   /* public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
     return Arrays.asList(new CatalogItem("No movie", "", 0));
    }*/
}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/