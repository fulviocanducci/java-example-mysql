/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Developer
 */
public class Connection {
    public static java.sql.Connection createConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/ex"; //Nome da base de dados
        String user = "root";
        String password = "senha";
        return DriverManager.getConnection(url, user, password);
    }
}
