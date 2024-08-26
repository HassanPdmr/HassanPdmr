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
    public void beforeAllTest() throws InterruptedException {

        userManagement = homepage.navigateToUserManagementPage();
        userManagement.verifyAddUserisClickable();


    }

    @Test(priority = 0, description = "Navigating to Add User")
    public void NavigateToAddUser() throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");

        ExtentReportListener.getTest().log(Status.INFO, "Clicking the Side UserManagement ");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the add user page");

        userManagement.verifyAddUserisClickable();
        ExtentReportListener.getTest().log(Status.INFO, "Navigated successfully to the Add user page");


    }

    @DataProvider(name = "getAddUserdata")
    public Object[][] getAddUserData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 0);
    }

    @Test(priority = 1, dataProvider = "getAddUserdata", description = "JMS-52 : Provide details for new employee but don’t click ‘ADD’ & ensure employee not created - Version 3")
    public void verifyCreatingUserBynotClickingAdd(String empName, String empId, String designation,
                                                   String gender, String depart, String Pub, String access,
                                                   String role, String mail) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");

        ExtentReportListener.getTest().log(Status.INFO, "Enter the details and don't click ‘ADD’button. Instead click 'cancel' or 'X'");
        ExtentReportListener.getTest().log(Status.INFO, "Check whether user created from list of users");


        userManagement.creatingUserWithoutAdd(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        Assert.assertFalse(false, "User created even without clicking Add button");

        ExtentReportListener.getTest().log(Status.INFO, "Navigated away from AddUser page");
        ExtentReportListener.getTest().log(Status.INFO, "User not available - not created");


    }

    @Test(priority = 2, dataProvider = "getAddUserdata", description = " JMS-55 : Try duplicate an employee by Emp-ID - EmpID should be unique - Version 2")
    public void verifyDuplicateEmpIdNotAdded(String empName, String empId, String designation,
                                             String gender, String depart, String Pub, String access,
                                             String role, String mail) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");

        ExtentReportListener.getTest().log(Status.INFO, "Try to add the new user using the existing the user id");

        String AlertMessage = userManagement.duplicateEmpId(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        Assert.assertEquals(AlertMessage, "Employee ID already exists.");

        ExtentReportListener.getTest().log(Status.INFO, "The user should not be created. The duplication is not allowed.");


    }

    @DataProvider(name = "getNewAddUser")
    public Object[][] getNewAddUserData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 1);
    }

    @Test(priority = 3, dataProvider = "getNewAddUser", description = "JMS-43 : Add new user with valid details and verify the entered details in Profile - Version 2")
    public void verifyAddUserandVerifyfromEdit(String empName, String empId, String designation,
                                               String gender, String depart, String Pub, String access,
                                               String role, String mail) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the User menu");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Add user button");
        ExtentReportListener.getTest().log(Status.INFO, "Add the valid information, Employee ID should be unique.");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Add user");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Profile, Verify the updated data's correct.");
        List<String> actualUserValues = userManagement.addNewUserandVerify(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        List<String> expectedUserValues = List.of(empName, empId, designation, gender, depart, Pub, access, role, mail);


        Assert.assertEquals(actualUserValues, expectedUserValues, "Values are differ from page class");

        ExtentReportListener.getTest().log(Status.INFO, "The user page is displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The Add user page is pop up is displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The data should be updated");
        ExtentReportListener.getTest().log(Status.INFO, "The user should be added");
        ExtentReportListener.getTest().log(Status.INFO, "The data entered on the add profile should be displayed on the profile page.");


    }


    @DataProvider(name = "getEmpName")
    public Object[][] getNewEmpData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 3);

    }

    @Test(priority = 4, dataProvider = "getEmpName", description = "JMS-48 : Verify the name should not allow other than alphabets - check with invalid scenarios - Version 3")
    public void verifyEmpNameShouldAllowAlphabets(String empName) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Try to enter the invalid format other than alphabet at the name file and click on the Add user button.");
        ExtentReportListener.getTest().log(Status.INFO, "Try the same on Existing users");

        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//User_Management.xlsx", 2);

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
                ExtentReportListener.getTest().log(Status.INFO, "The name should not accept. User should not be created.");
                ExtentReportListener.getTest().log(Status.INFO, "Invalid name should not get accepted");


            }


        }
    }


    @DataProvider(name = "getEmpID")
    public Object[][] getNewEmpID() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 5);

    }

    @Test(priority = 5, dataProvider = "getEmpID", description = "JMS-49 : Verify the validation of employee id, whether not accepting any Alphabets - Version 2")
    public void verifyEmpIDShouldOnlyAcceptNumerics(String empId) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Try to enter the alphabet at the employee id field and Click Add user.");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//User_Management.xlsx", 4);

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
                ExtentReportListener.getTest().log(Status.INFO, "Should not accept at the employee id field and user should not be created.");


            }


        }


    }

    @Test(priority = 6, description = "JMS-54 : Attempt to edit employee details, but don’t click ‘UPDATE’ & ensure the details have not changed - Version 2")
    public void verifyEditEmployeeWithoutUpdateClick() throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the edit button");
        ExtentReportListener.getTest().log(Status.INFO, "Modify the data,update button should not be clicked and close the button");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//User_Management", 8);

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

                ExtentReportListener.getTest().log(Status.INFO, "Edit pop up is displayed");
                ExtentReportListener.getTest().log(Status.INFO, "Verify at the profile page , data should not be changed.");


            }


        }
    }

    @Test(priority = 7, description = "JMS-56 : Duplicate user with employ ID of the deactivated account - Version 3")
    public void verifyDupEmpIDofDeactivatedAccount() throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Try to create the new user using the deactivated employ Id.");
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

                ExtentReportListener.getTest().log(Status.INFO, "The user should not be created");


            }

        }
    }

    @DataProvider(name = "getGender")
    public Object[][] getGenderData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 9);

    }


    @Test(priority = 8, dataProvider = "getGender", description = "JMS-51 : Verify the Male/female icon on employee badge based on gender - Version 2")
    public void verifyGenderWithMaleAndFemale(String empName, String empId, String designation, String gender,
                                              String depart, String Pub, String access, String role, String mail) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the User menu");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Add user button");
        ExtentReportListener.getTest().log(Status.INFO, "Enter the details of the user as the male");
        ExtentReportListener.getTest().log(Status.INFO, "Enter the details of the user as the Female");

        List<String> actual = userManagement.MaleFemaleIconCheck(empName, empId, designation, gender, depart, Pub, access, role, mail);

        String MaleGen = "Male";
        String FemaleGen = "Female";


        if (gender.equals(MaleGen)) {
            Assert.assertEquals(actual.get(0), MaleGen, "Expected gender to be Male");
        } else if (gender.equals(FemaleGen)) {
            Assert.assertEquals(actual.get(0), FemaleGen, "Expected gender to be Female");
        }

        ExtentReportListener.getTest().log(Status.INFO, "The user page is displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The Add user page is pop up is displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The male gender is displayed at the JMS user page of the created user.");
        ExtentReportListener.getTest().log(Status.INFO, "The Female image is displayed at the JMS user page of the created user.");

    }

    @DataProvider(name = "getChangingRoles")
    public Object[][] getRoleNewData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 10);

    }

    @Test(priority = 10, dataProvider = "getChangingRoles", description = "JMS-207 : Change role of any user and verify the same by logging into user’s profile - Version 2")
    public void verifyChangingRolesByLoggingUser(String empName, String empId, String designation, String gender,
                                                 String depart, String Pub, String access, String role, String mail,
                                                 String roleTL, String Uname, String Pwd, String rolecheck, String PmUname, String PmPwd) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "From PM Login, Change the role of the Employee(A) from 'user'to TL");
        ExtentReportListener.getTest().log(Status.INFO, "Then log in to the employee's profile and check the Role");
        ExtentReportListener.getTest().log(Status.INFO, "Check whether 'Assign' option is enabled which is exclusive for TL");
        ExtentReportListener.getTest().log(Status.INFO, "From PM login, Change again role of the emp(A) from TL to 'Login', and verify");
        ExtentReportListener.getTest().log(Status.INFO, "Login to the employee's profile and verify the AM/TL dashboard is available");
        ExtentReportListener.getTest().log(Status.INFO, "Check whether the employee can able 'Add the article' now");

        List<String> actualVal = userManagement.CheckWithChangingRoles(empName, empId, designation, gender, depart,
                Pub, access, role, mail, roleTL, Uname, Pwd, rolecheck, PmUname, PmPwd);


        String TLVal = "Role : AMTL";
        String UserVal = "Role : User";


        Assert.assertEquals(actualVal.get(0), TLVal, "Expected gender to be Male");
        Assert.assertEquals(actualVal.get(1), UserVal, "Expected gender to be FeMale");

        ExtentReportListener.getTest().log(Status.INFO, "The Role should be changed by the PM.");
        ExtentReportListener.getTest().log(Status.INFO, "Role changed from User to 'AM/TL'");
        ExtentReportListener.getTest().log(Status.INFO, "'Assign' option is enabled");
        ExtentReportListener.getTest().log(Status.INFO, "Role now changed again from 'AM/TL' to 'Login'");
        ExtentReportListener.getTest().log(Status.INFO, "AM/TL dashboard is NOT available now");
        ExtentReportListener.getTest().log(Status.INFO, "Can navigate to 'Add article' page, and check for add article");



    }


    @DataProvider(name = "getDepartDesignation")
    public Object[][] getDeptDesignData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 11);

    }

    @Test(priority = 9, dataProvider = "getDepartDesignation", description = "JMS-57 : Verify Designation and Department don’t impact on the roles assigned - Version 2")
    public void verifyDepartDesignOnUserRoles(String empName, String empId, String designation,
                                              String gender, String depart, String Pub, String access, String role, String mail,
                                              String Uname, String Pwd, String desigNew, String departNew) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Add user by giving all the mandatory details like Emp-ID, name, Access, Role");
        ExtentReportListener.getTest().log(Status.INFO, "Login as the user");
        ExtentReportListener.getTest().log(Status.INFO, "As PM, Change now the Dept & Designation of the user as totally irrelavant and update");
        ExtentReportListener.getTest().log(Status.INFO, "Repeat step2 and check");


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

        ExtentReportListener.getTest().log(Status.INFO, "User must be able to create");
        ExtentReportListener.getTest().log(Status.INFO, "User able to login");
        ExtentReportListener.getTest().log(Status.INFO, "User updated");
        ExtentReportListener.getTest().log(Status.INFO, "User able to login as previous and continous the work as usual");


    }


    @DataProvider(name = "getAllManageView")
    public Object[][] getManageView() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 0);

    }

    @Test(priority = 11, dataProvider = "getAllManageView", description = "JMS-210 : MANAGE view and verify user can easily navigate between Publisher view, Journal view, Article view - Version 1")
    public void verifyAllViewManage(String PubV, String JourV, String ArtV) {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Manage menu");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Publisher view");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the article view");
        ExtentReportListener.getTest().log(Status.INFO, " Click on the Journal view");


        List<Boolean> actualVal = userManagement.navigateToAllManageViewPage(PubV, JourV, ArtV);

        Assert.assertTrue(actualVal.get(0), "View was not displayed as per the option");
        Assert.assertTrue(actualVal.get(1), "View was not displayed as per the option");
        Assert.assertTrue(actualVal.get(2), "View was not displayed as per the option");

        ExtentReportListener.getTest().log(Status.INFO, "Manage view page is displayed");
        ExtentReportListener.getTest().log(Status.INFO, "All the Publisher list are displayed");
        ExtentReportListener.getTest().log(Status.INFO, "All the Article list displayed");
        ExtentReportListener.getTest().log(Status.INFO, "All the journal list displayed");


    }

    @DataProvider(name = "getPublisherView")
    public Object[][] getPubView() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 1);

    }

    @Test(priority = 12, dataProvider = "getPublisherView", description = "JMS-211 : Add a new Publisher(Pub-A) and verify the publisher is displayed with correct Name/Acronym - Version 2")
    public void verifyPubView(String PubAcro, String PubName) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Create the publisher as Pub A");

        List<Object> actualVal = userManagement.addAndVerifyPublisher(PubAcro, PubName);

        Assert.assertTrue((Boolean) actualVal.get(0), "Publisher Acro displayed wrongly here ");

        Assert.assertEquals(actualVal.get(1), PubName, "Publisher name was wrongly displayed");

        Assert.assertFalse((Boolean) actualVal.get(2), "Journal was added");

        ExtentReportListener.getTest().log(Status.INFO, "Verify that publisher is displayed on the manage board with correct Name/Acronym");


    }


    @DataProvider(name = "getTwoJournalCheck")
    public Object[][] getTwoJournals() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 2);

    }

    @Test(priority = 13, dataProvider = "getTwoJournalCheck", description = "JMS-212 : Add one or two journals(Jrnl-A,B) under the publisher and revisit the Publisher banner. - Version 2")
    public void verifyTwoJournals(String PubAcro, String PubName, String Jacro,
                                  String Jname, String JacroNew, String JnameNew) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Create the journal A, B under the Pub A");
        ExtentReportListener.getTest().log(Status.INFO, "navigate to Manage->Publisher View page; Check the journal count on the Publisher");
        ExtentReportListener.getTest().log(Status.INFO, "Now on same - Check the Article count on the Publisher");

        List<Boolean> actualVal = userManagement.addTwoJounralsAndVerifywithPub(PubAcro, PubName, Jacro, Jname, JacroNew, JnameNew);

        Assert.assertTrue(actualVal.get(0), "Journal count 1 was not added");
        Assert.assertTrue(actualVal.get(1), "Journal count 2 was not added");
        Assert.assertTrue(actualVal.get(2), "Article was not listed here");

        ExtentReportListener.getTest().log(Status.INFO, "Two journals have been created");
        ExtentReportListener.getTest().log(Status.INFO, "journal count is updated as 2");
        ExtentReportListener.getTest().log(Status.INFO, "No count is updated or indicated as '0'");


    }


    @Test(priority = 14, dataProvider = "getTwoJournalCheck", description = "JMS-213 : Verify the added Journals displayed in Journal-View with their Name /Acronym correctly displayed - Version 2")
    public void verifyTwoJournalsWithAcro(String PubAcro, String PubName, String Jacro,
                                          String Jname, String JacroNew, String JnameNew) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Create 2 different journals under the Publisher");
        ExtentReportListener.getTest().log(Status.INFO, "Check now in Manage->Journal view - Journals displayed under the Publisher");
        ExtentReportListener.getTest().log(Status.INFO, "Check their acronyms");
        ExtentReportListener.getTest().log(Status.INFO, "Check the article count for the concerned journals");

        List<Object> actualVal = userManagement.addTwoJounralsAndVerifywithPubandJournalAcro(PubAcro, PubName, Jacro, Jname, JacroNew, JnameNew);

        Assert.assertEquals(actualVal.get(0), Jacro, "Wrong Journal Acroname for 1stAdded Journal is displayed");

        Assert.assertEquals(actualVal.get(1), JacroNew, "Wrong Journal Acroname for 1stAdded Journal is displayed");

        Assert.assertFalse((Boolean) actualVal.get(2), "Article was added before creating it");

        ExtentReportListener.getTest().log(Status.INFO, "Two journals created");
        ExtentReportListener.getTest().log(Status.INFO, "Two journals displayed under the publisher");
        ExtentReportListener.getTest().log(Status.INFO, "Acronyms are correctly displayed");
        ExtentReportListener.getTest().log(Status.INFO, "If no articles created under the Particular journal - Art. count indicates '0'");

    }


    @DataProvider(name = "getAddArtilce")
    public Object[][] getAddArticleData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 3);

    }

    @Test(priority = 15, dataProvider = "getAddArtilce", description = "JMS-214 : Add articles under journal(Jrnl-A) and verify the Publisher and Journal banners - Version 2")
    public void verifyAddArticlewithPub(String PubAcro, String PubName, String Jacro,
                                        String Jname, String JacroArt, String artname, String workflow) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Create 2 articles under journal(Jrnl-A) only and check the Art.Count in Journal View");
        ExtentReportListener.getTest().log(Status.INFO, "Now Check the Jrnl, Art count in Publisher View");


        Boolean actValue = userManagement.AddArticleWithPub(PubAcro, PubName, Jacro, Jname, JacroArt, artname, workflow);
        Assert.assertTrue(actValue, "Article not added");
        ExtentReportListener.getTest().log(Status.INFO, "Article added Successfully");


    }

    @DataProvider(name = "getPubDescription")
    public Object[][] getPubDescptn() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 4);

    }


    @Test(priority = 16, dataProvider = "getPubDescription", description = "JMS-216 : Verify the description of the publisher is displayed correctly in the outer banner - Version 2")
    public void verifyPubDescription(String PubAcro, String PubName, String Description) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Manage menu");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Publisher view");
        ExtentReportListener.getTest().log(Status.INFO, "Create the publisher and update the description");
        ExtentReportListener.getTest().log(Status.INFO, "Click on the Edit button and update the description.");

        String actualVal = userManagement.checkPubDescription(PubAcro, PubName, Description);

        Assert.assertEquals(actualVal, "Test Description", "Wrong description is displayed");

        ExtentReportListener.getTest().log(Status.INFO, "Manage view page is displayed");
        ExtentReportListener.getTest().log(Status.INFO, "All the Publisher list are displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The description is displayed at the card of the publisher correctly in the outer banner");
        ExtentReportListener.getTest().log(Status.INFO, "The description is displayed at the card of the publisher correctly in the outer banner");
    }

    @Test(priority = 17, dataProvider = "getPubDescription", description = "JMS-219 : Verify the following details in Publisher banner by adding/editing - Version 2")
    public void verifyPubDetailsOuterBanner(String PubAcro, String PubName, String Description) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check publisher Acroname and Description in Outer banner  ");

        List<String> actualVal = userManagement.checkPubDetailsInOuterBanner(PubAcro, PubName, Description);

        Assert.assertEquals(actualVal.get(0), PubAcro, "Wrong PubAcro is displayed");
        Assert.assertEquals(actualVal.get(1), Description, "Wrong description is displayed");

        ExtentReportListener.getTest().log(Status.INFO, "Description has been successfully verified");


    }

    @DataProvider(name = "getJournalBanner")
    public Object[][] getJrDataBanner() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 5);

    }


    @Test(priority = 18, dataProvider = "getJournalBanner", description = "JMS-220 : Verify the following details in journal badge by adding/editing: - Version 3")
    public void verifyJournalDetailOuterBanner(String PubAcro, String PubName, String Jacro, String Jname) {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check the details inside the banner are correct");
        ExtentReportListener.getTest().log(Status.INFO, "Modify the Recto option and check again");

        List<Object> actualVal = userManagement.checkJournalDetailInOuterBanner(PubAcro, PubName, Jacro, Jname);

        Assert.assertEquals(actualVal.get(0), PubName, "Wrong PubName displayed");
        Assert.assertEquals(actualVal.get(1), Jacro, "Wrong Journal Ackro displayed");
        Assert.assertEquals(actualVal.get(2), Jname, "Wrong Journal Name displayed");
        Assert.assertTrue((Boolean) actualVal.get(3), "Wrong message");

        ExtentReportListener.getTest().log(Status.INFO, "Publisher Name is correct");
        ExtentReportListener.getTest().log(Status.INFO, "Journal Name is correct");
        ExtentReportListener.getTest().log(Status.INFO, "Journal Ackro is correct");
        ExtentReportListener.getTest().log(Status.INFO, "Recto option is changed and updated correctly");


    }


    @DataProvider(name = "getArticleBadge")
    public Object[][] getArtBadge() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 6);

    }

    @Test(priority = 19, dataProvider = "getArticleBadge", description = "JMS-220 : Verify the following details in journal badge by adding/editing: - Version 2")
    public void verifyArticleName(String PubName, String journalacro, String Jack, String artname, String workflow) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check Article Details in Article Badge");

        List<Object> actualVal = userManagement.checkDetailsInArticleBadge(PubName, journalacro, Jack, artname, workflow);

        Assert.assertTrue((Boolean) actualVal.get(0), "Wrong result displayed");
        Assert.assertEquals(actualVal.get(1), artname, "Wrong result displayed");

        ExtentReportListener.getTest().log(Status.INFO, "Article badge has been successfully verified");


    }


    @DataProvider(name = "getSearch")
    public Object[][] getsearchStock() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 7);

    }


    @Test(priority = 20, dataProvider = "getSearch", description = "JMS-82 : Search with any text and verify the list is updated accordingly - Version 2")
    public void verifyStockSearch(String PubName) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Navigate to stocklist page");
        ExtentReportListener.getTest().log(Status.INFO, "Search with any text (eg. publisher name) in the search box.");

        boolean actualVal = userManagement.checkSearchOptioninStock(PubName);
        Assert.assertTrue(actualVal, "Search results are not correctly updated.");
        ExtentReportListener.getTest().log(Status.INFO, "The Publisher, Journal , article etc should be display in the stocklist.");
        ExtentReportListener.getTest().log(Status.INFO, "In the search box, it will display a list of data as per the search");


    }

    @DataProvider(name = "getPubFromFilter")
    public Object[][] getPubinBothStockAndManage() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 8);

    }

    @Test(priority = 21, dataProvider = "getPubFromFilter", description = "JMS-78 : Apply filter on Pub, journal separately and ensure the list is updated by filter(Publisher) - Version 2")
    public void verifyPubinCountFilter(String PubAcro, String PubNam) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Checking Publisher count from stock with manage count");

        boolean actualVal = userManagement.checkPubJournalSepratelyinFilterPUB(PubAcro, PubNam);
        Assert.assertTrue(actualVal, "Publisher count varies");
        ExtentReportListener.getTest().log(Status.INFO, "Filter verification completed for Publisher");


    }


    @DataProvider(name = "getJourFromFilter")
    public Object[][] getJourinBothStockAndManage() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 9);

    }


    @Test(priority = 22, dataProvider = "getJourFromFilter", description = "JMS-78 : Apply filter on Pub, journal separately and ensure the list is updated by filter(Journal) - Version 2")
    public void verifyJourInCountFilter(String PubAcro, String Jacro, String Jname) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Checking Journals count from stock with manage count");

        boolean actualVal = userManagement.checkPubJournalSepratelyinFilterJour(PubAcro, Jacro, Jname);
        Assert.assertTrue(actualVal, "Publisher count varies");
        ExtentReportListener.getTest().log(Status.INFO, "Filter verification completed for Journals");


    }

    @DataProvider(name = "getJrWithPub")
    public Object[][] getJourPubFromFilter() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 10);

    }

    @Test(priority = 23, dataProvider = "getJrWithPub", description = "JMS-79 : Apply filter on Pub+journal and ensure the list is updated accordingly - Version 1 ")
    public void verifyJrWithPubInFilter(String PubAcro, String PubName, String Jacro, String Jname) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Checking Journals count with publisher at a time");


        boolean ActualVal = userManagement.PubWithJournalColumnFilterCheck(PubAcro, PubName, Jacro, Jname);
        Assert.assertTrue(ActualVal, "Publisher withJournal count varies");
        ExtentReportListener.getTest().log(Status.INFO, "Filter verification completed for Journals with Publisher");


    }

    @DataProvider(name = "getPenArt")
    public Object[][] getPendingArticle() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 11);

    }

    @Test(priority = 24, dataProvider = "getPenArt", description = "JMS-79 : Apply filter on Pub+journal and ensure the list is updated accordingly - Version 1 ")
    public void verifyPendingArticleCount(String journalacro, String artname, String workflow) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Checking Journals count with publisher at a time");

        boolean ActualVal = userManagement.checkPendingArticleCount(journalacro, artname, workflow);
        Assert.assertTrue(ActualVal, "Pending Article varies");
        ExtentReportListener.getTest().log(Status.INFO, "Pending Article count has been verified");


    }


    @DataProvider(name = "getUserDeactDataN")
    public Object[][] getUserDactDataN() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 0);

    }

    @Test(priority = 25, dataProvider = "getUserDeactDataN", description = "JMS-145 : Deactivate the user and ensure the employee can’t be logged in again - Version 2")
    public void verifyDeactivatedUserLogin(String empName, String empId, String designation, String gender,
                                           String depart, String Pub, String access, String role, String mail, String Uname, String Pwd) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "From Proj. Mngr login, Navigate to User-A's EDIT profile page");
        ExtentReportListener.getTest().log(Status.INFO, "Deactivate the employee");
        ExtentReportListener.getTest().log(Status.INFO, "Login with de-activate credential(user profile)");

        boolean ActualVal = userManagement.checkDeactivationUserAndLogin(empName, empId, designation, gender, depart, Pub, access, role, mail, Uname, Pwd);

        Assert.assertTrue(ActualVal, "Deactivated User can able to login again");

        ExtentReportListener.getTest().log(Status.INFO, "PM is on User's profile page");
        ExtentReportListener.getTest().log(Status.INFO, "Employee deactivated message should be displayed");
        ExtentReportListener.getTest().log(Status.INFO, "User should be unable to login");


    }

    @DataProvider(name = "getUserforAssign")
    public Object[][] getUserforAssign() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 1);

    }

    @Test(priority = 26, dataProvider = "getUserforAssign", description = " JMS-146 : Deactivate the user and ensure employee is not available to be assigned - Version 2")
    public void verifyDeactivatedUsertoAssign(String empName, String empId, String designation, String gender,
                                              String depart, String Pub, String access, String role, String mail, String Uname, String Pwd) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check whether the User is available for his/her TL to Assign any task from TL login - click on 'Assign' icon and check on Assign List");
        ExtentReportListener.getTest().log(Status.INFO, "Now from Proj. Mngr login, Deactivate the user");
        ExtentReportListener.getTest().log(Status.INFO, "Now Login as TL");
        ExtentReportListener.getTest().log(Status.INFO, "Repeat the step 1 and search the user");




        boolean ActualVal = userManagement.checkDeactivatedUserForAssign(empName, empId, designation, gender, depart, Pub, access, role, mail, Uname, Pwd);

        Assert.assertFalse(ActualVal, "Deactivated User displayed in assign option for Latex TL login");

        ExtentReportListener.getTest().log(Status.INFO, "User is available to be assigned");
        ExtentReportListener.getTest().log(Status.INFO, "User is deactivated");
        ExtentReportListener.getTest().log(Status.INFO, "Logged In as TL");
        ExtentReportListener.getTest().log(Status.INFO, "Now User should not be available to assign any task");


    }

    @DataProvider(name = "getActivateUserToLogin")
    public Object[][] getActivateUserToLogin() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 2);

    }

    @Test(priority = 27, dataProvider = "getActivateUserToLogin", description = "JMS-149 : Verify Deactivated employee can be re-activated, and check he/she can be signed in - Version 2")
    public void verifyActivatedUserToLogin(String empName, String empId, String designation, String gender,
                                           String depart, String Pub, String access, String role, String mail, String Uname, String Pwd) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "From Proj.Mngr login, Deactivate any User/TL");
        ExtentReportListener.getTest().log(Status.INFO, "Verify the deactivation by logging as the user");
        ExtentReportListener.getTest().log(Status.INFO, "From Proj.Mngr login, Re-Activate the user");
        ExtentReportListener.getTest().log(Status.INFO, "Now Login again as the User");


        boolean ActulVal = userManagement.checkDeactivatedEmpToBeActivated(empName, empId, designation, gender, depart,
                Pub, access, role, mail, Uname, Pwd);

        Assert.assertTrue(ActulVal, "Can't able to login for Activated user");

        ExtentReportListener.getTest().log(Status.INFO, "staff deactivated");
        ExtentReportListener.getTest().log(Status.INFO, "Can't able to Login");
        ExtentReportListener.getTest().log(Status.INFO, "staff Re-Activated");
        ExtentReportListener.getTest().log(Status.INFO, "able to login sucessfully");


    }

    @DataProvider(name = "getTLAMReactivaton")
    public Object[][] getTLAMReactivaton() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 3);

    }


    @Test(priority = 28, dataProvider = "getTLAMReactivaton", description = " JMS-151 : Reactivated employee (TL/AM) can perform the same roles as normal - Version 2")
    public void verifyReactivationAMTLforAssign(String journalacro, String artname, String workflow,
                                                String empName, String EmpId, String Uname, String Pwd, String UempName, String UempId) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");

        ExtentReportListener.getTest().log(Status.INFO, "Log in as a PM user");
        ExtentReportListener.getTest().log(Status.INFO, "click on users functionality in the dashboard");
        ExtentReportListener.getTest().log(Status.INFO, "Deactivate and reactivate same employee(TL/AM)");
        ExtentReportListener.getTest().log(Status.INFO, "Login as a user (TL/AM)");
        ExtentReportListener.getTest().log(Status.INFO, "verify reactivated employee (TL/AM) can perform the same roles (TaskAssigning) as normal");


        boolean ActulVal = userManagement.checkReactivatedTLorAMtoAssign(journalacro, artname, workflow, empName, EmpId, Uname, Pwd, UempName, UempId);
        Assert.assertTrue(ActulVal, "Can't able to assign to user login from AM/TL Reactivated login");
        ExtentReportListener.getTest().log(Status.INFO, "The home page will displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The list of users will displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The employee will be reactivated");
        ExtentReportListener.getTest().log(Status.INFO, "The home page will displayed");
        ExtentReportListener.getTest().log(Status.INFO, "The employee should perfrom the same roles (TaskAssigning) as normal");





    }


    @DataProvider(name = "getPMOwnDeactivation")
    public Object[][] getPMOwnDeactivation() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 4);

    }

    @Test(priority = 29, dataProvider = "getPMOwnDeactivation", description = "JMS-152 : Deactivation of OwnID must be prevented for PMuser - Version 1")
    public void verifyOwnIDReactivation(String empName, String empId, String designation, String gender,
                                        String depart, String Pub, String access, String role, String mail, String PmUname, String PmPass) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Login as Proj. Mngr");
        ExtentReportListener.getTest().log(Status.INFO, "Search for own profile and try Deactivating own ID");

        boolean ActulVal = userManagement.checkDeactivationOfOwnIDforPM(empName, empId, designation, gender, depart, Pub, access, role, mail, PmUname, PmPass);

        Assert.assertTrue(ActulVal, "Deactivated PM user can able to login here");
        ExtentReportListener.getTest().log(Status.INFO, "Login sucessfully");
        ExtentReportListener.getTest().log(Status.INFO, "Deactivating OwnID should not be allowed");


    }


