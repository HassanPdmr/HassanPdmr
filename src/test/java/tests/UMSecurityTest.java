package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static utils.ExcelReader.ReadExcelData;


@Listeners(ExtentReportListener.class)
public class UMSecurityTest extends BaseTest {


    @BeforeMethod
    public void beforeAllTest() {

        umSecurityPage = homepage.navigateToUMSecurityPage();

    }

    @DataProvider(name = "getEmpIDCorrectDetails")
    public Object[][] getCorrectEmpID() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Security.xlsx", 0);

    }

    @Test(priority = 2, dataProvider = "getEmpIDCorrectDetails", description = "JMS-58 : Logout and login with Correct credentials - should allow user to login - Version 1")
    public void verifyEmpIDWithCorrectDetails(String Uname, String Pwd, String EmpId) {


        ExtentReportListener.getTest().assignCategory("User Management Security");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, " Logout and login with Correct credentials with valid credentials");
        List<String> actualVal = umSecurityPage.loginLogoutCorrectData(Uname, Pwd, EmpId);


        String Expected_EmpId_before = "Employee ID : 7000";
        String Expected_EmpId_after = "Employee ID : 7000";


        Assert.assertEquals(actualVal.get(0), Expected_EmpId_before, "Wrong Emp ID has been Displayed before logout");
        Assert.assertEquals(actualVal.get(1), Expected_EmpId_after, "Wrong Emp ID has been Displayed after login");

        ExtentReportListener.getTest().log(Status.INFO, " Logout and Login with correct details has been verified");


    }


    @DataProvider(name = "getEmpIDInvalidDetails")
    public Object[][] getInValidEmpID() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Security.xlsx", 1);

    }

    @Test(priority = 3, dataProvider = "getEmpIDInvalidDetails", description = "JMS-59 : Logout and login with wrong set of credentials - should not allow user to login - Version 1")
    public void verifyEmpIDWithInvalidCredentials(String IvUname, String IvPwd, String Pwd, String Uname, String ErrMsg) {

        ExtentReportListener.getTest().assignCategory("User Management Security");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check Logout and login with Correct credentials with Invalid credentials");


        boolean isErrorMessageDisplayed = umSecurityPage.loginLogoutINCorrectData(IvUname, IvPwd, Pwd, Uname, ErrMsg);

        Assert.assertTrue(isErrorMessageDisplayed, "Error message is not displayed as expected");
        ExtentReportListener.getTest().log(Status.INFO, " Logout and Login with correct details has been verified");


    }


    @DataProvider(name = "getLogOutWithFwdBackButton")
    public Object[][] getLogOutWithBackButton() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Security.xlsx", 2);

    }

    @Test(priority = 1, dataProvider = "getLogOutWithFwdBackButton", description = "JMS-61 : After logged in, Click Back and Forward from browser - user should be Prevented - Version 3")
    public void verifyFwdBackWithLogoutButton(String Uname, String Pass) {

        ExtentReportListener.getTest().assignCategory("User Management Security");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check with Fwd and withBack button hence user cant able to login from here");

        boolean isLoginPageDisplayedFwd = umSecurityPage.CheckForwardBackAfterLogout(Uname, Pass);
        Assert.assertTrue(isLoginPageDisplayedFwd, "Login page was not displayed when we click forward button after back from browser");
        ExtentReportListener.getTest().log(Status.INFO, "Forward and Back button from login page has been verified");




    }


    @Test(priority = 4, description = " JMS-60 : Logout and click ‘Back’ from browser - user shouldn’t be able to login - Version 3")
    public void verifyLogOutWithBackButton() {

        ExtentReportListener.getTest().assignCategory("User Management Security");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check with Bck button from Logout, thus user should not able to login again");


        boolean isLoginPageDisplayedBack = umSecurityPage.CheckLogOutWithBackButton();

        Assert.assertFalse(isLoginPageDisplayedBack, "Login page has been displayed even after clicking Back button from browser");
        ExtentReportListener.getTest().log(Status.INFO, "Back browser button with logout restriction for user has been verified");


    }




}
