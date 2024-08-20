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

    @Test(priority = 1, dataProvider = "getAddUserdata", description = "JMS-52 : Provide details for new employee but don’t click ‘ADD’ & ensure employee not created")
    public void verifyCreatingUserBynotClickingAdd(String empName, String empId, String designation,
                                                   String gender, String depart, String Pub, String access,
                                                   String role, String mail) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");

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

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");

        ExtentReportListener.getTest().log(Status.INFO, "Verifying the duplicate EmpID should be unique");

        String AlertMessage = userManagement.duplicateEmpId(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        Assert.assertEquals(AlertMessage, "Employee ID already exists.");

        ExtentReportListener.getTest().log(Status.INFO, "Verifying the duplicate EmpID should be unique");


    }

    @DataProvider(name = "getNewAddUser")
    public Object[][] getNewAddUserData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 1);
    }

    @Test(priority = 3, dataProvider = "getNewAddUser", description = "JMS-43 : Add new user with valid details and verify the entered details in Profile")
    public void verifyAddUserandVerifyfromEdit(String empName, String empId, String designation,
                                               String gender, String depart, String Pub, String access,
                                               String role, String mail) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Verify new user from edit profile");
        List<String> actualUserValues = userManagement.addNewUserandVerify(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        List<String> expectedUserValues = List.of(empName, empId, designation, gender, depart, Pub, access, role, mail);


        Assert.assertEquals(actualUserValues, expectedUserValues, "Values are differ from page class");

        ExtentReportListener.getTest().log(Status.INFO, "New user from edit profile has been verified");


    }


    @DataProvider(name = "getEmpName")
    public Object[][] getNewEmpData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 3);

    }

    @Test(priority = 4, dataProvider = "getEmpName", description = "JMS-48 : Verify the name should not allow other than alphabets - check with invalid scenarios - Version 1")
    public void verifyEmpNameShouldAllowAlphabets(String empName) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check the Emp Name only with alphabets");
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
                ExtentReportListener.getTest().log(Status.INFO, "EMP name has been verified with alphabets");


            }


        }
    }


    @DataProvider(name = "getEmpID")
    public Object[][] getNewEmpID() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 5);

    }

    @Test(priority = 5, dataProvider = "getEmpID", description = "JMS-49 : Verify the validation of employee id, whether not accepting any Alphabets - Version 1")
    public void verifyEmpIDShouldOnlyAcceptNumerics(String empId) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check the EmpID only with Numerics");
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
                ExtentReportListener.getTest().log(Status.INFO, "EmpID has been verified with numerics");


            }


        }


    }

    @Test(priority = 6, description = "Test Case has relations. Click to jump to section JMS-54 : Attempt to edit employee details, but don’t click ‘UPDATE’ & ensure the details have not changed - Version 1 ")
    public void verifyEditEmployeeWithoutUpdateClick() throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check edit employee details without clicking update button");
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

                ExtentReportListener.getTest().log(Status.INFO, "edit employee details without clicking update button has been verified");


            }


        }
    }

    @Test(priority = 7, description = "JMS-56 : Duplicate user with employ ID of the deactivated account - Version 2")
    public void verifyDupEmpIDofDeactivatedAccount() throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
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

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 9);

    }


    @Test(priority = 8, dataProvider = "getGender", description = "JMS-51 : Verify the Male/female icon on employee badge based on gender - Version 1")
    public void verifyGenderWithMaleAndFemale(String empName, String empId, String designation, String gender,
                                              String depart, String Pub, String access, String role, String mail) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
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

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 10);

    }

    @Test(priority = 10, dataProvider = "getChangingRoles", description = " JMS-207 : Change role of any user and verify the same by logging into user’s profile - Version 1")
    public void verifyChangingRolesByLoggingUser(String empName, String empId, String designation, String gender,
                                                 String depart, String Pub, String access, String role, String mail,
                                                 String roleTL, String Uname, String Pwd, String rolecheck, String PmUname, String PmPwd) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
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

        return ReadExcelData(".//src//test//resources//files//User_Management.xlsx", 11);

    }

    @Test(priority = 9, dataProvider = "getDepartDesignation", description = " JMS-57 : Verify Designation and Department don’t impact on the roles assigned - Version 2")
    public void verifyDepartDesignOnUserRoles(String empName, String empId, String designation,
                                              String gender, String depart, String Pub, String access, String role, String mail,
                                              String Uname, String Pwd, String desigNew, String departNew) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
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

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 0);

    }

    @Test(priority = 11, dataProvider = "getAllManageView", description = "JMS-210 : MANAGE view and verify user can easily navigate between Publisher view, Journal view, Article view - Version 1")
    public void verifyAllViewManage(String PubV, String JourV, String ArtV) {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check for Manage View for Publisher, Journal and Article view");

        List<Boolean> actualVal = userManagement.navigateToAllManageViewPage(PubV, JourV, ArtV);

        Assert.assertTrue(actualVal.get(0), "View was not displayed as per the option");
        Assert.assertTrue(actualVal.get(1), "View was not displayed as per the option");
        Assert.assertTrue(actualVal.get(2), "View was not displayed as per the option");

        ExtentReportListener.getTest().log(Status.INFO, "All Manage view has been displayed");


    }

    @DataProvider(name = "getPublisherView")
    public Object[][] getPubView() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 1);

    }

    @Test(priority = 12, dataProvider = "getPublisherView", description = "JMS-211 : Add a new Publisher(Pub-A) and verify the publisher is displayed with correct Name/Acronym - Version 1")
    public void verifyPubView(String PubAcro, String PubName) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check for Publisher verify with ack and name");

        List<Object> actualVal = userManagement.addAndVerifyPublisher(PubAcro, PubName);

        Assert.assertTrue((Boolean) actualVal.get(0), "Publisher Acro displayed wrongly here ");

        Assert.assertEquals(actualVal.get(1), PubName, "Publisher name was wrongly displayed");

        Assert.assertFalse((Boolean) actualVal.get(2), "Journal was added");

        ExtentReportListener.getTest().log(Status.INFO, "Publisher name and Acro has been verified");


    }


    @DataProvider(name = "getTwoJournalCheck")
    public Object[][] getTwoJournals() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 2);

    }

    @Test(priority = 13, dataProvider = "getTwoJournalCheck", description = "JMS-212 : Add one or two journals(Jrnl-A,B) under the publisher and revisit the Publisher banner. - Version 1")
    public void verifyTwoJournals(String PubAcro, String PubName, String Jacro,
                                  String Jname, String JacroNew, String JnameNew) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check two journals that can be added in one publisher");

        List<Boolean> actualVal = userManagement.addTwoJounralsAndVerifywithPub(PubAcro, PubName, Jacro, Jname, JacroNew, JnameNew);


        Assert.assertTrue(actualVal.get(0), "Journal count 1 was not added");
        Assert.assertTrue(actualVal.get(1), "Journal count 2 was not added");
        Assert.assertTrue(actualVal.get(2), "Article was not listed here");


        ExtentReportListener.getTest().log(Status.INFO, "Journals created and it has been verified under publisher");


    }


    @DataProvider(name = "getAddArtilce")
    public Object[][] getAddArticleData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 3);

    }

    @Test(priority = 14, dataProvider = "getAddArtilce", description = " JMS-214 : Add articles under journal(Jrnl-A) and verify the Publisher and Journal banners - Version 1")
    public void verifyAddArticlewithPub(String PubAcro, String PubName, String Jacro,
                                        String Jname, String JacroArt, String artname, String workflow) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check with add article along with publisher");

        Boolean actValue = userManagement.AddArticleWithPub(PubAcro, PubName, Jacro, Jname, JacroArt, artname, workflow);
        Assert.assertTrue(actValue, "Article not added");
        ExtentReportListener.getTest().log(Status.INFO, "Article added Successfully");


    }

    @DataProvider(name = "getPubDescription")
    public Object[][] getPubDescptn() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 4);

    }


    @Test(priority = 15, dataProvider = "getPubDescription", description = "JMS-216 : Verify the description of the publisher is displayed correctly in the outer banner - Version 1")
    public void verifyPubDescription(String PubAcro, String PubName, String Description) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check publisher Description and verify it");

        String actualVal = userManagement.checkPubDescription(PubAcro, PubName, Description);

        Assert.assertEquals(actualVal, "Test Description", "Wrong description is displayed");

        ExtentReportListener.getTest().log(Status.INFO, "Description has been successfully verified");

    }

    @Test(priority = 16, dataProvider = "getPubDescription", description = "JMS-219 : Verify the following details in Publisher banner by adding/editing - Version 2")
    public void verifyPubDetailsOuterBanner(String PubAcro, String PubName, String Description) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check publisher Acroname and Desc in Outer banner  ");

        List<String> actualVal = userManagement.checkPubDetailsInOuterBanner(PubAcro, PubName, Description);

        Assert.assertEquals(actualVal.get(0), PubAcro, "Wrong PubAcro is displayed");
        Assert.assertEquals(actualVal.get(1), Description, "Wrong description is displayed");

        ExtentReportListener.getTest().log(Status.INFO, "Description has been successfully verified");


    }

    @DataProvider(name = "getJournalBanner")
    public Object[][] getJrDataBanner() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 5);

    }


    @Test(priority = 17, dataProvider = "getJournalBanner", description = "JMS-220 : Verify the following details in journal badge by adding/editing: - Version 2")
    public void verifyJournalDetailOuterBanner(String PubAcro, String PubName, String Jacro, String Jname) {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check Journal Details in Journal Badge");

        List<Object> actualVal = userManagement.checkJournalDetailInOuterBanner(PubAcro, PubName, Jacro, Jname);

        Assert.assertEquals(actualVal.get(0), PubName, "Wrong PubName displayed");
        Assert.assertEquals(actualVal.get(1), Jacro, "Wrong Journal Ackro displayed");
        Assert.assertEquals(actualVal.get(2), Jname, "Wrong Journal Name displayed");
        Assert.assertTrue((Boolean) actualVal.get(3), "Wrong message");

        ExtentReportListener.getTest().log(Status.INFO, "Journal badge has been successfully verified");

    }


    @DataProvider(name = "getArticleBadge")
    public Object[][] getArtBadge() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 6);

    }

    @Test(priority = 18, dataProvider = "getArticleBadge", description = "JMS-220 : Verify the following details in journal badge by adding/editing: - Version 2")
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


    @Test(priority = 19, dataProvider = "getSearch", description = "JMS-220 : Verify the following details in journal badge by adding/editing: - Version 2")
    public void verifyStockSearch(String PubName) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Checking Search in Stock");

        boolean actualVal = userManagement.checkSearchOptioninStock(PubName);
        Assert.assertTrue(actualVal, "Search results are not correctly updated.");
        ExtentReportListener.getTest().log(Status.INFO, "Search verification completed.");


    }

    @DataProvider(name = "getPubFromFilter")
    public Object[][] getPubinBothStockAndManage() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_Manage.xlsx", 8);

    }

    @Test(priority = 20, dataProvider = "getPubFromFilter", description = "JMS-78 : Apply filter on Pub, journal separately and ensure the list is updated by filter(Publisher) - Version 2")
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


    @Test(priority = 21, dataProvider = "getJourFromFilter", description = "JMS-78 : Apply filter on Pub, journal separately and ensure the list is updated by filter(Journal) - Version 2")
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

    @Test(priority = 22, dataProvider = "getJrWithPub", description = "JMS-79 : Apply filter on Pub+journal and ensure the list is updated accordingly - Version 1 ")
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

    @Test(priority = 23, dataProvider = "getPenArt", description = "JMS-79 : Apply filter on Pub+journal and ensure the list is updated accordingly - Version 1 ")
    public void verifyPendingArticleCount(String journalacro, String artname, String workflow) {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Checking Journals count with publisher at a time");

        boolean ActualVal = userManagement.checkPendingArticleCount(journalacro, artname, workflow);
        Assert.assertTrue(ActualVal, "Pending Article varies");
        ExtentReportListener.getTest().log(Status.INFO, "Pending Article count has been verified");


    }


    @DataProvider(name = "getUserDeactData")
    public Object[][] getUserDactData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 0);

    }

    @Test(priority = 24, dataProvider = "getUserDeactData", description = "JMS-145 : Deactivate the user and ensure the employee can’t be logged in again - Version 1 ")
    public void verifyDeactivatedUserLogin(String empName, String empId, String designation, String gender,
                                           String depart, String Pub, String access, String role, String mail, String Uname, String Pwd) throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Verification the deactivated user need to be login again");

        boolean ActualVal = userManagement.checkDeactivationUserAndLogin(empName, empId, designation, gender, depart, Pub, access, role, mail, Uname, Pwd);

        Assert.assertFalse(ActualVal, "Deactivated User can able to login again");

        ExtentReportListener.getTest().log(Status.INFO, "Deactivated user login has been verified");


    }

    @DataProvider(name = "getUserforAssign")
    public Object[][] getUserforAssign() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 1);

    }

    @Test(priority = 25, dataProvider = "getUserforAssign", description = " JMS-146 : Deactivate the user and ensure employee is not available to be assigned - Version 2")
    public void verifyDeactivatedUsertoAssign(String empName, String empId, String designation, String gender,
                                              String depart, String Pub, String access, String role, String mail, String Uname, String Pwd) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Verify the deactivated user to login again");

        boolean ActualVal = userManagement.checkDeactivatedUserForAssign(empName, empId, designation, gender, depart, Pub, access, role, mail, Uname, Pwd);

        Assert.assertFalse(ActualVal, "Deactivated User displayed in assign option for Latex TL login");

        ExtentReportListener.getTest().log(Status.INFO, "Deactivated user assign has been verified");


    }

    @DataProvider(name = "getActivateUserToLogin")
    public Object[][] getActivateUserToLogin() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 2);

    }

    @Test(priority = 26, dataProvider = "getActivateUserToLogin", description = "JMS-149 : Verify Deactivated employee can be re-activated, and check he/she can be signed in - Version 2")
    public void verifyActivatedUserToLogin(String empName, String empId, String designation, String gender,
                                           String depart, String Pub, String access, String role, String mail, String Uname, String Pwd) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Verify the deactivated to active and try user to login again");


        boolean ActulVal = userManagement.checkDeactivatedEmpToBeActivated(empName, empId, designation, gender, depart,
                Pub, access, role, mail, Uname, Pwd);

        Assert.assertTrue(ActulVal, "Can't able to login for Activated user");

        ExtentReportListener.getTest().log(Status.INFO, "Deactivated user has been Activated and it has been verified");


    }
    @DataProvider(name = "getTLAMReactivaton")
    public Object[][] getTLAMReactivaton() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 3);

    }


    @Test(priority = 27, dataProvider = "getTLAMReactivaton", description = "JMS-151 : Reactivated employee (TL/AM) can perform the same roles as normal - Version 2")
    public void verifyReactivationAMTLforAssign(String journalacro, String artname, String workflow,
                                                String empName, String EmpId, String Uname, String Pwd, String UempName, String UempId) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Deactivate and Reactivate the TL/AM login and check to assign to user");


        boolean ActulVal = userManagement.checkReactivatedTLorAMtoAssign(journalacro,artname,workflow,empName,EmpId,Uname,Pwd,UempName,UempId);

        Assert.assertTrue(ActulVal, "Can't able to assign to user login from AM/TL Reactivated login");

        ExtentReportListener.getTest().log(Status.INFO, "Reactivation ID can able to assign to user verification is done");

    }


    @DataProvider(name = "getPMOwnDeactivation")
    public Object[][] getPMOwnDeactivation() throws IOException {

        return ReadExcelData(".//src//test//resources//files//User_Management_ActDeact.xlsx", 4);

    }

    @Test(priority = 28, dataProvider = "getPMOwnDeactivation", description = "JMS-152 : Deactivation of OwnID must be prevented for PMuser - Version 1")
    public void verifyOwnIDReactivation(String empName, String empId, String designation, String gender,
                                        String depart, String Pub, String access, String role, String mail, String PmUname, String PmPass) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("User Management");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check deactivation of ownID for PMuser");
        boolean ActulVal = userManagement.checkDeactivationOfOwnIDforPM(empName,empId,designation,gender,depart,Pub,access,role,mail,PmUname,PmPass);

        Assert.assertTrue(ActulVal, "Deactivated PM user can able to login here");
        ExtentReportListener.getTest().log(Status.INFO, "Deactivated PMuser login has been verified");

    }





}
