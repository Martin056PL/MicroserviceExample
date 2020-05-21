package wawer.kamil.moviecatalogservice.service;

import wawer.kamil.moviecatalogservice.model.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getRatings(Long userId);
}
