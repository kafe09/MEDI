package com.example.katharinafeiertag.mediary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by scheerbernhard on 30.03.18.
 */

public class ProgrammController {
    Connection con = null;
    Statement meinStatement = null;
    ResultSet meinRs = null;
    String dbBenutzer = null;
    String verbindung;


    ProgrammController() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"
                    + "MEDiary?user=root&password=root");


            dbBenutzer = "benutzer";

            verbindung = "Verbindung erfolgreich";
        } catch (SQLException ex) {
            Logger.getLogger(HauptmenuActivity.class.getName()).log(Level.SEVERE, null, ex);
            verbindung = "Verbindung fehlgeschlagen";
        }
        System.out.println(verbindung);
    }
}



