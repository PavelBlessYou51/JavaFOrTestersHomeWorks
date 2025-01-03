package ru.stqa.mantis.manager;

import org.apache.axis.soap.SOAP11Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private Properties properties;
    private String string;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private JamesAPIHelper jamesAPIHelper;
    private DeveloperMailHelper developerMailHelper;
    private RestAPIHelper restAPIHelper;
    private SoapAPIHelper soapAPIHelper;

    public void init(String browser, Properties properties) {
        this.properties = properties;
        this.string = browser;

    }

    public WebDriver driver() {
        if (driver == null) {
            if ("firefox".equals(string)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(string)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().fullscreen();
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper JamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public JamesAPIHelper JamesAPI() {
        if (jamesAPIHelper == null) {
            jamesAPIHelper = new JamesAPIHelper(this);
        }
        return jamesAPIHelper;
    }


    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public DeveloperMailHelper developerMail() {
        if (developerMailHelper == null) {
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }

    public RestAPIHelper rest() {
        if (restAPIHelper == null) {
            restAPIHelper = new RestAPIHelper(this);
        }
        return restAPIHelper;
    }

    public SoapAPIHelper soap() {
        if (soapAPIHelper == null) {
            soapAPIHelper = new SoapAPIHelper(this);
        }
        return soapAPIHelper;
    }


    public String property(String name) {
        return properties.getProperty(name);
    }
}
