package com.demoqa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserUtils {

    public static String getFirstName(String fullName) {
        int i = fullName.indexOf(" ");
        return fullName.substring(0, i);
    }

    public static String getLastName(String fullName) {
        int i = fullName.lastIndexOf(" ");
        return fullName.substring(i + 1, fullName.length());
    }

    public static List<String> getRandomUsers(List<String> users, int numberOfUsers) {
        List<String> randomUsers = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numberOfUsers; i++) {
            int randomIndex = rand.nextInt(users.size());
            randomUsers.add(users.get(randomIndex));
            users.remove(randomIndex);
        }
        return randomUsers;
    }

    public static List<String> getUnregisteredUsers(List<String> allUsers, List<String> registeredUsers) {
        registeredUsers.forEach(registeredUser -> {
            if (allUsers.contains(registeredUser)) {
                allUsers.remove(registeredUser);
            }
        });
        return allUsers;
    }

    public static void printUsers(List<String> users) {
        users.forEach(System.out::println);
    }
}
