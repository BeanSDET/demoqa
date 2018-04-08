package com.demoqa.pages;

import com.github.webdriverextensions.WebPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.demoqa.utils.UserUtils.getFirstName;
import static com.demoqa.utils.UserUtils.getLastName;
import static com.github.webdriverextensions.Bot.*;

public class RegistrationPage extends WebPage {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationPage.class);
    public static final String PASSWORD = "Test1234";
    public static final String REGISTRATION_SUCCESS = "Thank you for your registration";

    @FindBy(id = "name_3_firstname")
    WebElement firstNameInput;
    @FindBy(id = "name_3_lastname")
    WebElement lastNameInput;
    @FindBy(css = "input[name*=checkbox_5]")
    List<WebElement> hobbiesCheckBoxes;
    @FindBy(id = "phone_9")
    WebElement phoneNumberInput;
    @FindBy(id = "username")
    WebElement usernameInput;
    @FindBy(id = "email_1")
    WebElement emailInput;
    @FindBy(id = "password_2")
    WebElement passwordInput;
    @FindBy(id = "confirm_password_password_2")
    WebElement confirmPasswordInput;
    @FindBy(css = "input[value=Submit]")
    WebElement submitButton;
    @FindBy(css = "#post-49 > div > p")
    WebElement registrationMessage;


    @Override
    public void open(Object... objects) {
        open("http://demoqa.com/registration/");
        assertIsOpen();
    }

    @Override
    public void assertIsOpen(Object... objects) throws AssertionError {
        assertCurrentUrlEquals("http://demoqa.com/registration/");
    }

    public String getRegistrationMessage() {
        return registrationMessage.getText();
    }

    public void registerUserMandatory(String fullName) {
        String uniqueId = getDateTime();
        logger.debug("{}'s unique id: {}", fullName, uniqueId);
        clearAndType(getFirstName(fullName), firstNameInput);
        clearAndType(getLastName(fullName), lastNameInput);
        selectRandomHobbies(getNumberOfHobbies());
        clearAndType("44" + uniqueId, phoneNumberInput);
        clearAndType("user_" + uniqueId, usernameInput);
        clearAndType(uniqueId + "@email.com", emailInput);
        clearAndType(PASSWORD, passwordInput);
        clearAndType(PASSWORD, confirmPasswordInput);
        click(submitButton);
        waitForElementToDisplay(registrationMessage);
    }

    private void selectRandomHobbies(int numberOfHobbies) {
        List<Integer> hobbiesIndexes = IntStream.rangeClosed(0, hobbiesCheckBoxes.size() - 1)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(hobbiesIndexes);
        for (int i = 0; i <= numberOfHobbies; i++) {
            select(hobbiesCheckBoxes.get(hobbiesIndexes.get(i)));
        }
    }

    private int getNumberOfHobbies() {
        Random rand = new Random();
        int numberOfHobbies = rand.nextInt(3);
        logger.debug("number of hobbies {}", numberOfHobbies + 1);
        return numberOfHobbies;
    }

    private String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        return now.format(formatter);
    }

}
