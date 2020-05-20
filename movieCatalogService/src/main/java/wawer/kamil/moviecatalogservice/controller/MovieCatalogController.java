package wawer.kamil.moviecatalogservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import wawer.kamil.moviecatalogservice.model.CatalogItem;
import wawer.kamil.moviecatalogservice.model.Movie;
import wawer.kamil.moviecatalogservice.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final WebClient.Builder builder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") Long userId) {

        List<Rating> ratings = builder.build()
                .get()
                .uri("http://localhost:8082/ratingsData/users/"+ userId)
                .retrieve()
                .bodyToFlux(Rating.class)
                .collectList()
                .block();

        return ratings.stream()
                .map(rating -> {
                   Movie movie = builder.build()
                            .get()
                            .uri("http://localhost:8081/movies/"+  rating.getId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    return new CatalogItem(movie.getTitle(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());

    }

}
