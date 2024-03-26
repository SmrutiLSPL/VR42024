package com.qa.VR4.tests;

import com.qa.VR4.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OwnerLoginTest extends BaseTest {

    @Test
    public void OwnerLoginvalidcredentialsTest() throws InterruptedException {

        String accPage = ownerloginPage.VROwnerLogin("parekhsmruti31@gmail.com","123456789");
        Assert.assertEquals(accPage,"VR Works - Owner Portal");
    }
    @Test
    public void OwnerLoginAllInvalidvalidcredentialsTest() throws InterruptedException {

        String accPage = ownerloginPage.VROwnerWithAllInvaliddata("","");
        Assert.assertEquals(accPage,"Please enter email or username Please enter password");
    }

    @Test
    public void OwnerLoginInvalidPasswordTest() throws InterruptedException {

        String accPage = ownerloginPage.VROwnerWithAllInvaliddata("parekhsmruti31@gmail.com","4566132");
        Assert.assertEquals(accPage,"Incorrect Email/Username or Password");
    }

}
