package org.jms.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.util.ArrayList;
import java.util.List;

public class UMSecurityPage {

    private Page page;

    private String username = "id=siginIn_id";
    private String password = "id=signin_pwd";
    private String SignIn = "//button[normalize-space()='Sign in']";
    private String profile = "//p[normalize-space()='Profile']";
    private String Logout = "//p[normalize-space()='Logout']";
    private String userdashboard = "//div[@id='root']//following::p[text()='Users']";
    private String addUser = "//main[@class='_main-content-container_105y3_23']//button[normalize-space()='Add User']";
    private String enterEmpName = "id=user-name";
    private String enterEmpId = "id=employee-id";
    private String designationDropdown = "id=user-designation";
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


    public UMSecurityPage(Page page) {
        this.page = page;

    }

    public void loginData(String Uname, String Pwd) {


        page.locator(username).fill(Uname);
        page.locator(password).fill(Pwd);
        page.waitForSelector(SignIn).click();


    }

    public List<String> loginLogoutCorrectData(String Uname, String Pwd, String EmpId) {


//        page.evaluate("window.scrollBy(0, 700)");
//        page.waitForSelector(Logout).click();

        Boolean LoginVisible = page.locator(Logout).isVisible();
        if ((LoginVisible.equals(true))) {

            System.out.println("Entered in If");

            page.locator(Logout).click();

        }


        loginData(Uname, Pwd);
        page.locator(profile).click();

        String EmpIDval = page.waitForSelector("//span[contains(text(), '" + EmpId + "')]").innerText();
        System.out.println("EmpId Verification 1 :" + EmpIDval);

        page.evaluate("window.scrollBy(0, 700)");
        page.waitForSelector(Logout).click();
        loginData(Uname, Pwd);
        page.locator(profile).click();

        String EmpIDval_same = page.waitForSelector("//span[contains(text(), '" + EmpId + "')]").innerText();
        System.out.println("EmpId Verification 2 :" + EmpIDval_same);

        List<String> EmployeeID = new ArrayList<>();

        EmployeeID.add(EmpIDval);
        System.out.println("Updated Department inside List :" + EmpIDval);

        EmployeeID.add(EmpIDval_same);
        System.out.println("Updated Department inside List :" + EmpIDval_same);


        return EmployeeID;
    }

    public boolean loginLogoutINCorrectData(String IvUname, String IvPwd, String Pwd, String Uname, String ErrMsg) {


        Boolean LoginVisible = page.locator(Logout).isVisible();
        if ((LoginVisible.equals(true))) {

            System.out.println("Entered in If");

            page.locator(Logout).click();

        }


        loginData(IvUname, IvPwd);


        String ErrorMessage = page.locator("//span[contains(text(), '" + ErrMsg + "')]").textContent();
        System.out.println("Error message here is :" + ErrorMessage);

        page.locator(username).clear();
        page.locator(password).clear();

        loginData(IvUname, Pwd);

        String ErrorMessage_1 = page.locator("//span[contains(text(), '" + ErrMsg + "')]").textContent();
        System.out.println("Error message here is :" + ErrorMessage_1);

        page.locator(username).clear();
        page.locator(password).clear();

        loginData(Uname, IvPwd);
        String ErrorMessage_2 = page.locator("//span[contains(text(), '" + ErrMsg + "')]").textContent();
        System.out.println("Error message here is :" + ErrorMessage_2);

        page.locator(username).clear();
        page.locator(password).clear();


        return ErrMsg.equals(ErrorMessage);


    }

    public boolean CheckForwardBackAfterLogout(String Uname, String Pass) {

        page.evaluate("window.scrollBy(0, 700)");
        page.waitForSelector(Logout).click();

        page.reload();
        page.waitForTimeout(2000);

        loginData(Uname, Pass);
        page.goBack();
        page.waitForLoadState();

        page.waitForTimeout(2000);
        page.goForward();
        page.waitForLoadState();

        Boolean UnameVisibilityFwd = page.locator(username).isVisible();
        System.out.println("Visibility of Username: " + UnameVisibilityFwd);

        Boolean PassVisibilityFwd = page.locator(password).isVisible();
        System.out.println("Visibility of Password: " + PassVisibilityFwd);

        page.waitForTimeout(2000);

        Boolean SignInVisibilityFwd = page.locator(SignIn).isVisible();
        System.out.println("Visibility of SignIn: " + PassVisibilityFwd);


        if (UnameVisibilityFwd && PassVisibilityFwd && SignInVisibilityFwd.equals(true)) {

            return true;

        } else {

            return false;
        }


    }

    public boolean CheckLogOutWithBackButton() {


        page.goBack();
        page.waitForLoadState();

        Boolean UnameVisibilityBack = page.locator(username).isVisible();
        System.out.println("Visibility of Username: " + UnameVisibilityBack);

        Boolean PassVisibilityBack = page.locator(password).isVisible();
        System.out.println("Visibility of Password: " + PassVisibilityBack);

        page.waitForTimeout(2000);

        Boolean SignInVisibilityBack = page.locator(SignIn).isVisible();
        System.out.println("Visibility of SignIn: " + PassVisibilityBack);

        if (UnameVisibilityBack && PassVisibilityBack && SignInVisibilityBack.equals(true)) {

            return true;

        } else {

            return false;
        }

    }


}



