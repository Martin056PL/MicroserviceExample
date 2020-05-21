package wawer.kamil.moviecatalogservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wawer.kamil.moviecatalogservice.model.CatalogItem;
import wawer.kamil.moviecatalogservice.model.Rating;
import wawer.kamil.moviecatalogservice.service.CatalogItemService;
import wawer.kamil.moviecatalogservice.service.RatingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final CatalogItemService catalogItemService;
    private final RatingService ratingService;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") Long userId) {
        List<Rating> ratings = ratingService.getRatings(userId);
        return ratings.stream()
                .map(catalogItemService::getCatalogItem)
                .collect(Collectors.toList());

    }





}