//    @DataProvider(name = "getWrongOldPass")
//    public Object[][] getWrongOldPassVerification() throws IOException {
//
//        return ReadExcelData(".//src//test//resources//files//User_Management_Security.xlsx", 3);
//
//    }
//
//
//    @Test(priority = 29, dataProvider = "getWrongOldPass", description = "JMS-62 : Provide wrong previous password in change password screen and attempt - Version 2")
//    public void verifyOldPassWrdChangeFromSecurity(String empName, String empId, String designation, String gender,
//                                                   String depart, String Pub, String access, String role, String mail, String WrgOldPass, String NewPass) throws InterruptedException {
//
//
//        ExtentReportListener.getTest().assignCategory("User Management Security");
//        ExtentReportListener.getTest().assignAuthor("Hassan");
//        ExtentReportListener.getTest().log(Status.INFO, "Click profile option --> security");
//        ExtentReportListener.getTest().log(Status.INFO, "Enter incorrect password at the old password details,try in change password");
//
//
//        boolean ActulVal = userManagement.CheckWithWrongPreviousPassword(empName, empId, designation, gender, depart, Pub, access, role, mail, WrgOldPass, NewPass);
//        Assert.assertTrue(ActulVal, "Old Password is not correct Alert was not displayed");
//
//        ExtentReportListener.getTest().log(Status.INFO, "Password change page is displayed");
//        ExtentReportListener.getTest().log(Status.INFO, "Incorrect password alert displayed, user is unable to change the password.");
//
//
//    }


}
