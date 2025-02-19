package org.jms.pagefactory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PageFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    Properties prop;

    String username="//*[@type='text']";
    String password="//*[@type='password']";


    public  Page initBrowser(Properties prop)
    {
        String browserName=prop.getProperty("Browser");
        System.out.println(browserName);
        playwright=Playwright.create();
        switch (browserName.toLowerCase())
        {
            case "chromium":
                browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "firefox":
                browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "webkit":
                browser=  playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            default:
                System.out.println("no parameter");
                break;
        }

        context=browser.newContext();
        page=context.newPage();

        // Evaluate the screen width and height
        int screenWidth = (int) page.evaluate("() => window.screen.width");
        int screenHeight = (int) page.evaluate("() => window.screen.height");
        page.setViewportSize(screenWidth, screenHeight);

        page.navigate(prop.getProperty("url"));
        page.locator(username).fill(prop.getProperty("username"));
        page.locator(password).fill(prop.getProperty("password"));
        page.keyboard().press("Enter");

        return page;
    }



    public Properties initProp() throws IOException {
        FileInputStream fio=new FileInputStream("Base.properties");
        prop=new Properties();
        prop.load(fio);
        return prop;
    }


}
