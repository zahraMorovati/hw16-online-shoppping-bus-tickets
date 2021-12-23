package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
    private static Connection connection;

    public static void creatDatabase(){
        String dataBaseName = "ticket_shop_db";
         String dataBaseUser = "account_mangment_system_user";
         String dataBasePassword = "1234";
         boolean dropAllTableOnApplicationStartup = true;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "george1378");
            Statement statement = con.createStatement();
            statement.executeUpdate(String.format("create user if not exists '%s' identified by '%s'", dataBaseUser, dataBasePassword));
            statement.executeUpdate("create database if not exists " + dataBaseName);
            statement.executeUpdate(String.format("grant all privileges on `%s`.* to '%s'", dataBaseName, dataBaseUser));
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, dataBaseUser, dataBasePassword);
        }catch (ClassNotFoundException | SQLException | RuntimeException e){
            System.out.println("\033[0;31m"+"Exception: cannot connect to dataBase!"+"\033[0m");
            System.out.println("\033[0;33m"+"hint: check your dataBase password!"+"\033[0m");
        }
    }


}
