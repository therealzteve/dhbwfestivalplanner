package com.config;

import java.io.BufferedReader;
 
import java.io.File;
import java.io.FileReader;
 
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
 
public class DatabaseResetter  {
 
    private static Properties dbProperties;
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    static
    {
        try
        {
        	dbProperties = new PropertyFactory().getDbProperties();
            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("*** Driver loaded");
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
        }
 
    }
 
    private static final String URL = dbProperties.getProperty("url");
    private static final String USER = dbProperties.getProperty("username");
    private static final String PASSWORD = dbProperties.getProperty("password");
    private static String INSTRUCTIONS = new String();
 
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
 
    public static void resetDatabase(String path) throws SQLException
    {
        String s            = new String();
        StringBuffer sb = new StringBuffer();
 
        try
        {
            FileReader fr = new FileReader(new File(path));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
 
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            br.close();
 
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
 
            Connection c = DatabaseResetter.getConnection();
            Statement st = c.createStatement();
 
            for(int i = 0; i<inst.length; i++)
            {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals(""))
                {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
   
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
 
    }
}