package main.java.LiteratureRecommendationSystem.repository;

import main.java.LiteratureRecommendationSystem.domain.model.Manga;
import java.util.*;

public class MangaRepository {

    private final Map<String, Manga> byId = new HashMap<>();

    public void add(Manga manga) {
        Objects.requireNonNull(manga, "manga");

        String id = manga.getId();
        if (byId.containsKey(id)) {
            throw new IllegalArgumentException("Manga s ID už existuje: " + id);
        }

        byId.put(id, manga);
    }

    public List<Manga> findAll() {
        return List.copyOf(byId.values());
    }

    public int size() {
        return byId.size();
    }
    public void clear() {
        byId.clear();
    }
}
