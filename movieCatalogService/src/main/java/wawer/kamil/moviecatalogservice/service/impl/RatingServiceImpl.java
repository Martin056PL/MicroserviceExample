package wawer.kamil.moviecatalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import wawer.kamil.moviecatalogservice.model.Rating;
import wawer.kamil.moviecatalogservice.service.RatingService;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final WebClient.Builder builder;

    @Override
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
}
