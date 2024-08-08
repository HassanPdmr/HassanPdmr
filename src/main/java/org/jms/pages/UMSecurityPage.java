package org.jms.pages;

import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class UMSecurityPage {

    private Page page;

    private String username = "id=siginIn_id";
    private String password = "id=signin_pwd";
    private String SignIn = "//button[normalize-space()='Sign in']";
    private String profile = "//p[normalize-space()='Profile']";
    private String Logout = "//p[normalize-space()='Logout']";


    public UMSecurityPage(Page page) {
        this.page = page;

    }


    public void loginData(String Uname, String Pwd) {


        page.locator(username).fill(Uname);
        page.locator(password).fill(Pwd);
        page.waitForSelector(SignIn).click();


    }



    public List<String> loginLogoutCorrectData(String Uname, String Pwd, String EmpId) {


        page.evaluate("window.scrollBy(0, 700)");
        page.waitForSelector(Logout).click();
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

        page.evaluate("window.scrollBy(0, 700)");
        page.waitForSelector(Logout).click();
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




}
