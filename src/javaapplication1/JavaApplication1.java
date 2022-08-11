/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import db.Cliente;
import db.Connection;
import db.DalClient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Developer
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        java.sql.Connection connection = Connection.createConnection();
        DalClient dal = new DalClient(connection);
        List<Cliente> clientes = dal.getClientes();
        for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
            Cliente next = iterator.next();
            System.out.print(next.getCodigo());
            System.out.println(next.getNome());
        }
    }
    
}
