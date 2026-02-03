package main.java.LiteratureRecommendationSystem.repository;

import main.java.LiteratureRecommendationSystem.domain.model.Literature;
import java.util.*;

public class LiteratureRepository {

    private final Map<String, Literature> byId = new HashMap<>();

    public void add(Literature item) {
        String id = requireValidId(item);
        if (byId.containsKey(id)) {
            throw new IllegalArgumentException("Položka s ID už existuje: " + id);
        }
        byId.put(id, item);
    }

    public void update(Literature item) {
        String id = requireValidId(item);
        if (!byId.containsKey(id)) {
            throw new IllegalArgumentException("Neexistuje položka s ID: " + id);
        }
        byId.put(id, item);
    }

    private String requireValidId(Literature item) {
        Objects.requireNonNull(item, "položka");
        String id = item.getId();
        return requireNonBlankId(id);
    }

    public Literature findById(String id) {
        String validId = requireNonBlankId(id);
        return byId.get(validId);
    }

    public List<Literature> findAll() {
        return List.copyOf(byId.values());
    }

    public boolean removeById(String id) {
        String validId = requireNonBlankId(id);
        return byId.remove(validId) != null;
    }

    private String requireNonBlankId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID je prázdne");
        }
        return id;
    }

    public int size() {
        return byId.size();
    }
    public void clear() {
        byId.clear();
    }
}