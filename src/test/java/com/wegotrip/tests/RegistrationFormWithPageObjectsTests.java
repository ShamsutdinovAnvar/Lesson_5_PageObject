package com.wegotrip.tests;

import com.codeborne.selenide.Configuration;
import com.wegotrip.pages.RegistrationFormPage;
import com.wegotrip.testData.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistrationFormWithPageObjectsTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }


    @Test
    void fillFormTest() {
        registrationFormPage.openPage()
                .setFirstName(User.firstName)
                .setLastName(User.lastName)
                .setEmail(User.email)
                .setGender(User.gender)
                .setNumber(User.number)
                .setBirthDate(User.day, User.month, User.year)
                .setSubjects(User.subjects)
                .setHobbies(User.hobbies)
                .uploadPicture(User.picturePath)
                .setAddress(User.address, User.state, User.city)
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", User.firstName + " " + User.lastName)
                .checkResult("Student Email", User.email)
                .checkResult("Gender", User.gender)
                .checkResult("Mobile", User.number)
                .checkResult("Date of Birth", User.birthDay)
                .checkResult("Subjects", User.subjects)
                .checkResult("Hobbies", User.hobbies)
                .checkResult("Picture", User.picture)
                .checkResult("Address", User.address)
                .checkResult("State and City", User.state + " " + User.city);
    }

}