package wawer.kamil.moviecatalogservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import wawer.kamil.moviecatalogservice.model.CatalogItem;
import wawer.kamil.moviecatalogservice.model.Movie;
import wawer.kamil.moviecatalogservice.model.Rating;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final RestTemplate template;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") Long userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating(1L, 4), new Rating(3L, 3)
        );

        return ratings.stream()
                .map(rating -> {
                    Movie movie = template.getForObject("http://localhost:8081/movies/"+  rating.getId(), Movie.class);
                    return new CatalogItem(movie.getTitle(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());

    }

}
