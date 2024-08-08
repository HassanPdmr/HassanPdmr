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

        return ReadExcelData("D:\\ZoneTest\\User_Management_Security.xlsx", 0);

    }
    @Test(priority = 1, dataProvider = "getEmpIDCorrectDetails", description = "JMS-58 : Logout and login with Correct credentials - should allow user to login - Version 1")
    public void verifyEmpIDWithCorrectDetails(String Uname, String Pwd, String EmpId) {


        ExtentReportListener.getTest().log(Status.INFO, " Logout and login with Correct credentials with valid credentials");
        List<String> actualVal = umSecurityPage.loginLogoutCorrectData(Uname, Pwd, EmpId);


        String Expected_EmpId_before = "Employee ID : 7000";
        String Expected_EmpId_after = "Employee ID : 7000";


        Assert.assertEquals(actualVal.get(0), Expected_EmpId_before, "Wrong Emp ID has been Displayed before logout");
        Assert.assertEquals(actualVal.get(1), Expected_EmpId_after, "Wrong Emp ID has been Displayed after login");


    }



    @DataProvider(name = "getEmpIDInvalidDetails")
    public Object[][] getInValidEmpID() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_Management_Security.xlsx", 1);

    }

    @Test(priority = 2, dataProvider = "getEmpIDInvalidDetails", description = "JMS-59 : Logout and login with wrong set of credentials - should not allow user to login - Version 1")
    public void verifyEmpIDWithInvalidCredentials(String IvUname, String IvPwd, String Pwd , String Uname, String ErrMsg) {

        ExtentReportListener.getTest().log(Status.INFO, "Check Logout and login with Correct credentials with Invalid credentials");


        boolean isErrorMessageDisplayed = umSecurityPage.loginLogoutINCorrectData(IvUname,IvPwd,Pwd,Uname, ErrMsg);

        Assert.assertTrue(isErrorMessageDisplayed, "Error message is not displayed as expected");



    }

}
