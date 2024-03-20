package com.ticketing.portal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection open() {
        try {
            DBConfig conf = new DBConfig();

            String url = conf.getUrl();
            String user = conf.getUser();
            String password = conf.getPassword();

            Connection conn = DriverManager.getConnection(url, user, password);
            return  conn;
        }
        catch (Exception ex){
            return null;
        }
    }

}
