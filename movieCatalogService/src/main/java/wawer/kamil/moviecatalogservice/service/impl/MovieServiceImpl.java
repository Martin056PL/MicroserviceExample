package wawer.kamil.moviecatalogservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import wawer.kamil.moviecatalogservice.model.Movie;
import wawer.kamil.moviecatalogservice.model.Rating;
import wawer.kamil.moviecatalogservice.service.MovieService;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final WebClient.Builder builder;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackGetMovie")
    public Movie getMovie(Rating rating) {
        return builder.build()
                .get()
                .uri("http://Movie-Info-Service/movies/"+  rating.getId())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }

    private Movie fallbackGetMovie(Rating rating){
        return new Movie(1L, "error");
    }
}
