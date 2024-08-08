package org.jms.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.FileChooser;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UserManagementPage {


    private Page page;
    FileChooser fileChooser;


    private String userdashboard = "//div[@id='root']//following::p[text()='Users']";
    private String addUser = "//main[@class='_main-content-container_105y3_23']//button[normalize-space()='Add User']";
    private String enterEmpName = "id=user-name";
    private String enterEmpId = "id=employee-id";
    private String designationDropdown = "id=user-designation";
    //    private String publisher = "id=react-select-3-placeholder";
//    private String access = "id=react-select-5-placeholder";
    private String addCloseButton = "(//span[normalize-space()='×'])[1]";
    private String department = "id=user-department";
    private String Role = "id=user-role";
    private String AddOkButton = "//form[@data-testid='add-user-user-form']//button[@type='submit']";
    private String editUserNameCard = "//div[@class='_content_d74oi_73']";
    private String sakthiuser = "(//p[text()='Sakthi'])[2]";
    private String Publisher = "//label[text()='Publisher']//following::div[1]";
    private String Access = "//label[text()='Access']//following::div[1]";
    private String Gender = "id=user-gender";
    private String EMail = "id=user-mail";
    private String ActivateIcon = "//div/span[@data-testid='edit-user-active-user']";
    private String DeactivaeIcon = "//div/span[@data-testid='edit-user-in-active-user']";
    private String UpdateBtn = "button[type='submit']";
    private String Logout = "//p[normalize-space()='Logout']";
    private String username = "id=siginIn_id";
    private String password = "id=signin_pwd";
    private String SignIn = "//button[normalize-space()='Sign in']";
    private String profile = "//p[normalize-space()='Profile']";
    private String Manage = "//p[normalize-space()='Manage']";

    private String SelectView = "id=select_view";
    private String PubView = "//p[@for='Publisher View']";
    private String JourView = "//p[@for='Journals View']";
    private String ArticleView = "//p[@for='Articles View']";

    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addpubicon = "id=add_publisher";


    private String pub_acronym = "//input[@data-testid='publisher-acronym']";
    private String pub_name_textbox = "//input[@data-testid='publisher-name']";
    private String pub_mail_textbox = "//input[@data-testid='email-account']";
    private String desc_inputbox = "//*[@name='description']";
    private String selectdateinput = "//input[@type='date']";
    private String pub_location = "//input[@data-testid='publisher-location']";
    private String ftpusername = "//input[@data-testid='ftp-user-name']";
    private String ftppassword = "//input[@data-testid='ftp-password']";
    private String ftp_initial_directory = "//input[@data-testid='ftp-initial-directory']";
    private String daysforlatexnormalization = "(//h2[text()='General']//following::input[@data-testid='days-for-latex-normalization'])[1]";
    private String daysforgraphics = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-graphics'])[1]";
    private String daysforPreediting = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-pre-editing'])[1]";
    private String daysforcopyediting = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-copyediting'])[1]";
    private String daysfortypesettings = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-typesetting'])[1]";
    private String daysforqc = "(//h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforaupag = "(//h2[text()='General']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String daysforauqc = "(//h2[text()='General']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforpepag = "(//h2[text()='General']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String daysforpeqc = "(//h2[text()='General']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String daysforonlinepag = "(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-Pagination'])[1]";
    private String daysforonlineqc = "(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-QC'])[1]";
    private String daysforonlinexml = "(//h2[text()='General']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-days-for-xml'])[1]";
    private String IssuePag = "(//h2[text()='General']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String IssueQC = "(//h2[text()='General']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String printpag = "(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-pagination'])[1]";
    private String printQC = "(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-qc'])[1]";
    private String printxml = "(//h2[text()='General']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-xml'])[1]";
    private String addbutton = "//*[@data-testid='submit-button']";

    private String JralertCloseButton = "//h2[text()='JMS - Add Journal']//following::span[1]";
    private String PubalertCloseButton = " //*[text()='JMS - Add Publisher']//following::span[1]";
    private String JrDuplicateralertCloseButton = "//h2[text()='JMS - Duplicate File']//following::span[1]";




    private String Imageuploadbutton = "//button[text()='Upload Image']";

    private String styletemplate = "//*[@id='styles-upload']//preceding::div[1]";

    private String guidelinesdoc = "//*[@id='guidelines-upload']//preceding::div[1]";
    private String CopyTat = "//*[@title='Replicate values from General to Fasttrack']";
    private String CopyTatConfirm = "//*[text()='Yes']";
    private String managemenu = "//div[@id='root']//following::p[text()='Manage']";

    private String addjouricon = "id=add_journal";


    private String publisher_1 = "id=publisher";

    private String jor_acrm = "//input[contains(@data-testid,'journal-acronym')]";
    private String jor_fullname = "//input[@data-testid='journal-full-name']";
    private String jor_received_date = "//input[@data-testid='journal-received-date']";
    private String recto_facing = "//input[@type='checkbox']/..";
    private String layout_1 = "id=layout";
    private String layout_2 = "//*[text()='Single']";
    private String trimSizeWidth = "//input[@data-testid='trim-size-width']";

    private String trimSizeHeight = "//input[@data-testid='trim-size-height']";
    private String remarks = "id=remarks";
    private String category_1 = "//label[text()='Category']/../input";
    private String category_2_typeSetting = "//p[normalize-space()='Typesetting']";
    private String category_2_technicalEdit = "//p[normalize-space()='Technical Editing']";
    private String category_3_CopyEdit = "//p[normalize-space()='Copy Editing']";
    private String copyEditLevel_1 = "id=copyEditingLevel";
    private String copyEditLevel_2 = "//p[@for='L1']";
    private String pubType_1 = "//label[text()='Publishing Type']/../input";
    private String pubType_CheckAll = "//div[text()='Check All']";

//--General--//

    private String G_FPdaysOfLatex = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Latex Normalization']/../input";
    private String G_FPdaysOfGraphics = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Graphics']/../input";
    private String G_FPdaysOfPreEdit = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Pre-Editing']/../input";
    private String G_FPdaysOfCopyEdit = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Copyediting']/../input";
    private String G_FPdaysOfTypeSetting = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Typesetting']/../input";
    private String G_FPdaysOfQC = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for QC']/../input";

    private String G_AUdaysOfPage = "//*[text()='General']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String G_AUdaysOfQC = "//*[text()='General']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for QC']/../input";
    private String G_PEdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String G_PEdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for QC']/../input";

    private String G_ONFdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for Pagination']/../input";
    private String G_ONFdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for QC']/../input";
    private String G_ONFdaysOfXML = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for XML']/../input";

    private String G_ISSdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for Pagination']/../input";
    private String G_ISSdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for QC']/../input";

    private String G_PrintdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for Pagination']/../input";
    private String G_PrintdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for QC']/../input";
    private String G_PrintdaysOfXML = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for XML']/../input";


    private String FS_FPdaysOfLatex = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Latex Normalization']/../input";
    private String FS_FPdaysOfGraphics = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Graphics']/../input";
    private String FS_FPdaysOfPreEdit = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Pre-Editing']/../input";
    private String FS_FPdaysOfCopyEdit = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Copyediting']/../input";
    private String FS_FPdaysOfTypeSetting = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Typesetting']/../input";
    private String FS_FPdaysOfQC = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for QC']/../input";

    private String FS_AUdaysOfPage = "//*[text()='Fasttrack']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_AUdaysOfQC = "//*[text()='Fasttrack']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for QC']/../input";
    private String FS_PEdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_PEdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for QC']/../input";

    private String FS_ONFdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_ONFdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for QC']/../input";
    private String FS_ONFdaysOfXML = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for XML']/../input";

    private String FS_ISSdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for Pagination']/../input";
    private String FS_ISSdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for QC']/../input";

    private String FS_PrintdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_PrintdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for QC']/../input";
    private String FS_PrintdaysOfXML = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for XML']/../input";


    private String styleTemplateUploadButton = "//*[@id='styles-upload']/../div";


    private String guideLineUploadButton = "//*[@id='guidelines-upload']/../div";

    private String importStyleTemplate = "//div[@class='_style-editor-container_132u2_451']//div[@title='Import from Publisher']";
    private String importGuideLinesDocument = "//div[@class='_guidelines-container_132u2_583']//div[@title='Import from Publisher']";
    private String importFileTATyes = "//*[text()='Yes']";


    private String addButton = "//button[@type='button']";

    private String acknowledgement = "//div[contains(text(),'Journal Added Successfully')]";


    public UserManagementPage(Page page) {
        this.page = page;

    }

    public void verifyAddUserisClickable() {

        page.locator(userdashboard).click();
        page.locator(addUser).click();
        page.waitForSelector(addCloseButton).click();

    }

    public void generalAddUser(String empName, String empId, String designation, String gender,
                               String depart, String Pub, String access, String role, String mail) {

        page.locator(userdashboard).click();
        page.locator(addUser).click();

        page.locator(enterEmpName).fill(empName);

        double empI = Double.parseDouble(empId);
        int empIdAsInt = (int) empI;
        String empIDAsString = String.valueOf(empIdAsInt);
        page.locator(enterEmpId).fill(empIDAsString);


        Locator desig = page.locator(designationDropdown);
        desig.selectOption(new SelectOption().setLabel(designation));


        Locator Gen = page.locator(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));


        Locator dept = page.locator(department);
        dept.selectOption(new SelectOption().setLabel(depart));


        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();


        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();

        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));


        page.locator(EMail).fill(mail);
//        page.locator(AddOkButton).click();
//
//        page.waitForSelector(addCloseButton).click();


    }


    public void editUserProfileCard(String empName, String empId) throws InterruptedException {

        page.locator(userdashboard).click();

        page.waitForSelector("(//p[text()='" + empName + "'])[last()]").click();
        Thread.sleep(1000);

        double empI = Double.parseDouble(empId);
        int empIdAsInt = (int) empI;
        String empIDAsString = String.valueOf(empIdAsInt);

        String EditButton = "//div/p[text()='" + empIDAsString + "']//following::button[@class='_btn_d74oi_223'][1]";

        System.out.println("Emp id here: " + empIDAsString);
        page.waitForSelector(EditButton, new Page.WaitForSelectorOptions().setTimeout(30000));
        Locator Edit = page.locator(EditButton);
        Edit.scrollIntoViewIfNeeded();
        Edit.click();


    }

    public void loginData(String Uname, String Pwd) {


        page.locator(username).fill(Uname);
        page.locator(password).fill(Pwd);
        page.waitForSelector(SignIn).click();


    }


    public void AddPubGeneral(String PubAcro, String PubName) {


        page.locator(baseicon).click();
        page.locator(addpubicon).click();
        page.locator(pub_acronym).fill(PubAcro);
        page.locator(pub_name_textbox).fill(PubName);
        page.locator(pub_mail_textbox).fill("sak@gmail.com");
        page.locator(desc_inputbox).fill("1");
        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);

        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);


        page.locator(selectdateinput).fill(tomorrow);

        page.locator(pub_location).fill("1");
        page.locator(ftpusername).fill("1");
        page.locator(ftppassword).fill("1");


        page.locator(ftp_initial_directory).fill("1");
        page.locator(daysforlatexnormalization).fill("1");
        page.locator(daysforgraphics).fill("1");
        page.locator(daysforPreediting).fill("1");
        page.locator(daysforcopyediting).fill("1");
        page.locator(daysforlatexnormalization).fill("1");
        page.locator(daysfortypesettings).fill("1");
        page.locator(daysforqc).fill("1");
        page.locator(daysforaupag).fill("1");
        page.locator(daysforauqc).fill("1");
        page.locator(daysforpepag).fill("1");
        page.locator(daysforpeqc).fill("1");
        page.locator(daysforonlinepag).fill("1");
        page.locator(daysforonlineqc).fill("1");
        page.locator(daysforonlinexml).fill("1");
        page.locator(IssuePag).fill("1");
        page.locator(IssueQC).fill("1");
        page.locator(printpag).fill("1");
        page.locator(printQC).fill("1");

        //assertThat(page.locator(printxml)).isAttached();
        page.locator(printxml).fill("1");

        page.locator(CopyTat).click();
        page.locator(CopyTatConfirm).click();

        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("image.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("GuidelinesNew.docx"));

        page.locator(addbutton).click();
        page.waitForSelector(PubalertCloseButton).click();


    }


    public void AddJournalGeneral(String PubAcro, String Jacro, String Jname) {

        page.locator(baseicon).click();
        page.locator(addjouricon).click();
        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + PubAcro + "']").click();
        page.locator(jor_acrm).fill(Jacro);
        page.locator(jor_fullname).fill(Jname);

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(jor_received_date).fill(formattedDate);
        page.locator(recto_facing).click();
        page.locator(layout_1).click();
        page.locator(layout_2).click();
        page.locator(trimSizeWidth).fill("1");
        page.locator(trimSizeHeight).fill("2");
        page.locator(remarks).fill("Test Remarks");
        page.waitForTimeout(2000);
        page.waitForSelector(category_1).click();
        page.waitForSelector(category_2_technicalEdit).click();
        page.waitForSelector(category_2_typeSetting).click();
