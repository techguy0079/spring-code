package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	 @Autowired
	 private Environment env;

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
    	System.out.println("Hello from Ratings Service "+ " MovieId ID:" +movieId + " : "+ env.getProperty("local.server.port"));
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
    	System.out.println("Hello from Ratings Service "+ " User ID:" +userId + " : "+ env.getProperty("local.server.port"));
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;

    }

}
