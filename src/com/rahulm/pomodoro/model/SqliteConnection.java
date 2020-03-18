package com.rahulm.pomodoro.model;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import net.ucanaccess.jdbc.JackcessOpenerInterface;
import com.healthmarketscience.jackcess.util.ErrorHandler;
//import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

public class SqliteConnection {
    public static Connection Connector(){
        try {
//            Class.forName("org.sqlite.JDBC");
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:resources/database/usersDb.sqlite");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://resources//database//usersDb.accdb");
            return conn;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
