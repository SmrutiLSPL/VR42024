package com.qa.VR4.tests;

import com.qa.VR4.base.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class createUserTest extends BaseTest {

    @BeforeTest
    public void login() {
        wlogin.defaultlogin(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("selectView"));
    }

    @Test
    public void openGlobalMenuTest() {
        openuserPage.getUserPage();
        openuserPage.clickonAddnew();
        openuserPage.openUserPage();
    }

    @Test
    public void validateProvidingOnlytheMandatoryFieldsTest() throws InterruptedException {
        String[] groups = {"Accounting", "Administrator", "Agent"};
        openuserPage.creatUserWithMandatoryfields(groups, "QASMR", "smruti", "qasmr@gmail.com", "123456789", "123456789");

    }


    @Test
    public void ValidateProperNotificationMessagesAreDisplayedForMandatoryFieldsTest() {
        openuserPage.getUserPage();
        openuserPage.clickonAddnew();
        openuserPage.ValidateProperNotificationMessagesAreDisplayedForMandatoryFields();
    }

    @Test
    public void ValidateRegisteringUserAccountProvidingAllFieldsTest() {
        openuserPage.getUserPage();
        openuserPage.clickonAddnew();
        String[] groups = {"Accounting", "Administrator", "Agent"};
        openuserPage.ValidateRegisteringUserAccountProvidingAllFields(groups, "QASMR", "smruti", "parekh","9878451258","sandeep8@gmail.com", "123456789", "123456789");
    }
    @Test
    public void ValidateDifferentPasswordsPasswordConfirmPasswordTest() {
        openuserPage.getUserPage();
        openuserPage.clickonAddnew();
        String[] groups = {"Accounting", "Administrator", "Agent"};
        openuserPage.ValidateDifferentPasswordsPasswordConfirmPassword(groups, "Sandeep", "Sandeep", "patel","9878451258","sandeep8@gmail.com", "123456789", "987456321");
    }
    @Test
    public void VValidateUserAccountProvidingExistingUserEmailIDTest() {
        openuserPage.getUserPage();
        openuserPage.clickonAddnew();
        String[] groups = {"Accounting", "Administrator", "Agent"};
        openuserPage.ValidateUserAccountProvidingExistingUserEmailID(groups, "Sandeep", "Sandeep", "patel","9878451258","qasmr@gmail.com", "123456789", "123456789");
    }
    @Test
    public void ValidateUserAccountProvidingInvalidEmailAddressTest() {
        openuserPage.getUserPage();
        openuserPage.clickonAddnew();
        String[] groups = {"Accounting", "Administrator", "Agent"};
        openuserPage.ValidateUserAccountProvidingInvalidEmailAddress(groups, "Sandeep", "Sandeep", "patel","9878451258","parekhsmruti31", "123456789", "123456789");
    }
    @Test
    public void openLIst() throws InterruptedException {
        openuserPage.getUserPage();
        openuserPage.ValidateLoggingApplicationEnableOptionForUserCredentials("Smruti");
    }
}