//        page.locator(category_3_CopyEdit).click();
//        page.locator(copyEditLevel_1).click();
//        page.locator(copyEditLevel_2).click();
        page.locator(pubType_1).click();
        page.locator(pubType_CheckAll).click();

        page.locator(G_FPdaysOfLatex).fill("1");
        page.locator(G_FPdaysOfGraphics).fill("1");
        page.locator(G_FPdaysOfPreEdit).fill("1");
        page.locator(G_FPdaysOfCopyEdit).fill("1");
        page.locator(G_FPdaysOfTypeSetting).fill("1");
        page.locator(G_FPdaysOfQC).fill("1");

        page.locator(FS_FPdaysOfLatex).fill("1");
        page.locator(FS_FPdaysOfGraphics).fill("1");
        page.locator(FS_FPdaysOfPreEdit).fill("1");
        page.locator(FS_FPdaysOfCopyEdit).fill("1");
        page.locator(FS_FPdaysOfTypeSetting).fill("1");
        page.locator(FS_FPdaysOfQC).fill("1");

        page.evaluate("window.scrollBy(0, 320)");
        page.locator(G_AUdaysOfPage).fill("1");
        page.locator(G_AUdaysOfQC).fill("1");
        page.locator(FS_AUdaysOfPage).fill("1");
        page.locator(FS_AUdaysOfQC).fill("1");

        page.locator(G_PEdaysOfPage).fill("1");
        page.locator(G_PEdaysOfQC).fill("1");
        page.locator(FS_PEdaysOfPage).fill("1");
        page.locator(FS_PEdaysOfQC).fill("1");
        page.evaluate("window.scrollBy(0, 320)");

        page.locator(G_ONFdaysOfPage).fill("1");
        page.locator(G_ONFdaysOfQC).fill("1");
        page.locator(G_ONFdaysOfXML).fill("1");
        page.locator(FS_ONFdaysOfPage).fill("1");
        page.locator(FS_ONFdaysOfQC).fill("1");
        page.locator(FS_ONFdaysOfXML).fill("1");


        page.locator(G_ISSdaysOfPage).fill("2");
        page.locator(G_ISSdaysOfQC).fill("2");
        page.locator(FS_ISSdaysOfPage).fill("2");
        page.locator(FS_ISSdaysOfQC).fill("2");

        page.locator(G_PrintdaysOfPage).fill("2");
        page.locator(G_PrintdaysOfQC).fill("2");
        page.locator(G_PrintdaysOfXML).fill("2");
        page.locator(FS_PrintdaysOfPage).fill("2");
        page.locator(FS_PrintdaysOfQC).fill("2");
        page.locator(FS_PrintdaysOfXML).fill("2");


    }

    public void AddJournalUploadFilesOld(){

        page.locator(importStyleTemplate).click();
        page.locator(importFileTATyes).click();

        page.waitForSelector(importGuideLinesDocument).click();
        page.locator(importFileTATyes).click();

    }

    public void NewAddJournalUploadFiles(){

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal New.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));


    }




    public boolean creatingUserWithoutAdd(String empName, String empId, String designation,
                                          String gender, String depart, String Pub, String access,
                                          String role, String mail) {

        page.locator(enterEmpName).fill(empName);
        page.locator(enterEmpId).fill(empId);

        Locator desigdropdown = page.locator(designationDropdown);
        desigdropdown.selectOption(new SelectOption().setLabel(designation));

        Locator Gen = page.locator(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));

        Locator dept = page.locator(department);
        dept.selectOption(new SelectOption().setLabel(depart));

        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();

        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();


        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));

        page.locator(EMail).fill(mail);
        page.waitForSelector(addCloseButton).click();
        page.locator(userdashboard).click();

        boolean checkProfile = page.locator("(//p[text()='" + empName + "'])[last()]").isVisible();

        return checkProfile;

    }


    public String duplicateEmpId(String empName, String empId, String designation, String gender,
                                 String depart, String Pub, String access, String role, String mail) {


        page.locator(enterEmpName).fill(empName);
        page.locator(enterEmpId).fill(empId);

        Locator desigdropdown = page.locator(designationDropdown);
        desigdropdown.selectOption(new SelectOption().setLabel(designation));

        Locator Gen = page.locator(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));

        Locator dept = page.locator(department);
        dept.selectOption(new SelectOption().setLabel(depart));

        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();

        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();

        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));

        page.locator(EMail).fill(mail);
        page.locator(AddOkButton).click();

        String AlertMsg = page.locator("//h2[text()='JMS - Duplicate Mail']//following::div[text()='Employee ID already exists.']").textContent();

        System.out.println("The alert message is: " + AlertMsg);

        page.waitForSelector(addCloseButton).click();
        return AlertMsg;


    }


    public List<String> addNewUserandVerify(String empName, String empId, String designation, String gender,
                                            String depart, String Pub, String access, String role, String mail) throws InterruptedException {


        page.locator(userdashboard).click();
        page.locator(addUser).click();

        page.locator(enterEmpName).fill(empName);
        page.locator(enterEmpId).fill(empId);


        Locator desig = page.locator(designationDropdown);
        desig.selectOption(new SelectOption().setLabel(designation));


        Locator Gen = page.locator(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));


        Locator dept = page.locator(department);
        dept.selectOption(new SelectOption().setLabel(depart));


        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();


        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();

        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));


        page.locator(EMail).fill(mail);
        page.locator(AddOkButton).click();

        page.waitForSelector(addCloseButton).click();


        editUserProfileCard(empName, empId);

        List<String> UserDetails = new ArrayList<>();

        UserDetails.add(page.locator(enterEmpName).inputValue());
        UserDetails.add(page.locator(enterEmpId).inputValue());


        assertThat(page.locator("id=user-designation")).isVisible();
        Locator selectedDesig = desig.locator("option:checked");
        String selectedDesigText = selectedDesig.textContent().trim();
        System.out.println("Selected value for designation: " + selectedDesigText);
        UserDetails.add(selectedDesigText);


        UserDetails.add(page.locator(Gender).inputValue());


        assertThat(page.locator("id=user-department")).isVisible();
        Locator selectedDepartment = dept.locator("option:checked");
        String selectedDptText = selectedDepartment.textContent().trim();
        System.out.println("Selected value for depart: " + selectedDptText);
        UserDetails.add(selectedDptText);


        UserDetails.add(page.locator(Publisher).textContent());
        String pub = page.locator(Publisher).textContent();
        System.out.println("This is Publisher here: " + pub);


        UserDetails.add(page.locator(Access).textContent());
        UserDetails.add(page.locator(Role).inputValue());
        UserDetails.add(page.locator(EMail).inputValue());

        return UserDetails;


    }


    public Boolean nameWithOnlyAlphabets(String empName, String empId, String designation, String gender,
                                         String depart, String Pub, String access, String role, String mail) throws InterruptedException {

        page.locator(userdashboard).click();
        page.locator(addUser).click();
        page.locator(enterEmpName).fill(empName);
        int empNameAsInt = (int) Double.parseDouble(empId);

        // Convert the integer to string
        String empNameAsString = String.valueOf(empNameAsInt);
        page.locator(enterEmpId).fill(empNameAsString);
        System.out.println("empIdStr: " + empNameAsString);


        Locator Gen = page.locator(Gender);
        page.waitForSelector(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));

        Locator dept = page.locator(department);
        page.waitForSelector(department);
        dept.selectOption(new SelectOption().setLabel(depart));


        Locator desig = page.locator(designationDropdown);
        desig.selectOption(new SelectOption().setLabel(designation));

        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();

        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();

        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));

        page.locator(EMail).fill(mail);


        Locator invalidInputs = page.locator("input:invalid").first();

        page.locator(AddOkButton).click();
        page.waitForSelector(addCloseButton).click();

        if (invalidInputs.isVisible()) {
            String alertText = (String) invalidInputs.evaluate("el => el.validationMessage");
            System.out.println("Err message here is : " + alertText);
            assert alertText != null && !alertText.isEmpty() : "Validation message is not displayed!";
            return true;
        } else {
            System.out.println("No validation message displayed.");
            return false;
        }


    }

    public Boolean empIDWithOnlyNumbers(String empName, String empId, String designation, String gender,
                                        String depart, String Pub, String access, String role, String mail) {


        page.locator(userdashboard).click();
        page.locator(addUser).click();

//        int empNameAsInt = (int) Double.parseDouble(empId);
        String empNameAsString = String.valueOf(empId);

        page.locator(enterEmpId).fill(empNameAsString);
        System.out.println("empIdStr: " + empNameAsString);

        page.locator(enterEmpName).fill(empName);

        Locator Gen = page.locator(Gender);
        page.waitForSelector(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));

        Locator dept = page.locator(department);
        page.waitForSelector(department);
        dept.selectOption(new SelectOption().setLabel(depart));


        Locator desig = page.locator(designationDropdown);
        desig.selectOption(new SelectOption().setLabel(designation));

        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();

        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();

        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));

        page.locator(EMail).fill(mail);

        //List<String> data= page.locator(EMail).allInnerTexts();


        Locator invalidInputs = page.locator("input:invalid").first();

        page.locator(AddOkButton).click();
        page.waitForSelector(addCloseButton).click();

        if (invalidInputs.isVisible()) {
            String alertText = (String) invalidInputs.evaluate("el => el.validationMessage");
            System.out.println("Err message here is : " + alertText);
            assert alertText != null && !alertText.isEmpty() : "Validation message is not displayed!";
            return true;
        } else {
            System.out.println("No validation message displayed.");
            return false;
        }


    }


    public List<String> editEmployeeButNotClickingUpdate(String empName, String empId, String designation, String gender,
                                                         String depart, String Pub, String access, String role) throws InterruptedException {

        page.locator(userdashboard).click();


        editUserProfileCard(empName, empId);

        page.locator(enterEmpName).clear();
        page.locator(enterEmpName).fill(empName);

//        page.locator(enterEmpId).clear();
//        double empI = Double.parseDouble(empId);
//        int empIdAsInt = (int) empI;
//        String empIDAsString = String.valueOf(empIdAsInt);
//        page.locator(enterEmpId).fill(empIDAsString);

        Locator desig = page.locator(designationDropdown);
        desig.selectOption(new SelectOption().setLabel(designation));

        Locator dept = page.locator(department);
        page.waitForSelector(department);
        dept.selectOption(new SelectOption().setLabel(depart));

        Locator Gen = page.locator(Gender);
        page.waitForSelector(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));

        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();

        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();

        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));

