package wawer.kamil.moviecatalogservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import wawer.kamil.moviecatalogservice.model.Rating;
import wawer.kamil.moviecatalogservice.service.RatingService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final WebClient.Builder builder;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackGetRating")
    public List<Rating> getRatings(Long userId) {
        return builder.build()
                .get()
                .uri("http://Ratings-Data-Service/ratingsData/users/"+ userId)
                .retrieve()
                .bodyToFlux(Rating.class)
                .timeout(Duration.ofMillis(2000))
                .collectList()
                .block();
    }

    private List<Rating> fallbackGetRating(Long userId){
        return Collections.singletonList(new Rating(0L, -10));
    }
}
