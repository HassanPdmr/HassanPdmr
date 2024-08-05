package org.jms.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UserManagementPage {


    private Page page;


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


    public UserManagementPage(Page page) {
        this.page = page;

    }

    public void verifyAddUserisClickable() {

        page.locator(userdashboard).click();
        page.locator(addUser).click();

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

        }
        else {

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


}