//        page.locator(EMail).fill(mail);

        List<String> EditUserDetails = new ArrayList<>();

        EditUserDetails.add(page.locator(enterEmpName).inputValue());
        EditUserDetails.add(page.locator(enterEmpId).inputValue());


        assertThat(page.locator("id=user-designation")).isVisible();
        Locator selectedDesig = desig.locator("option:checked");
        String selectedDesigText = selectedDesig.textContent().trim();
        System.out.println("Selected value for designation: " + selectedDesigText);
        EditUserDetails.add(selectedDesigText);


        EditUserDetails.add(page.locator(Gender).inputValue());


        dept.selectOption(new SelectOption().setLabel(depart));
        assertThat(page.locator("id=user-department")).isVisible();
        Locator selectedDepartment = dept.locator("option:checked");
        String selectedDptText = selectedDepartment.textContent().trim();
        System.out.println("Selected value for depart: " + selectedDptText);
        EditUserDetails.add(selectedDptText);


        EditUserDetails.add(page.locator(Publisher).textContent());
        String pub = page.locator(Publisher).textContent();
        System.out.println("This is Publisher here: " + pub);


        EditUserDetails.add(page.locator(Access).textContent());
        EditUserDetails.add(page.locator(Role).inputValue());


        return EditUserDetails;


    }


    public String dupUserEmpIdOfDeactivatedAcct(String empName, String empId, String designation, String gender,
                                                String depart, String Pub, String access, String role, String mail) throws InterruptedException {


        editUserProfileCard(empName, empId);

        Boolean DeActV = page.locator(DeactivaeIcon).isVisible();

        if (DeActV.equals(true)) {

            page.waitForSelector(DeactivaeIcon).click();
            page.waitForSelector(addCloseButton).click();
            page.waitForSelector(ActivateIcon).click();
            page.waitForSelector(addCloseButton).click();


            System.out.println("Close button clicked");

            page.locator(userdashboard).click();
            page.locator(addUser).click();
            generalAddUser(empName, empId, designation, gender, depart, Pub, access, role, mail);
            page.locator(AddOkButton).click();


        } else {


            page.waitForSelector(ActivateIcon).click();
            page.waitForSelector(addCloseButton).click();


            System.out.println("Close button clicked");

            page.locator(userdashboard).click();
            page.locator(addUser).click();
            generalAddUser(empName, empId, designation, gender, depart, Pub, access, role, mail);
            page.locator(AddOkButton).click();
            System.out.println("Ok button has been clicked here");
            page.waitForTimeout(2000);


        }

        String ErrMsg = page.locator("//div[contains(text(),'Employee ID already exists.')]").textContent();
        System.out.println("Error Message: " + ErrMsg);
        return ErrMsg;


    }


    public List<String> MaleFemaleIconCheck(String empName, String empId, String designation, String gender,
                                            String depart, String Pub, String access, String role, String mail) throws InterruptedException {


        page.locator(userdashboard).click();
        page.locator(addUser).click();
        page.locator(enterEmpName).fill(empName);


        double empI = Double.parseDouble(empId);
        int empIdAsInt = (int) empI;
        String empIDAsString = String.valueOf(empIdAsInt);
        page.locator(enterEmpId).fill(empIDAsString);


        Locator desig = page.locator(designationDropdown);
        desig.selectOption(new SelectOption().setLabel(designation));

        Locator Gen = page.locator(Gender);
        Gen.selectOption(new SelectOption().setLabel(gender));

        Locator dept = page.locator(department);
        dept.selectOption(new SelectOption().setLabel(depart));


        page.locator(Publisher).click();
        page.locator("//div[text()='" + Pub + "']").click();


        page.locator(Access).click();
        page.locator("//div[text()='" + access + "']").click();

        Locator roleNew = page.locator(Role);
        roleNew.selectOption(new SelectOption().setLabel(role));


        page.locator(EMail).fill(mail);
        page.locator(AddOkButton).click();
        page.waitForTimeout(2000);
        page.waitForSelector(addCloseButton).click();
        System.out.println("Close button clicked");

        editUserProfileCard(empName, empId);

        List<String> GenderCheck = new ArrayList<>();

        GenderCheck.add(page.locator(Gender).inputValue());

        String GendVal = page.locator(Gender).inputValue();
        System.out.println("Gender Value: " + GendVal);

        return GenderCheck;

    }

    public List<String> CheckWithChangingRoles(String empName, String empId, String designation, String gender,
                                               String depart, String Pub, String access, String role, String mail,
                                               String roleTL, String Uname, String Pwd, String rolecheck, String PmUname, String PmPwd) throws InterruptedException {


        generalAddUser(empName, empId, designation, gender, depart, Pub, access, role, mail);
        page.locator(AddOkButton).click();

        page.waitForTimeout(2000);
        page.waitForSelector(addCloseButton).click();

        editUserProfileCard(empName, empId);

        Locator roleNext = page.locator(Role);
        roleNext.selectOption(new SelectOption().setLabel(roleTL));
        System.out.println("1st Role here is: " + roleTL);

        page.waitForSelector(UpdateBtn).click();

        page.waitForTimeout(2000);
        page.waitForSelector(addCloseButton).click();
        page.waitForSelector(Logout).click();

        loginData(Uname, Pwd);

        page.waitForSelector(profile).click();

        page.waitForTimeout(2000);
        page.evaluate("window.scrollBy(0, 5000)");

        boolean checkVis = page.locator("//span[contains(text(), '" + rolecheck + "')]").isVisible();
        System.out.println("AMTL Role Visibility: " + checkVis);


        String AMTL_Role = page.waitForSelector("//span[contains(text(), '" + rolecheck + "')]").innerText();


        page.locator(Logout).click();

        loginData(PmUname, PmPwd);
        page.locator(userdashboard).click();
        editUserProfileCard(empName, empId);

        Locator roleNext_1 = page.locator(Role);
        roleNext_1.selectOption(new SelectOption().setLabel(role));
        System.out.println("2nd Role here is: " + role);

        page.waitForSelector(UpdateBtn).click();

        page.waitForTimeout(2000);
        page.waitForSelector(addCloseButton).click();
        page.waitForSelector(Logout).click();

        loginData(Uname, Pwd);

        page.waitForSelector(profile).click();

        String UserRole = page.waitForSelector("//span[contains(text(), '" + role + "')]").innerText();

        List<String> RolesCheck = new ArrayList<>();

        RolesCheck.add(AMTL_Role);
        System.out.println("AMTL inside List :" + AMTL_Role);

        RolesCheck.add(UserRole);
        System.out.println("User inside List :" + UserRole);

        return RolesCheck;


    }


    public List<String> desigDeptVerification(String empName, String empId, String designation,
                                              String gender, String depart, String Pub, String access, String role, String mail,
                                              String Uname, String Pwd, String desigNew, String departNew) throws InterruptedException {


        generalAddUser(empName, empId, designation, gender, depart, Pub, access, role, mail);
        page.locator(AddOkButton).click();

        page.waitForTimeout(2000);
        page.waitForSelector(addCloseButton).click();
        page.waitForSelector(Logout).click();

        loginData(empId, empId);
        page.waitForSelector(profile).click();


        Boolean DesiVal = page.waitForSelector("//span[contains(text(), '" + designation + "')]").isVisible();
        if (DesiVal.equals(true)) {

            String DesignNew = page.waitForSelector("//span[contains(text(), '" + designation + "')]").innerText();
            System.out.println("Existing Designation :" + DesignNew);


            String DeptNew = page.waitForSelector("//span[contains(text(), '" + depart + "')]").innerText();
            System.out.println("Existing Department :" + DeptNew);

            page.evaluate("window.scrollBy(0, 1000)");
            page.waitForSelector(Logout).click();

            loginData(Uname, Pwd);
            page.locator(userdashboard).click();

            editUserProfileCard(empName, empId);

            Locator desig = page.locator(designationDropdown);
            desig.selectOption(new SelectOption().setLabel(desigNew));

            Locator dept = page.locator(department);
            dept.selectOption(new SelectOption().setLabel(departNew));

            page.waitForSelector(UpdateBtn).click();
            page.waitForTimeout(2000);
            page.waitForSelector(addCloseButton).click();
            page.waitForSelector(Logout).click();

            loginData(empId, empId);

            page.waitForSelector(profile).click();

            String DesignUpdate = page.waitForSelector("//span[contains(text(), '" + desigNew + "')]").innerText();
            System.out.println("Updated Designation :" + DesignUpdate);


            String DeptUpdated = page.waitForSelector("//span[contains(text(), '" + departNew + "')]").innerText();
            System.out.println("Updated Department :" + DeptUpdated);


            List<String> DeptAndDesignCheck = new ArrayList<>();

            DeptAndDesignCheck.add(DesignNew);
            System.out.println("Existing Designation inside List :" + DesignNew);

            DeptAndDesignCheck.add(DeptNew);
            System.out.println("Existing Department inside List :" + DeptNew);


            DeptAndDesignCheck.add(DesignUpdate);
            System.out.println("Updated Designation inside List :" + DesignUpdate);


            DeptAndDesignCheck.add(DeptUpdated);
            System.out.println("Updated Department inside List :" + DeptUpdated);

            return DeptAndDesignCheck;

        } else {

            page.waitForSelector(Logout).click();
            loginData(Uname, Pwd);
            editUserProfileCard(empName, empId);

            Locator desig = page.locator(designationDropdown);
            desig.selectOption(new SelectOption().setLabel(designation));

            Locator dept = page.locator(department);
            dept.selectOption(new SelectOption().setLabel(depart));

            page.waitForSelector(UpdateBtn).click();
            page.waitForTimeout(2000);
            page.waitForSelector(addCloseButton).click();
            page.waitForSelector(Logout).click();

            loginData(empId, empId);
            page.waitForSelector(profile).click();

            String DesignNew = page.waitForSelector("//span[contains(text(), '" + designation + "')]").innerText();
            System.out.println("Existing Designation :" + DesignNew);


            String DeptNew = page.waitForSelector("//span[contains(text(), '" + depart + "')]").innerText();
            System.out.println("Existing Department :" + DeptNew);


            page.evaluate("window.scrollBy(0, 1000)");
            page.waitForSelector(Logout).click();

            loginData(Uname, Pwd);
            page.locator(userdashboard).click();

            editUserProfileCard(empName, empId);

            Locator desigN = page.locator(designationDropdown);
            desigN.selectOption(new SelectOption().setLabel(desigNew));

            Locator deptN = page.locator(department);
            deptN.selectOption(new SelectOption().setLabel(departNew));

            page.waitForSelector(UpdateBtn).click();
            page.waitForTimeout(2000);
            page.waitForSelector(addCloseButton).click();
            page.waitForSelector(Logout).click();

            loginData(empId, empId);

            page.waitForSelector(profile).click();

            String DesignUpdate = page.waitForSelector("//span[contains(text(), '" + desigNew + "')]").innerText();
            System.out.println("Updated Designation :" + DesignUpdate);


            String DeptUpdated = page.waitForSelector("//span[contains(text(), '" + departNew + "')]").innerText();
            System.out.println("Updated Department :" + DeptUpdated);


            List<String> DeptAndDesignCheck = new ArrayList<>();

            DeptAndDesignCheck.add(DesignNew);
            System.out.println("Existing Designation inside List :" + DesignNew);

            DeptAndDesignCheck.add(DeptNew);
            System.out.println("Existing Department inside List :" + DeptNew);


            DeptAndDesignCheck.add(DesignUpdate);
            System.out.println("Updated Designation inside List :" + DesignUpdate);


            DeptAndDesignCheck.add(DeptUpdated);
            System.out.println("Updated Department inside List :" + DeptUpdated);

            return DeptAndDesignCheck;


        }


    }

    public List<Boolean> navigateToAllManageViewPage(String PubV, String JourV, String ArtV) {


        page.locator(Manage).click();
        page.locator(SelectView).click();
        page.locator(PubView).click();

        List<Boolean> ManageViewCheck = new ArrayList<>();


        Boolean PubView = page.locator("//input[@value='" + PubV + "']").isVisible();
        System.out.println("Does Publisher view is visible: " + PubView);
        ManageViewCheck.add(PubView);


        page.locator(SelectView).click();
        page.locator(JourView).click();

        Boolean JournView = page.locator("//input[@value='" + JourV + "']").isVisible();
        System.out.println("Does Publisher view is visible: " + JournView);
        ManageViewCheck.add(JournView);


        page.locator(SelectView).click();
        page.locator(ArticleView).click();

        Boolean ArticleView = page.locator("//input[@value='" + ArtV + "']").isVisible();
        System.out.println("Does Publisher view is visible: " + ArticleView);
        ManageViewCheck.add(ArticleView);

        return ManageViewCheck;


    }


    public List<Object> addAndVerifyPublisher(String PubAcro, String PubName) {


        AddPubGeneral(PubAcro, PubName);
        List<Object> CheckPubVisibility = new ArrayList<>();


        page.locator(Manage).click();
        page.locator(SelectView).click();
        page.locator(PubView).click();
        Boolean PubVisibility = page.locator("//*[text()='" + PubAcro + "']").isVisible();
        System.out.println("Is Publisher is visible :" + PubVisibility);
        CheckPubVisibility.add(PubVisibility);


        page.waitForSelector("//*[text()='" + PubAcro + "']//following::span[@data-target='#dropright'][1]").click();
        page.waitForSelector("//div[normalize-space()='Edit Publisher']").click();


        String PublisherName = page.locator("//input[@value='" + PubName + "']").inputValue();
        System.out.println("Is Publisher name is visible :" + PublisherName);
        CheckPubVisibility.add(PublisherName);


        page.locator(Manage).click();
        page.locator(SelectView).click();
        page.locator(JourView).click();
        Boolean JourVisibility = page.locator("//h2[text()='" + PubName + "']//following::th[text()='xxxxx']//following::div[text()='xxxxx']").isVisible();
        System.out.println("Is Journal is visible :" + JourVisibility);
        CheckPubVisibility.add(JourVisibility);


        return CheckPubVisibility;


    }


    public List<Boolean> addTwoJounralsAndVerifywithPub(String PubAcro, String PubName, String Jacro,
                                                        String Jname, String JacroNew, String JnameNew) {


        AddPubGeneral(PubAcro, PubName);


        AddJournalGeneral(PubAcro, Jacro, Jname);
        AddJournalUploadFilesOld();

        page.waitForTimeout(2000);

        page.waitForSelector(addButton).click();
        page.locator(JralertCloseButton).click();

        page.keyboard().press("Control+Shift+R");
        page.reload();
        page.waitForTimeout(2000);

        AddJournalGeneral(PubAcro, JacroNew, JnameNew);
        NewAddJournalUploadFiles();

        page.waitForSelector(addButton).click();
        page.locator(JralertCloseButton).click();

        page.locator(Manage).click();
        page.locator(SelectView).click();
        page.locator(JourView).click();

        List<Boolean> DoubleJournalCheck = new ArrayList<>();

        Boolean FirstJournal =
                page.locator("//h2[text()='" + PubName + "']//following::th[text()='" + Jacro + "'][1]//following::div[text()='" + Jname + "'][1]").isVisible();
        System.out.println("Visibility of 1 journal: " + FirstJournal);
        DoubleJournalCheck.add(FirstJournal);

        Boolean SecondJournal =
                page.locator("//h2[text()='" + PubName + "']//following::th[text()='" + JacroNew + "'][1]//following::div[text()='" + JnameNew + "'][1]").isVisible();
        System.out.println("Visibility of 1 journal: " + SecondJournal);
        DoubleJournalCheck.add(SecondJournal);


        page.locator(Manage).click();
        page.locator(SelectView).click();
        page.locator(ArticleView).click();

        Boolean ArtView = page.locator("//h2[normalize-space()='" + PubName + " / " + Jacro + "']").isVisible();
        System.out.println("Visibility of Article: " + ArtView);

        DoubleJournalCheck.add(ArtView);
        return DoubleJournalCheck;


    }
}