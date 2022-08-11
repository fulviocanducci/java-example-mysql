/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Developer
 */
public class DalClient {
    private final java.sql.Connection connection;
    public DalClient(java.sql.Connection connection){
        this.connection = connection;
    }
    
    public List<Cliente> getClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Statement sts = connection.createStatement();
        try (ResultSet resultCliente = sts.executeQuery("SELECT * FROM pessoa")) {
            while (resultCliente.next())
            {
                Cliente c = new Cliente();
                c.setCodigo(resultCliente.getInt("codigo"));
                c.setNome(resultCliente.getString("nome"));
                clientes.add(c);
            }
            resultCliente.close();
        }        
        List<Integer> codigos = clientes
                .stream()
                .map(c -> c.getCodigo())
                .distinct()
                .collect(Collectors.toList());
        StringBuilder strSql = new StringBuilder();
        strSql.append("SELECT * FROM telefone WHERE pessoa_codigo in(");
        for (int i = 0; i < codigos.size(); i++) {
            if (i > 0) {
                strSql.append(",");    
            }
            strSql.append("?");            
        }
        strSql.append(") ORDER BY pessoa_codigo ASC");
        PreparedStatement queryTelefone = connection.prepareStatement(strSql.toString());
        for (int i = 0; i < codigos.size(); i++) {
            queryTelefone.setInt((i + 1),codigos.get(i));    
        }
        try(ResultSet resultTelefone = queryTelefone.executeQuery()){
            while(resultTelefone.next()) {
                try {
                    Telefone t = new Telefone();
                    t.setCodigo(resultTelefone.getInt("codigo"));
                    t.setDdd(resultTelefone.getString("ddd"));
                    t.setNumero(resultTelefone.getString("numero"));
                    Integer pessoa_codigo = resultTelefone.getInt("pessoa_codigo");
                    Cliente cliente = clientes
                            .stream()
                            .filter(w -> Objects.equals(w.getCodigo(), pessoa_codigo))
                            .findFirst()
                            .orElse(null);
                    if (cliente != null){
                        t.setCliente(cliente);
                        cliente.setTelefone(t);                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DalClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            resultTelefone.close();
        }
        return clientes;
    } 
}
