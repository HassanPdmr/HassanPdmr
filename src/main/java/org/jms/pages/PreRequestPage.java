package org.jms.pages;


import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PreRequestPage {

    private Page page;
    FileChooser fileChooser;


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

    private String Imageuploadbutton = "//button[text()='Upload Image']";

    private String styletemplate = "//*[@id='styles-upload']//preceding::div[1]";

    private String guidelinesdoc = "//*[@id='guidelines-upload']//preceding::div[1]";
    private String CopyTat = "//*[@title='Replicate values from General to Fasttrack']";
    private String CopyTatConfirm = "//*[text()='Yes']";
    private String managemenu = "//div[@id='root']//following::p[text()='Manage']";

    //webelement for fasttrack


    private String addalertclose = "//h2[text()='JMS - Add Publisher']//following::span[1]";

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


    private String addButton = "//button[@type='button']";

    private String acknowledgement = "//div[contains(text(),'Journal Added Successfully')]";

    private String alertCloseButton = "//h2[text()='JMS - Add Journal']//following::span[1]";

    //

    private String verifypubforlog = "//th[text()='k']";
    private String viewtype = "";
    private String pubviewtype = "";
    private String updatebutton = "//button[text()='Update Publisher']";
    private String updatealert = "//h2[text()='JMS - Update Publisher']//following::span[1]";
    private String reuploadbutton = "//*[text()='Reupload']";
    private String journal_pub_drop = "id=publisher";
    private String journal_pub_list = "//*[@id='publisher']//following::li";
    public String f_latex_val;
    public String f_graph_val;
    public String f_pre_val;
    public String f_copy_edit_val;
    public String f_ts_val;
    public String f_qc_val;
    public String style_error_text = "//h2[text()='JMS - File Upload Restriction']//following::div[2]";
    public String getStyle_error_alert = "//h2[text()='JMS - File Upload Restriction']//following::span[1]";
    public String CountofGuideLinesFile = "//h2[text()='Latest Files']//following::li//following::p";
    public String styuploaderrormsg = "//*[text()='JMS - Upload']//following::div[1]";
    public String guidestyleerroralert = "//h2[text()='JMS - Upload']//following::div[2]";
    public String editstyshowfielddropdown = "(//h2[text()='Latest Files']//following-sibling::img[@title='Show'])[1]";
    private String removestyfiles = "//p[text()='styledoc.sty']";
    private String removefileclose = "//p[text()='styledoc.sty']//following::img[@title='Remove']";
    private String reovefileyes = "//*[text()='Remove File']//following::newbutton[text()='Yes']";
    private String ftphost = "//input[@data-testid='ftp-host']";
    private String StyShow = "(//*[text()='Latest Files']//following::img[@title='Show'])[1]";

//


    private String selectPubJrName = "id=publisher";

    private String UploadLabel = "//label[text()='Upload']";
    private String formLabel = "//label[text()='Form']";
    private String clientftplabel = "//label[text()='Client FTP']";
    /* private String uploadform="//*[@alt='Option 1 (Upload)']";

     private String selectprioritydropdown="id=priority";
     private String selectpriority="//*[@id='priority']//following::p[text()='High']";
     private String selecttat="//label[text()='TAT']//following::div[1]";
     private String selecttatinput="//label[text()='TAT']//following::li[1]//following::p[1]";
     private String Doino="id=doi";
     private String  selectworkflowdropdown="id=workFlow";
     private String selectworkflow="//*[@id='workFlow']//following::p[text()='Fresh']";
     private String noofpages="//input[@id='numberOfPages']";
     private String cebypass="id=CE by-pass";*/
    private String addarticleicon = "id=add_article";
    private String form = "//img[@alt='Option 2 (Form)']";
    private String clickSelectPub = "id=publisher";
    private String Selectpubdropdown = "//input[@placeholder='Search...']";
    private String selectpublisher = "//p[normalize-space(text())='GGD(EMS Press)']\n";
    private String articleidinput = "id=articleID";
    private String authormail = "id=authorMail";
    private String authorname = "id=authorName";
    private String articlename = "id=articleName";
    private String selectpriority = "id=priority";
    private String selectpriorityopt = "//*[@id='priority']//following::p[text()='High']";
    private String receivedate = "id=receivedDate";
    private String reviseddate = "id=revisedDate";
    private String Accepteddate = "id=historyAccDate";
    private String selecttat = "//label[text()='TAT']//following::div[1]";
    private String selecttatinput = "//label[text()='TAT']//following::li[1]//following::p[1]";
    private String Doino = "id=doi";
    private String workflowselection = "//*[text()='Assign Workflow']";
    private String selectgeneralworkflow = "//*[@alt='General']";
    private String assignbutton = "//*[text()='Assign']";


    // private String selectworkflowopt="//*[@id='workFlow']//following::p[text()='Fresh']";
    private String noofpages = "//input[@id='numberOfPages']";
    private String cebypass = "id=CE by-pass";
    private String articletype = "//*[@placeholder='Enter Article Type...']";
    private String TATShow = "//*[text()='Turn Around Time']//preceding-sibling::img";
    private String importtatfromjournal = "//*[@title='Import TATs from Journal']";
    private String confirmimportfromjour = "//*[contains(text(),'Import TAT')]//following::div//following::newbutton[contains(text(),'Yes')]";
    private String startdate = "id=startDate";
    private String ChecklistSelectionShow = "//*[text()='Checklist Selections']//preceding-sibling::img";

    private String OnOpenAccess = "id=acpOpenAccess";


    private String fileupload = "//*[@alt='Upload']";
    private String addnotes = "//*[@alt='Add Notes']";
    private String Plzwwritehere = "//*[@data-placeholder='Type here...']";
    private String AddNoteutton = "//*[text()='Add Note']";
    private String addnotetoastclose = "//*[text()='JMS - Add Notes']//following::span[1]";
    private String checklist = "//*[text()='Check Lists']";
    private String figurechecklist = "id=figures";
    private String checlistalert = "//*[text()='Alert']//following::newbutton[text()='Yes']";
    private String checklisttoast = "//*[text()='JMS - Check Lists']//following::span[1]";
    private String mailpreview = "//*[text()='Preview']";
    private String tomail = "//label[text()='To']//following::input[@id='category'][1]";
    private String tomailluser = "//p[normalize-space(text())='compuscriptrep@gmail.com']//preceding-sibling::input";
    private String Acknowledgementtomailluser = "//p[normalize-space(text())='latexam@gmail.com']//preceding-sibling::input";


    private String ccmail = "//label[text()='Cc']//following::input[@id='category'][1]";
    private String ccmailuser = "//p[normalize-space(text())='nirmala@pdmrindia.com']//preceding-sibling::input";
    private String Acknowlegeemtnsavemailbutton = "//button[text()='Save Mail']";
    private String Acknowledgementyesalert = "//*[text()='Are you sure to Save the mail on acknowledgement?']//following::newbutton[text()='Yes']";
    private String Acknowlegementtoastclose = "//*[text()='JMS - Mail']//following::span[1]";
    private String notificationmail = "//*[text()='Notification']";
    private String savenotificationmail = "//*[text()='Save Mail']";
    private String notificationalert = "//*[text()='Are you sure to Save the mail on notification?']//following::newbutton[text()='Yes']";
    private String notificationsuccesstoastmail = "//*[text()='A mail will be triggered once the article is added successfully']//preceding::span[text()='×']";
    private String addarticlebutton = "(//*[text()='Add Article'])[2]";
    private String checkall = "//div[text()='Check All']";
    private String Checklistsubmitbutton = "//button[text()='Submit CheckList']";
    private String addarticlealert = "//*[text()='JMS - Add Article']//following::span[1]";
    private String selectview = "id=select_view";
    private String journalsview = "//*[@id='select_view']//following::li//following::p[text()='Articles View']";

    private String supplement = "id=supplement";
    private String aopFree = "id=acpFree";
    private String aopopenacess = "id=acpOpenAccess";
    private String tables = "id=tables";
    private String displayfigures = "id=displayFigures";
    private String InlineFigures = "id=inlineFigures";

    private String userdashboard = "//div[@id='root']//following::p[text()='Users']";
    private String addUser = "//main[@class='_main-content-container_105y3_23']//button[normalize-space()='Add User']";
    private String enterEmpName = "id=user-name";
    private String enterEmpId = "id=employee-id";
    private String designationDropdown = "id=user-designation";
    private String publisher = "id=react-select-3-placeholder";
    private String access = "id=react-select-5-placeholder";
    private String mail = "id=user-mail";
    private String addCloseButton = "(//span[normalize-space()='×'])[1]";
    private String department = "id=user-department";
    private String Role = "id=user-role";
    private String AddOkButton = "//form[@data-testid='add-user-user-form']//button[@type='submit']";
    private String editUserNameCard = "//div[@class='_content_d74oi_73']";
    private String sakthiuser = "(//p[text()='Sakthi'])[2]";
    private String EditUser = "//p[text()='1948']//following::img[@alt='edit']";
    private String Publisher = "//label[text()='Publisher']//following::div[1]";
    private String Access = "//label[text()='Access']//following::div[1]";
    private String Gender = "id=user-gender";
    private String Mail = "id=user-mail";


    public PreRequestPage(Page page) {
        this.page = page;

    }

    public void navigatetobaseicon() {
        page.locator(baseicon).click();
    }

    public void DoAddPub(String a, String b) {
        page.locator(baseicon).click();
        page.locator(addpubicon).click();
        page.locator(pub_acronym).fill(a);
        page.locator(pub_name_textbox).fill(b);
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


        uploadfiles();
        page.locator(CopyTat).click();
        page.locator(CopyTatConfirm).click();

        page.locator(addbutton).click();
        page.locator(addalertclose).click();


    }

    public void uploadfiles() {
        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("image.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("GuidelinesNew.docx"));
    }


    public void DoAddJournal(String Pub, String J_acrm, String J_name) throws InterruptedException {

        page.locator(baseicon).click();
        page.locator(addjouricon).click();
        System.out.println(Pub);
        System.out.println(J_acrm);
        System.out.println(J_name);
        page.locator(baseicon).click();
        page.locator(addjouricon).click();
        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + Pub + "']").click();
        page.locator(jor_acrm).fill(J_acrm);
        page.locator(jor_fullname).fill(J_name);
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(jor_received_date).fill(formattedDate);
        page.locator(recto_facing).click();
        page.locator(layout_1).click();
        page.locator(layout_2).click();
        page.locator(trimSizeWidth).fill("1");
        page.locator(trimSizeHeight).fill("2");
        page.locator(remarks).fill("Test Remarks");
        page.locator(category_1).click();
        page.locator(category_2_technicalEdit).click();
        page.locator(category_2_typeSetting).click();
        page.locator(category_3_CopyEdit).click();
        page.locator(copyEditLevel_1).click();
        page.locator(copyEditLevel_2).click();
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

        page.locator(G_ISSdaysOfPage).fill("1");
        page.locator(G_ISSdaysOfQC).fill("1");
        page.locator(FS_ISSdaysOfPage).fill("1");
        page.locator(FS_ISSdaysOfQC).fill("1");
        page.locator(G_PrintdaysOfPage).fill("1");
        page.locator(G_PrintdaysOfQC).fill("1");
        page.locator(G_PrintdaysOfXML).fill("1");
        page.locator(FS_PrintdaysOfPage).fill("1");
        page.locator(FS_PrintdaysOfQC).fill("1");
        page.locator(FS_PrintdaysOfXML).fill("1");

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));
        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

        page.locator(addButton).click();
        Thread.sleep(2000);

        page.locator(alertCloseButton).click();

    }


    public void DoAddArticle(String journalacro, String articleid, String artname, String doinum, String workflow) throws InterruptedException {


        page.locator(baseicon).click();
        assertThat(page.locator(addarticleicon)).isVisible();
        page.locator(addarticleicon).click();
        page.locator(form).click();

        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);
        LocalDate DayOftomarrow = today.plusDays(2);


        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);
        String dayoftomorrow = (today.plusDays(2)).format(DateTimeFormatter.ISO_DATE);


        page.locator(clickSelectPub).click();

        page.locator(Selectpubdropdown).fill(journalacro);
        page.waitForTimeout(1000);
        Locator JAck = page.locator("//p[normalize-space(text())='" + journalacro + "']");
        JAck.scrollIntoViewIfNeeded();
        JAck.click();
        String arttimeid = String.valueOf(System.currentTimeMillis());
        page.locator(articleidinput).fill(arttimeid);

        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill(artname);
        page.locator(selectpriority).click();
        page.locator(selectpriorityopt).click();
        page.locator(receivedate).fill(formattedDate);
        page.locator(reviseddate).fill(tomorrow);
        page.locator(Accepteddate).fill(dayoftomorrow);
        page.locator(selecttat).click();
        page.locator(selecttatinput).click();

        int doi=1;
        long doinumber=Long.parseLong(arttimeid);
        long doival=doi+doinumber;
        String doivalue=String.valueOf(doival);

        page.locator(Doino).fill(doivalue);
        page.locator(workflowselection).click();
        page.locator("//*[@alt='" + workflow + "']").click();
        page.locator(assignbutton).click();
        page.locator(noofpages).fill("200");
        page.locator(articletype).fill("Research");
        page.locator(cebypass).click();
        page.locator(TATShow).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();
        page.locator(ChecklistSelectionShow).click();
        page.locator(startdate).fill(formattedDate);
        page.locator(OnOpenAccess).click();
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("Sample.zip"));

        page.locator(addnotes).click();

        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();

        page.locator(mailpreview).click();
        page.locator(ccmail).click();
        page.locator(checkall).click();
        page.locator(tomail).click();
        page.locator(checkall).click();
        page.locator(Acknowlegeemtnsavemailbutton).click();
        page.locator(Acknowledgementyesalert).click();
        page.locator(Acknowlegementtoastclose).click();
        page.locator(notificationmail).click();
        page.locator(ccmail).click();
        page.locator(checkall).click();
        page.locator(tomail).click();
        page.locator(checkall).click();
        page.locator(savenotificationmail).click();
        page.locator(notificationalert).click();
        page.locator(notificationsuccesstoastmail).click();
        //page.locator(checkall).click();
        page.locator(checklist).click();
        page.locator(figurechecklist).click();
        page.locator(Checklistsubmitbutton).click();
        page.locator(checlistalert).click();
        page.locator(checklisttoast).click();

       Locator Check = page.locator(addarticlebutton);

        boolean isVisible = Check.isVisible();
        boolean isEnabled = Check.isEnabled();

        if (isVisible && isEnabled) {
            System.out.println("The button is clickable.");
        } else {
            System.out.println("The button is not clickable.");
        }
        page.locator(addarticlebutton).click();

        Thread.sleep(10000);



    }

    public void AddUser(String empName, String empId, String designation,
                        String gender, String depart, String Pub, String access,
                        String role, String mail){



        page.locator(userdashboard).click();
        page.locator(addUser).click();
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

        page.locator(Mail).fill(mail);
        page.locator(AddOkButton).click();
        page.waitForSelector(addCloseButton);
        page.locator(addCloseButton).click();





    }


    public void ReloadDashBoard() throws InterruptedException {
        Thread.sleep(2000);
        page.reload();

    }
}















