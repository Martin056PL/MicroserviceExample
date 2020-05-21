package wawer.kamil.moviecatalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wawer.kamil.moviecatalogservice.model.CatalogItem;
import wawer.kamil.moviecatalogservice.model.Movie;
import wawer.kamil.moviecatalogservice.model.Rating;
import wawer.kamil.moviecatalogservice.service.CatalogItemService;
import wawer.kamil.moviecatalogservice.service.MovieService;

@RequiredArgsConstructor
@Service
public class CatalogItemServiceImpl implements CatalogItemService {

    private final MovieService movieService;

    @Override
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = movieService.getMovie(rating);
        return new CatalogItem(movie.getTitle(), "Description", rating.getRating());
    }

}
