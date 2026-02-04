package main.java.LiteratureRecommendationSystem;

public class User {

    private final int age;
    private final String name;

    public User(int age, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Meno nesmie byť prázdne");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Vek musí byť väčší ako 0");
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
