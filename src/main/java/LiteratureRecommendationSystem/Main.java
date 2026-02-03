package main.java.LiteratureRecommendationSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Zadaj meno:");
        String name = sc.nextLine();

        System.out.println("Zadaj vek:");
        int age = Integer.parseInt(sc.nextLine());

        User user1 = new User(age, name);
    }
}