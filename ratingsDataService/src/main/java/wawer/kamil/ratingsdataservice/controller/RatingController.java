package wawer.kamil.ratingsdataservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wawer.kamil.ratingsdataservice.model.Rating;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsData")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") Long movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/users/{userId}")
    public List<Rating> getRatings(@PathVariable("userId") Long userId) {
        return Arrays.asList(
                new Rating(userId, 4),
                new Rating(userId, 3)
        );
    }
}
