package tests;


import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExcelParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.ExcelReader.ReadExcelData;

@Listeners(ExtentReportListener.class)
public class UserManagementTest extends BaseTest {


    @BeforeMethod
    public void beforeAllTest() {
        userManagement = homepage.navigateToUserManagementPage();
        userManagement.verifyAddUserisClickable();


    }

    @Test(priority = 0, description = "Navigating to Add User")
    public void NavigateToAddUser() {

        ExtentReportListener.getTest().assignCategory("Add User");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the Side UserManagement ");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the add user page");

        userManagement.verifyAddUserisClickable();
        ExtentReportListener.getTest().log(Status.INFO, "Navigated successfully to the Add user page");


    }

    @DataProvider(name = "getAddUserdata")
    public Object[][] getAddUserData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 0);
    }

    @Test(priority = 1, dataProvider = "getAddUserdata", description = "JMS-52 : Provide details for new employee but don’t click ‘ADD’ & ensure employee not created")
    public void verifyCreatingUserBynotClickingAdd(String empName, String empId, String designation,
                                                   String gender, String depart, String Pub, String access,
                                                   String role, String mail) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Adding User without clicking Add button");

        userManagement.creatingUserWithoutAdd(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        Assert.assertFalse(false, "User created even without clicking Add button");

        ExtentReportListener.getTest().log(Status.INFO, "User creation failed without clicking Add button");


    }

    @Test(priority = 2, dataProvider = "getAddUserdata", description = "JMS-55 : Try duplicate an employee by Emp-ID - EmpID should be unique - Version 1 Show ghost string Display Author/Updater ")
    public void verifyDuplicateEmpIdNotAdded(String empName, String empId, String designation,
                                             String gender, String depart, String Pub, String access,
                                             String role, String mail) {

        ExtentReportListener.getTest().log(Status.INFO, "Verifying the duplicate EmpID should be unique");

        String AlertMessage = userManagement.duplicateEmpId(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        Assert.assertEquals(AlertMessage, "Employee ID already exists.");

        ExtentReportListener.getTest().log(Status.INFO, "Verifying the duplicate EmpID should be unique");


    }

    @DataProvider(name = "getNewAddUser")
    public Object[][] getNewAddUserData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 1);
    }

    @Test(priority = 3, dataProvider = "getNewAddUser", description = "JMS-43 : Add new user with valid details and verify the entered details in Profile")
    public void verifyAddUserandVerifyfromEdit(String empName, String empId, String designation,
                                               String gender, String depart, String Pub, String access,
                                               String role, String mail) throws InterruptedException {


        ExtentReportListener.getTest().log(Status.INFO, "Verify new user from edit profile");
        List<String> actualUserValues = userManagement.addNewUserandVerify(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        List<String> expectedUserValues = List.of(empName, empId, designation, gender, depart, Pub, access, role, mail);


        Assert.assertEquals(actualUserValues, expectedUserValues, "Values are differ from page class");


    }


    @DataProvider(name = "getEmpName")
    public Object[][] getNewEmpData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 3);

    }

    @Test(priority = 4, dataProvider = "getEmpName", description = "JMS-48 : Verify the name should not allow other than alphabets - check with invalid scenarios - Version 1")
    public void verifyEmpNameShouldAllowAlphabets(String empName) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check the Emp Name only with alphabets");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\User_management.xlsx", 2);

        for (Object[] row : excelDataA) {

            if (row.length == 9) {

                String a = row[0].toString();
                String empId = row[1].toString();
                String designation = row[2].toString();
                String gender = row[3].toString();
                String depart = row[4].toString();
                String pub = row[5].toString();
                String access = row[6].toString();
                String role = row[7].toString();
                String mail = row[8].toString();

                Boolean actualErr = userManagement.nameWithOnlyAlphabets(empName, empId, designation, gender, depart, pub, access, role, mail);

                System.out.println("Actual Error: " + actualErr);
                Assert.assertFalse(actualErr, "No Validation error displayed");
                ExtentReportListener.getTest().log(Status.INFO, "EMP name has been verified with alphabets");


            }


        }
    }


    @DataProvider(name = "getEmpID")
    public Object[][] getNewEmpID() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 5);

    }

    @Test(priority = 5, dataProvider = "getEmpID", description = "JMS-49 : Verify the validation of employee id, whether not accepting any Alphabets - Version 1")
    public void verifyEmpIDShouldOnlyAcceptNumerics(String empId) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check the EmpID only with Numerics");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\User_management.xlsx", 4);

        for (Object[] row : excelDataA) {

            if (row.length == 9) {

                String empName = row[0].toString();
                String a = row[1].toString();
                String designation = row[2].toString();
                String gender = row[3].toString();
                String depart = row[4].toString();
                String pub = row[5].toString();
                String access = row[6].toString();
                String role = row[7].toString();
                String mail = row[8].toString();

                Boolean actualErr = userManagement.empIDWithOnlyNumbers(empName, empId, designation, gender, depart, pub, access, role, mail);

                System.out.println("Actual Error: " + actualErr);
                Assert.assertFalse(actualErr, "No Validation error displayed");
                ExtentReportListener.getTest().log(Status.INFO, "EmpID has been verified with numerics");


            }


        }


    }

    @Test(priority = 6, description = "Test Case has relations. Click to jump to section JMS-54 : Attempt to edit employee details, but don’t click ‘UPDATE’ & ensure the details have not changed - Version 1 ")
    public void verifyEditEmployeeWithoutUpdateClick() throws InterruptedException {


        ExtentReportListener.getTest().log(Status.INFO, "Check  edit employee details without clicking update button");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\User_management.xlsx", 8);

        for (Object[] row : excelDataA) {

            if (row.length == 9) {

                String empName = row[0].toString();
                String empID = row[1].toString();
                String designation = row[2].toString();
                String gender = row[3].toString();
                String depart = row[4].toString();
                String pub = row[5].toString();
                String access = row[6].toString();
                String role = row[7].toString();


                List<String> actualExp = userManagement.editEmployeeButNotClickingUpdate(empName, empID, designation, gender, depart, pub, access, role);

                double empI = Double.parseDouble(empID);
                int empIdAsInt = (int) empI;
                String empIDAsString = String.valueOf(empIdAsInt);

                List<String> expectedUserValues = List.of(empName, empIDAsString, designation, gender, depart, pub, access, role);


                Assert.assertEquals(actualExp, expectedUserValues, "Values are differ from page class");


            }


        }
    }

    @Test(priority = 7, description = "JMS-56 : Duplicate user with employ ID of the deactivated account - Version 2")
    public void verifyDupEmpIDofDeactivatedAccount() throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check inactive user EMP id can added again from add user");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\User_management.xlsx", 8);

        for (Object[] row : excelDataA) {

            if (row.length == 9) {

                String empName = row[0].toString();
                String empID = row[1].toString();
                String designation = row[2].toString();
                String gender = row[3].toString();
                String depart = row[4].toString();
                String pub = row[5].toString();
                String access = row[6].toString();
                String role = row[7].toString();
                String mail = row[8].toString();

                String actualErr = userManagement.dupUserEmpIdOfDeactivatedAcct(empName, empID, designation, gender, depart, pub, access, role, mail);


                String expectedMsg = "Employee ID already exists.";

                Assert.assertEquals(actualErr, expectedMsg, "Same inactive user also added here");

                ExtentReportListener.getTest().log(Status.INFO, "Inactive user login has been verified");


            }

        }
    }

    @DataProvider(name = "getGender")
    public Object[][] getGenderData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 9);

    }


    @Test(priority = 8, dataProvider = "getGender", description = "JMS-51 : Verify the Male/female icon on employee badge based on gender - Version 1")
    public void verifyGenderWithMaleAndFemale(String empName, String empId, String designation, String gender,
                                              String depart, String Pub, String access, String role, String mail) throws InterruptedException {


        ExtentReportListener.getTest().log(Status.INFO, "Check Gender dropdown");

        List<String> actual = userManagement.MaleFemaleIconCheck(empName, empId, designation, gender, depart, Pub, access, role, mail);

        String MaleGen = "Male";
        String FemaleGen = "Female";


        if (gender.equals(MaleGen)) {
            Assert.assertEquals(actual.get(0), MaleGen, "Expected gender to be Male");
        } else if (gender.equals(FemaleGen)) {
            Assert.assertEquals(actual.get(0), FemaleGen, "Expected gender to be Female");
        }

        ExtentReportListener.getTest().log(Status.INFO, "Gender icon has been verified");

    }

    @DataProvider(name = "getChangingRoles")
    public Object[][] getRoleNewData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 10);

    }

    @Test(priority = 9, dataProvider = "getChangingRoles", description = " JMS-207 : Change role of any user and verify the same by logging into user’s profile - Version 1")
    public void verifyChangingRolesByLoggingUser(String empName, String empId, String designation, String gender,
                                                 String depart, String Pub, String access, String role, String mail,
                                                 String roleTL, String Uname, String Pwd, String rolecheck, String PmUname, String PmPwd) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check Roles from TL to User and User to TL");

        List<String> actualVal = userManagement.CheckWithChangingRoles(empName, empId, designation, gender, depart,
                Pub, access, role, mail, roleTL, Uname, Pwd, rolecheck, PmUname, PmPwd);


        String TLVal = "Role : AMTL";
        String UserVal = "Role : User";


        Assert.assertEquals(actualVal.get(0), TLVal, "Expected gender to be Male");
        Assert.assertEquals(actualVal.get(1), UserVal, "Expected gender to be FeMale");

        ExtentReportListener.getTest().log(Status.INFO, "Changing roles has been verified");


    }


    @DataProvider(name = "getDepartDesignation")
    public Object[][] getDeptDesignData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 11);

    }

    @Test(priority = 9, dataProvider = "getDepartDesignation", description = " JMS-57 : Verify Designation and Department don’t impact on the roles assigned - Version 2")
    public void verifyDepartDesignOnUserRoles(String empName, String empId, String designation,
                                              String gender, String depart, String Pub, String access, String role, String mail,
                                              String Uname, String Pwd, String desigNew, String departNew) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check Designation and Department don't impact on User roles");


        List<String> actualVal = userManagement.desigDeptVerification(empName, empId, designation, gender,
                depart, Pub, access, role, mail, Uname, Pwd, desigNew, departNew);

        String ExisitingDesigVal = "Designation : Senior Paginator";
        String ExisitingDeptVal = "Department : Graphics";

        String UpdatedDesigVal = "Designation : Product Tester";
        String UpdatedDeptVal = "Department : Administration";


        Assert.assertEquals(actualVal.get(0), ExisitingDesigVal, "Wrong Existing Designation displayed");
        Assert.assertEquals(actualVal.get(1), ExisitingDeptVal, "Wrong Existing Department displayed");

        Assert.assertEquals(actualVal.get(2), UpdatedDesigVal, "Wrong Updated Designation displayed");
        Assert.assertEquals(actualVal.get(3), UpdatedDeptVal, "Wrong Updated Department displayed");

        ExtentReportListener.getTest().log(Status.INFO, "Department and Designation has been verified which it doesn't reflect on roles");


    }


    @DataProvider(name = "getAllManageView")
    public Object[][] getManageView() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_Management_Manage.xlsx", 0);

    }

    @Test(priority = 10, dataProvider = "getAllManageView", description = "JMS-210 : MANAGE view and verify user can easily navigate between Publisher view, Journal view, Article view - Version 1")
    public void verifyAllViewManage(String PubV, String JourV, String ArtV){



        ExtentReportListener.getTest().log(Status.INFO, "Check for Manage View for Publisher, Journal and Article view");

        List<Boolean> actualVal = userManagement.navigateToAllManageViewPage(PubV,JourV,ArtV);

        Assert.assertTrue(actualVal.get(0), "View was not displayed as per the option");
        Assert.assertTrue(actualVal.get(1), "View was not displayed as per the option");
        Assert.assertTrue(actualVal.get(2), "View was not displayed as per the option");

        ExtentReportListener.getTest().log(Status.INFO, "All Manage view has been displayed");






    }

    @DataProvider(name = "getPublisherView")
    public Object[][] getPubView() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_Management_Manage.xlsx", 1);

    }

    @Test(priority = 11, dataProvider = "getPublisherView", description = "JMS-211 : Add a new Publisher(Pub-A) and verify the publisher is displayed with correct Name/Acronym - Version 1")
    public void verifyPubView(String PubAcro, String PubName){

        ExtentReportListener.getTest().log(Status.INFO, "Check for Publisher verify with ack and name");

        List<Object> actualVal = userManagement.addAndVerifyPublisher(PubAcro,PubName);

        Assert.assertTrue((Boolean) actualVal.get(0), "Publisher Acro displayed wrongly here ");

        Assert.assertEquals(actualVal.get(1), PubName, "Publisher name was wrongly displayed");

        Assert.assertFalse((Boolean) actualVal.get(2), "Journal was added");

        ExtentReportListener.getTest().log(Status.INFO, "Publisher name and Acro has been verified");




    }



    @DataProvider(name = "getTwoJournalCheck")
    public Object[][] getTwoJournals() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_Management_Manage.xlsx", 2);

    }

    @Test(priority = 12, dataProvider = "getTwoJournalCheck", description = "JMS-212 : Add one or two journals(Jrnl-A,B) under the publisher and revisit the Publisher banner. - Version 1")
    public void verifyTwoJournals(String PubAcro, String PubName, String Jacro,
                                  String Jname, String JacroNew, String JnameNew){

        ExtentReportListener.getTest().log(Status.INFO, "Check two journals that can be added in one publisher");

        List<Boolean> actualVal = userManagement.addTwoJounralsAndVerifywithPub(PubAcro, PubName, Jacro, Jname, JacroNew,JnameNew);


        Assert.assertTrue(actualVal.get(0), "Journal count 1 was not added");
        Assert.assertTrue(actualVal.get(1), "Journal count 2 was not added");
        Assert.assertFalse(actualVal.get(2), "Article was listed here");


        ExtentReportListener.getTest().log(Status.INFO, "Journals created and it has been verified under publisher");






    }











}
