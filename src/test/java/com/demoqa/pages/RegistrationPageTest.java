package com.demoqa.pages;

import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.demoqa.pages.RegistrationPage.REGISTRATION_SUCCESS;
import static com.demoqa.utils.UserUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(WebDriverRunner.class)
@Chrome
public class RegistrationPageTest {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationPageTest.class);

    RegistrationPage registrationPage;

    List<String> users = Stream.of("Jan van Dam", "Chack Norris", "Klark n Kent",
            "John Daw", "Bat Man", "Tim Los", "Dave o Core", "Pay Pal", "Lazy Cat", "Jack & Johnes")
            .collect(Collectors.toList());

    @Test
    public void register_five_random_users_successfully() {
        registrationPage.open();
        List<String> randomUsers = getRandomUsers(users, 5);
        randomUsers.forEach(user -> {
            logger.debug("registering user '{}'", user);
            registrationPage.registerUserMandatory(user);
            assertThat(registrationPage.getRegistrationMessage()).isEqualTo(REGISTRATION_SUCCESS);
        });
        System.out.println("*** Unregistered Users ***");
        printUsers(getUnregisteredUsers(users, randomUsers));
        System.out.println("**********************");
    }


}
