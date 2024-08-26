package tests;


import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.ExcelParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static utils.ExcelReader.ReadExcelData;

@Listeners(ExtentReportListener.class)
public class AddJournalTest extends BaseTest {

    @BeforeMethod
    public void beforeAllTest() {

        addJournalPage = homepage.navigateToAddJournalpage();
        addJournalPage.verifyAddJournalIconIsClickable();


    }

    @Test(priority = 0, description = "Navigating to Add Journals")
    public void NavigateToAddJorIcon() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the Base icon");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the add journal icon");

        addJournalPage.verifyAddJournalIconIsClickable();
        ExtentReportListener.getTest().log(Status.INFO, "Navigated successfully to the Add Journals page");


    }

    @DataProvider(name = "getJrData")
    public Object[][] getJournalData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//newaddjournals.xlsx", 1);
    }

    @Test(priority = 1, dataProvider = "getJrData", description = "JMS-155 : Acronym should not be allowed with special characters - Version 1")
    public void verifyAcrWithSplChar(String J_acrm, String J_name) throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Login with valid credential");
        ExtentReportListener.getTest().log(Status.INFO, "Add journal acronym using the special charcaters (e.g: @#$%)");


        List<Object[]> excelData = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 0);


        for (Object[] row : excelData) {

            System.out.println("Row length: " + row.length);
            System.out.println("Row contents: " + Arrays.toString(row));

            if (!excelData.isEmpty() && excelData.get(0).length > 0) {
                String Pub = excelData.get(0)[0].toString();

                Boolean JrAck = addJournalPage.sameAcrWithSlChar(Pub, J_acrm, J_name);

                System.out.println("Acknowledgement received as: " + JrAck);


                Assert.assertTrue(JrAck, "Journal was added with spl charc");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

            String J_AcroName = addJournalPage.fromManageJournalsCheck(J_acrm);
            Assert.assertEquals(J_AcroName, J_acrm, "Journal Acroname not added with Special charcters");
            ExtentReportListener.getTest().log(Status.INFO, "Login sucessfully");
            ExtentReportListener.getTest().log(Status.INFO, "Wrong alert displayed");


        }


    }


    @Test(priority = 2, description = "JMS-157 : Same journal can be created with different publisher - Version 2")
    public void verifySameJrWithDiffPub() throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Login with valid credential");
        ExtentReportListener.getTest().log(Status.INFO, "Create a new journal with the same name or same acronym under the Pub-B");



        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 2);

        for (Object[] row : excelDataA) {

            if (row.length == 6) {

                String PubAcro1 = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubAcro2 = row[3].toString();
                String PubName = row[4].toString();
                String PubNameII = row[5].toString();

                addJournalPage.diffPubSameJournals(PubAcro1, J_acrm, J_name, PubAcro2, PubName, PubNameII);
                Assert.assertTrue(true, "Journal not added with different publisher");
                ExtentReportListener.getTest().log(Status.INFO, "Login sucessfully");
                ExtentReportListener.getTest().log(Status.INFO, "Journal can be created");



            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }
        }

    }

    @Test(priority = 3, description = "JMS-168 : Verify Publisher, Title, Acronym can’t be changed after journal is created - Version 2")
    public void verifyPubNameAcro() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click on Add journals icon");
        ExtentReportListener.getTest().log(Status.INFO, "Ensure that to fill Mandatory Text Filled");
        ExtentReportListener.getTest().log(Status.INFO, " select the  Publisher ,Title,Acronym and in Journal and click add journal button");
        ExtentReportListener.getTest().log(Status.INFO, "verify that publisher ,Title,Acronym cant be changed after journal is created");

        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 3);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                List<String> PubDetails = addJournalPage.samePubAcro(Pub, J_acrm, J_name, PubName);

                Assert.assertEquals(PubDetails.get(0), PubName, "Publisher name varies");
                System.out.println("Pub title: " + PubDetails);

                Assert.assertEquals(PubDetails.get(1), Pub, "Publisher Acroname varies");
                System.out.println("Pub ACK: " + PubDetails);

                ExtentReportListener.getTest().log(Status.INFO, "Navigate to Add journal Page");
                ExtentReportListener.getTest().log(Status.INFO, "Text field are added successfully");
                ExtentReportListener.getTest().log(Status.INFO, "Journal is created succesfully");
                ExtentReportListener.getTest().log(Status.INFO, "Publisher details were not changed even after journal created");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }

    }

    @Test(priority = 4, description = "JMS-158 : Ensure the working of recto option at issue stage - Version 1")
    public void verifyRectoBox() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check the recto checkbox whether it is clickable");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 4);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String PubAck = row[0].toString();
                String PubName = row[1].toString();
                String JAcrm = row[2].toString();
                String J_Name = row[3].toString();

                List<Boolean> RectoVisible = addJournalPage.CheckRecto(PubAck, PubName, JAcrm, J_Name);
                String isenable = String.valueOf(RectoVisible.get(0));
                System.out.println(isenable);
                String ischecked = String.valueOf(RectoVisible.get(1));
                System.out.println(ischecked);

                Assert.assertEquals(isenable, "true", "11");
                Assert.assertEquals(ischecked, "true", "222");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }
        }

    }

    @Test(priority = 5, description = "JMS-159 : Check Copyediting levels are available only when CopyEditing option is selected - Version 2")
    public void verifyCELevels() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click on Add Journal Option");
        ExtentReportListener.getTest().log(Status.INFO, "When CopyEditing is not selected");
        ExtentReportListener.getTest().log(Status.INFO, "When CopyEditing is selected");
        ExtentReportListener.getTest().log(Status.INFO, "Check the selection from edit journal page");

        List<String> text = addJournalPage.CopyEditingLevel();
        String l1 = text.get(0);
        String l2 = text.get(1);
        String l3 = text.get(2);

        SoftAssert SoftAst = new SoftAssert();

        SoftAst.assertEquals(l1, "L1", "Wrong option displayed");
        SoftAst.assertEquals(l2, "L2", "Wrong option displayed");
        SoftAst.assertEquals(l3, "L3", "Wrong option displayed");

        ExtentReportListener.getTest().log(Status.INFO, "Navigate to Journal Page");
        ExtentReportListener.getTest().log(Status.INFO, "Copyediting levels(L1,L2,L3) should be available");
        ExtentReportListener.getTest().log(Status.INFO, "L1,L2,L3 should not be available");
        ExtentReportListener.getTest().log(Status.INFO, "Verified");


    }

    @Test(priority = 6, description = "JMS-160 : Trim sizes - Validation - Version 2")
    public void verifyTrimSize() throws InterruptedException {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Click On Add Journal Option");
        ExtentReportListener.getTest().log(Status.INFO, "Ensure that Trim Sizes Width and Trim Size Height can be given numerical values");
        ExtentReportListener.getTest().log(Status.INFO, "Check the decimal values are also accepted");
        ExtentReportListener.getTest().log(Status.INFO, "Check '0' is accepted");
        ExtentReportListener.getTest().log(Status.INFO, "Check Negative values (-12) are accepted");
        ExtentReportListener.getTest().log(Status.INFO, "Try to add the Special character and Alphabets ");


        boolean isNumericType = addJournalPage.TrimSizeIsNumeric();
        Assert.assertTrue(isNumericType, "Trim size should be numeric");


        ExtentReportListener.getTest().log(Status.INFO, "Navigate to Add journal page");
        ExtentReportListener.getTest().log(Status.INFO, "Numeriacal values are adding successfuly");
        ExtentReportListener.getTest().log(Status.INFO, "Decimal values are also accepted");
        ExtentReportListener.getTest().log(Status.INFO, "User is not allowed to enter the details on the Trim sizes input field");



    }

    @Test(priority = 7, description = "JMS-161 : Give TATdate for General and ensure the same schedule can be copied to FastTrack - Version 1")
    public void verifyGeneralToFastCopyTat() {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Login with valid credential");
        ExtentReportListener.getTest().log(Status.INFO, "Add publisher detail option");
        ExtentReportListener.getTest().log(Status.INFO, "Add journal,Give TAT option for the General and Click the Forward button available");


        List<String> actualValues = addJournalPage.GenToFastCopyTat();

        List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8",
                "9", "1",
                "2", "3", "4",
                "5", "6",
                "7", "8", "9");

        Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");

        ExtentReportListener.getTest().log(Status.INFO, "Login sucessfully");
        ExtentReportListener.getTest().log(Status.INFO, "Pub Added sucessfully");
        ExtentReportListener.getTest().log(Status.INFO, " The General TAT date are copied to the Fastrack details.");


    }

    @Test(priority = 8, description = "JMS-161 - 2 : Give TATdate for FastTrack and ensure the same schedule can be copied to General - Version 1")
    public void verifyFastTrackToGeneral() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Login with valid credential");
        ExtentReportListener.getTest().log(Status.INFO, "Add publisher detail option");
        ExtentReportListener.getTest().log(Status.INFO, " Add journal,Give TAT option for the Fastrack and Click the Reverse button available");


        List<String> actualValues = addJournalPage.FastToGenCopyTat();

        List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8",
                "9", "1",
                "2", "3", "4",
                "5", "6",
                "7", "8", "9");


        Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");

        ExtentReportListener.getTest().log(Status.INFO, "Login sucessfully");
        ExtentReportListener.getTest().log(Status.INFO, "Pub Added sucessfully");
        ExtentReportListener.getTest().log(Status.INFO, "Values are imported from FastTrack to General");


    }

    @Test(priority = 9, description = "JMS-162 : After Copied, verify the TAT modification be possible separately (General to Fast) - Version 2 ")
    public void verifyTATModificationGeneralToFast() {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");

        ExtentReportListener.getTest().log(Status.INFO, "Add journal,Give TAT option for the General and Click the Forward button available");


        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 3);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                List<String> actualValues = addJournalPage.TATModificationGeneralToFastTrack(Pub, J_acrm, J_name, PubName);

                List<String> expectedValues = List.of("1", "1", "1", "1", "1", "1", "1", "1",
                        "1", "1",
                        "1", "1", "1",
                        "1", "1",
                        "1", "1", "1");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");
                ExtentReportListener.getTest().log(Status.INFO, "Edited Values for imported FastTrack days has been displayed ");

            }
        }
    }

    @Test(priority = 10, description = "JMS-162 : After Copied, verify the TAT modification be possible separately (FastTrack to General) - Version 2 ")
    public void verifyTATModificationFastTrackToGeneral() {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check modification of TAT after Copied (Fast to Gen)");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 3);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();


                List<String> actualValues = addJournalPage.TATModificationFastTrackToGeneral(Pub, J_acrm, J_name, PubName);


                List<String> expectedValues = List.of("2", "2", "2", "2", "2", "2", "2", "2",
                        "2", "2",
                        "2", "2", "2",
                        "2", "2",
                        "3", "3", "3");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");

                ExtentReportListener.getTest().log(Status.INFO, "Edited Values for imported General days has been displayed ");


            }
        }
    }

    @Test(priority = 11, description = "JMS-163 : Import TAT from Publisher & verify the details are correctly imported for Gen & FastTat - Version 3")
    public void verifyImportTATFromPub_GenToFast() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Import Publisher TAT to Gen to Fast and verify it");

        List<Object[]> excelData = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 5);

        for (Object[] row : excelData) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                List<String> actualValues = addJournalPage.TATImportFromGenToFast(Pub, J_acrm, J_name, PubName, J_name, J_acrm);

                List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");
                ExtentReportListener.getTest().log(Status.INFO, "General to FastTrack has been verified");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }


    }

    @Test(priority = 12, description = "JMS-163 : Import TAT from Publisher & verify the details are correctly imported for Gen & FastTat (Fast to Gen) - Version 3")
    public void verifyImportTATFromPub_FastToGen() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Import Publisher TAT to Fast to Gen and verify it");

        List<Object[]> excelData = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 5);

        for (Object[] row : excelData) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                List<String> actualValues = addJournalPage.TATImportFromFastToGen(Pub, J_acrm, J_name, PubName, J_name, J_acrm);

                List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");
                ExtentReportListener.getTest().log(Status.INFO, "FastTrack to general import values has been verified");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }


    }

    @DataProvider(name = "getJrNewData")
    public Object[][] getJournalNewData() throws IOException {

        return ReadExcelData(".//src//test//resources//files//newaddjournals.xlsx", 6);
    }

    @Test(priority = 13, dataProvider = "getJrNewData", description = "JMS-175 : Move the files from Latest to Archive and save the journal and verify the number of files later ")
    public void verifyFromLatestToArchFiles(String Arch_Sty) {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Import Publisher TAT to Fast to Gen and verify it");

        List<Object[]> excelData = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 5);

        for (Object[] row : excelData) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                System.out.println("for: " + Pub);
                System.out.println("for: " + J_acrm);
                System.out.println("for: " + J_name);
                System.out.println("for: " + PubName);


                String Arch_File = addJournalPage.moveLatestToArchiveAndVerify(Pub, J_acrm, J_name, PubName, Arch_Sty);
                System.out.println("Archive File received as: " + Arch_File);

                Assert.assertEquals(Arch_File, Arch_Sty, "Latest Files moved to archived files");
                ExtentReportListener.getTest().log(Status.INFO, "Archived file verified");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);

            }


        }

    }

    @DataProvider(name = "getJrNewData2")
    public Object[][] getJournalNewData2() throws IOException {

        return ReadExcelData(".//src//test//resources//files//newaddjournals.xlsx", 7);
    }

    @Test(priority = 14, dataProvider = "getJrNewData2", description = "JMS-169 : Ensure integrity - Before Journal creation, Try to change Publisher - Version 3")
    public void verifyChangePubBeforeCreateJr(String PubNewAckro, String PubNameNew2) {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Navigate to ADD Journal Page and select Pub(A); provide all the necessary details like Publisher selection till TAT - Don't click ADD button");
        ExtentReportListener.getTest().log(Status.INFO, "Now change the Publisher from Pub(A) to Pub(B) and observe the following details like TAT's and documents (from preconditions) are changed");
        ExtentReportListener.getTest().log(Status.INFO, "If Step:2 is failed, proceed by adding the journal");



        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 8);

        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();

                System.out.println("for : " + Pub);
                System.out.println("for : " + J_acrm);
                System.out.println("for : " + J_name);

                addJournalPage.JrCreationByChangingPub(Pub, J_acrm, J_name, PubNewAckro, PubNameNew2);
                Assert.assertTrue(true, "Journal not added once we change new publisher");

                ExtentReportListener.getTest().log(Status.INFO, "All the necessary datas been provided");
                ExtentReportListener.getTest().log(Status.INFO, "Journal Ready to be created");
                ExtentReportListener.getTest().log(Status.INFO, "Either the datas should be changed automatically or All datas should be Resetted ");
                ExtentReportListener.getTest().log(Status.INFO, "Journal should not be created");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }
    }

    @Test(priority = 15, description = "JMS-171 : Create a journal under publisher without importing files - Version 3")
    public void verifyJournalWithoutPubImport() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Navigate to add journal page and select the publisher");
        ExtentReportListener.getTest().log(Status.INFO, "provide all necessary details except the files");
        ExtentReportListener.getTest().log(Status.INFO, "without importing any file from publisher or Uploading any new file, Try creating the journal");

        List<Object[]> excelData = ExcelParser.getExcelData(".//src//test//resources//files//AddPub.xlsx", 0);

        for (Object[] row : excelData) {
            if (row.length == 8) {
                String acro = row[0].toString();
                String pub = row[1].toString();
                String c = row[2].toString();
                String d = row[3].toString();
                String e = row[4].toString();
                String f = row[5].toString();
                String g = row[6].toString();
                String h = row[7].toString();


                addJournalPage.addPublisher(acro, pub, c, d, e, f, g, h);
                System.out.println("Executed is done");

            } else {
                System.out.println("Row does not have the expected number of columns: " + row.length);
            }

        }
        ExtentReportListener.getTest().log(Status.INFO, "All details are provided");



        List<Object[]> excelDataAB = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 9);

        for (Object[] row : excelDataAB) {
            if (row.length == 3) {
                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();


                boolean ActuVal = addJournalPage.createJrWithoutImportFiles(Pub, J_acrm, J_name);
                Assert.assertTrue(ActuVal, "Error message has been displayed to import files");

                ExtentReportListener.getTest().log(Status.INFO, "Journal Can't be created without any files. Any file is needed");

            }


        }

    }

    @Test(priority = 16, description = "JMS-153 : Create and verify the Journal with valid details")
    public void verifyAddJournals() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Login with valid credentials");
        ExtentReportListener.getTest().log(Status.INFO, "Click add Journals");
        ExtentReportListener.getTest().log(Status.INFO, "Create add journals with valid credentials");
        ExtentReportListener.getTest().log(Status.INFO, "Create add journals with valid credentials");
        ExtentReportListener.getTest().log(Status.INFO, "Verify the add journals with valid credentials");


        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 10);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                System.out.println("for : " + Pub);
                System.out.println("for : " + J_acrm);
                System.out.println("for : " + J_name);
                System.out.println("for : " + PubName);


                addJournalPage.checkJournalAdded(Pub, J_acrm, J_name, PubName);
                Assert.assertTrue(true, "Journal not added once we change new publisher");
                ExtentReportListener.getTest().log(Status.INFO, "Login Successfully");
                ExtentReportListener.getTest().log(Status.INFO, "Add Journal displayed");
                ExtentReportListener.getTest().log(Status.INFO, "Journal added succesfully");
                ExtentReportListener.getTest().log(Status.INFO, "Details are correct");
                ExtentReportListener.getTest().log(Status.INFO, "Journal added is verified");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }


    }


    @DataProvider(name = "getJrNewData3")
    public Object[][] getJournalNewData3() throws IOException {

        return ReadExcelData(".//src//test//resources//files//newaddjournals.xlsx", 11);
    }


    @Test(priority = 17, dataProvider = "getJrNewData3", description = "JMS-156 : Journal can't be duplicated within the publisher - Version 2")
    public void verifyJrCantDuplicatedWithPub(String Pub, String J_acrm, String J_name, String PubName) {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Create a journal under the Pub-A with exact same name");
        ExtentReportListener.getTest().log(Status.INFO, "Create a journal under the Pub-A with exact same Acronym");
        List<Boolean> JrVisible = addJournalPage.JournalCantDuplicated(Pub, J_acrm, J_name, PubName);


        for (Boolean isVisible : JrVisible) {
            Assert.assertTrue(isVisible, "Expected journal to be visible but it's not.");
            ExtentReportListener.getTest().log(Status.INFO, "Jrnl can't be created with the same name");
            ExtentReportListener.getTest().log(Status.INFO, "Jrnl can't be created with the same acronym");

        }


    }

    @Test(priority = 18, description = "JMS-170 : Even if the publisher has files, it should not be imported unless IMPORT icon is clicked")
    public void verifyImportIconForImportFiles() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check whether files has been imported before import icon");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 12);


        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();

                System.out.println("for : " + Pub);
                System.out.println("for : " + J_acrm);
                System.out.println("for : " + J_name);


                List<Boolean> ImpIconClickFiles = addJournalPage.ImportIconClickWithoutFiles(Pub, J_acrm, J_name);

                SoftAssert softAssert = new SoftAssert();

                boolean ImpIconSty = ImpIconClickFiles.get(0);
                softAssert.assertFalse(ImpIconSty, "Files has been displayed before import icon");
                System.out.println(ImpIconSty);

                boolean ImpIconSty_1 = ImpIconClickFiles.get(1);
                softAssert.assertTrue(ImpIconSty_1, "Files not displayed even after import icon");
                System.out.println(ImpIconSty_1);

                boolean ImpIconGuide = ImpIconClickFiles.get(2);
                softAssert.assertFalse(ImpIconGuide, "Files has been displayed before import icon");
                System.out.println(ImpIconGuide);

                boolean ImpIconGuide_1 = ImpIconClickFiles.get(3);
                softAssert.assertTrue(ImpIconGuide_1, "Files has been displayed before import icon");
                System.out.println(ImpIconGuide_1);

                ExtentReportListener.getTest().log(Status.INFO, "Import icon verified for uploading files");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }

    }

    @Test(priority = 19, description = "JMS-165 : Select General TAT for journal and verify the same by creating new article (Add Article)")
    public void verifyGenTATByAddingArticle() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check whether files has been imported before import icon (Gen to Fast)");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 13);


        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String artname = row[0].toString();


                List<String> GenTATVerify = addJournalPage.genTATverifyByAddArticle(artname);
                List<String> expectedValues = List.of("1", "1", "1", "1", "1", "1", "1", "1", "1",
                        "1", "1", "1", "1", "1", "1", "1", "1", "1");

                SoftAssert softAssert = new SoftAssert();

                softAssert.assertEquals(GenTATVerify, expectedValues, "Values retrieved from page class do not match expected values");
                ExtentReportListener.getTest().log(Status.INFO, "Import icon verified for uploading files in add article");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }

    }

    @Test(priority = 20, description = "JMS-166 : Select Fast TAT for journal and verify the same by creating new article (Add Article)")
    public void verifyFastTATByAddingArticle() {

        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check whether files has been imported before import icon (Fast to Gen)");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 13);


        for (Object[] row : excelDataA) {

            if (row.length == 1) {

                String artname = row[0].toString();


                List<String> FastTATVerify = addJournalPage.fastTATverifyByAddArticle(artname);
                List<String> expectedValues = List.of("1", "1", "1", "1", "1", "1", "1", "1", "1",
                        "1", "1", "1", "1", "1", "1", "1", "1", "1");

                SoftAssert softAssert = new SoftAssert();

                softAssert.assertEquals(FastTATVerify, expectedValues, "Values retrieved from page class do not match expected values");
                ExtentReportListener.getTest().log(Status.INFO, "Import icon verified for uploading files in add article (Fast to Gen)");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }


    }

    @Test(priority = 21, description = " JMS-172 : Import template from publisher and verify the following details from publisher itself - Version 2")
    public void verifyImportStyTemplateFromPub() throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Try Adding Journal under this publisher");
        ExtentReportListener.getTest().log(Status.INFO, "import template from publisher");
        ExtentReportListener.getTest().log(Status.INFO, "verify the details from publisher itself((file is in Latest/Archive, file count, file name)\u200B");
        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 12);
        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String j_acrm = row[1].toString();
                String j_name = row[2].toString();


                addJournalPage.ImportStyFromPub(Pub, j_acrm, j_name);
                int latestFileCount = addJournalPage.getFileCount();
                int expectedFileCount = 1;
                System.out.println("Actual Latest files count: " + latestFileCount);
                Assert.assertEquals(latestFileCount, expectedFileCount, "The number of files does not match the expected count.");


                List<String> latestFilesTexts = addJournalPage.getFileTexts();
                List<String> expectedTexts = List.of("ems_journal.sty");

                System.out.println("Actual lastName: " + latestFilesTexts);
                Assert.assertTrue(latestFilesTexts.containsAll(expectedTexts), "The list of file texts does not match the expected texts.");
                ExtentReportListener.getTest().log(Status.INFO, "Navigated to Journal Page");
                ExtentReportListener.getTest().log(Status.INFO, "Style templates successfully");
                ExtentReportListener.getTest().log(Status.INFO, "A.sty, B.sty - in Latest; C.sty - in Archive");

            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }


    }


    @Test(priority = 22, description = "JMS-173 : Import GuideLines from publisher and verify the following details - Version 2")
    public void verifyImportGuideLineFromPub() {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Navigate to Add journal page and try adding under this publisher");
        ExtentReportListener.getTest().log(Status.INFO, "Import Guideliness from publisher");
        ExtentReportListener.getTest().log(Status.INFO, "Verify the details from publisher itself");


        List<Object[]> excelDataA = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 12);
        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String j_acrm = row[1].toString();
                String j_name = row[2].toString();


                addJournalPage.ImportGuideFromPub(Pub, j_acrm, j_name);
                int latestguideFileCount = addJournalPage.getGuideFileCount();
                int expectedFileCount = 1;
                System.out.println("Actual Latest files count: " + latestguideFileCount);
                Assert.assertEquals(latestguideFileCount, expectedFileCount, "The number of files does not match the expected count.");


                List<String> latestGuideFilesTexts = addJournalPage.getGuideFileTexts();
                List<String> expectedTexts = List.of("Guideline....docx");


                System.out.println("Actual latestGuide: " + latestGuideFilesTexts);
                System.out.println("Expected latestGuide: " + expectedTexts);


                Assert.assertTrue(latestGuideFilesTexts.containsAll(expectedTexts), "The list of file texts does not match the expected texts.");


                ExtentReportListener.getTest().log(Status.INFO, "Navigated to Journal Page");
                ExtentReportListener.getTest().log(Status.INFO, "Imported Guidlines from Publisher successfully");



            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }


    }


    @Test(priority = 23, description = "JMS-164 : Verify modification of TAT by different articles under two journals from same publisher - Version 3")
    public void verifyModifiedTATtwoJournalAddAritcle() throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Create a publisher with 'Gen Ltx Norm - 1' day");
        ExtentReportListener.getTest().log(Status.INFO, "Create Journal(A) under the same publisher and import the days without any change");
        ExtentReportListener.getTest().log(Status.INFO, "Create Art-A under the same journal and import the days without any change;Verify the 'Gen Ltx Norm - 1' day");
        ExtentReportListener.getTest().log(Status.INFO, "Create Journal(B) under the same publisher with the following change; 'Gen LTX Norm - 2' days");
        ExtentReportListener.getTest().log(Status.INFO, "Create Art-B under the Jrnl-B and import the days without any change");




        List<Object[]> excelData = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 14);

        for (Object[] row : excelData) {

            if (row.length == 43) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();
                String a = row[4].toString();
                String b = row[5].toString();
                String c = row[6].toString();
                String d = row[7].toString();
                String e = row[8].toString();
                String f = row[9].toString();
                String g = row[10].toString();
                String h = row[11].toString();
                String i = row[12].toString();
                String j = row[13].toString();
                String k = row[14].toString();
                String l = row[15].toString();
                String m = row[16].toString();
                String n = row[17].toString();
                String o = row[18].toString();
                String p = row[19].toString();
                String q = row[20].toString();
                String r = row[21].toString();
                String s = row[22].toString();
                String t = row[23].toString();
                String u = row[24].toString();
                String v = row[25].toString();
                String w = row[26].toString();
                String x = row[27].toString();
                String y = row[28].toString();
                String z = row[29].toString();
                String aa = row[30].toString();
                String bb = row[31].toString();
                String cc = row[32].toString();
                String dd = row[33].toString();
                String ee = row[34].toString();
                String ff = row[35].toString();
                String gg = row[36].toString();
                String hh = row[37].toString();
                String ii = row[38].toString();
                String jj = row[39].toString();
                String artName = row[40].toString();
                String journalacro = row[41].toString();
                String workflow = row[42].toString();


                List<String> actualValuesModifiedTAT = addJournalPage.modificationOfTATbyAddArticleTwoJournals(Pub, J_acrm, J_name, PubName, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o,
                        p, q, r, s, t, u, v, w, x, y, z, aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, artName, journalacro, workflow);

                List<String> expectedValuesModifiedTAT = List.of(a, b, c, d, e, f, g, h, i,
                        j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, aa, bb, cc, dd, ee, ff, gg, hh, ii, jj);

                Assert.assertEquals(actualValuesModifiedTAT, expectedValuesModifiedTAT, "Values retrieved from page class do not match expected values");


                ExtentReportListener.getTest().log(Status.INFO, "Publisher is created");

                ExtentReportListener.getTest().log(Status.INFO, "Jrnl-A is created");

                ExtentReportListener.getTest().log(Status.INFO, "Art-A is created; TAT is verified");

                ExtentReportListener.getTest().log(Status.INFO, "Jrnl-B is created");

                ExtentReportListener.getTest().log(Status.INFO, "Art-B is created; TAT is verified as 2 days");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }
    }

    @Test(priority = 24, description = "JMS-164 : Verify unmodified of TAT - Create two journals, import TAT for both journals (Add article) - UnModified")
    public void verifyUnModifiedTATtwoJournalAddAritcle() throws InterruptedException {


        ExtentReportListener.getTest().assignCategory("Add Journals");
        ExtentReportListener.getTest().assignAuthor("Hassan");
        ExtentReportListener.getTest().log(Status.INFO, "Check the modification of TAT by adding 2 journals from add article - Modifed TAT");

        List<Object[]> excelData = ExcelParser.getExcelData(".//src//test//resources//files//newaddjournals.xlsx", 15);

        for (Object[] row : excelData) {

            if (row.length == 7) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();
                String artName = row[4].toString();
                String journalacro = row[5].toString();
                String workflow = row[6].toString();


                List<String> actualValuesUnModifiedTAT = addJournalPage.unmodifiedTATbyAddArticleTwoJournals(Pub, J_acrm, J_name, PubName, artName, journalacro, workflow);

                List<String> expectedValuesUnModifiedTAT = List.of("9", "9", "9", "9", "9", "9", "9", "9", "9",
                        "9", "9", "9", "9", "9", "9", "9", "9", "9",
                        "9", "9", "9", "9", "9", "9", "9", "9", "9",
                        "9", "9", "9", "9", "9", "9", "9", "9", "9");

                Assert.assertEquals(actualValuesUnModifiedTAT, expectedValuesUnModifiedTAT, "Unmodified list size has been varies");
                ExtentReportListener.getTest().log(Status.INFO, "UnModified TAT for journals has been verified from add articles");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }
    }


}






















