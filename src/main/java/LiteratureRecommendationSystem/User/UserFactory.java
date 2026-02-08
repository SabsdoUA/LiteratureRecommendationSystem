package LiteratureRecommendationSystem.User;

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
        int age = readInt();

        return new User(name, age);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Prosím, zadajte iba číslo.");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
