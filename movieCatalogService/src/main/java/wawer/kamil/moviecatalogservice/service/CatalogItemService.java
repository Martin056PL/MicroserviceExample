package wawer.kamil.moviecatalogservice.service;

import wawer.kamil.moviecatalogservice.model.CatalogItem;
import wawer.kamil.moviecatalogservice.model.Rating;

public interface CatalogItemService {
    CatalogItem getCatalogItem(Rating rating);
}
