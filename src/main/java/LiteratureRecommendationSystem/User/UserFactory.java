package main.java.LiteratureRecommendationSystem.User;

import java.util.Scanner;

public class UserFactory {

    private final Scanner scanner;

    public UserFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    public User create() {
        System.out.println("Zadaj meno: ");
        String name = scanner.nextLine();

        System.out.println("Zadaj vek: ");
        int age = scanner.nextInt();

        scanner.nextLine();
        return new User(name, age);
    }
}
