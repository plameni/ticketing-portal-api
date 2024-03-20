package com.ticketing.portal;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private String url;
    private String user;
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public DBConfig(){
        try {
            InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("application.properties");
            Properties prop = new Properties();

            if (input == null){
                System.out.println("Nemoguce otvoriti app properties");
                return;
            }

            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
