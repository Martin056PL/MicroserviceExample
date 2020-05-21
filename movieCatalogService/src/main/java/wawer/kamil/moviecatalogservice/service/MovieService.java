package wawer.kamil.moviecatalogservice.service;

import wawer.kamil.moviecatalogservice.model.Movie;
import wawer.kamil.moviecatalogservice.model.Rating;

public interface MovieService {


    Movie getMovie(Rating rating);
}
