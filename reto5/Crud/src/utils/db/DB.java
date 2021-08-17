/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils.db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pablo
 **/
public class DB {
    private String driver = "com.mysql.jdbc.Driver";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/forum?characterEncoding=utf8";

    public DB() {
        try{
            Class.forName( driver );
        } catch(ClassNotFoundException e){
        }
    }
    
    public Connection conectar(){
        Connection conn = null;
        try{
            conn = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Conectado");
        } catch(SQLException e){
            System.err.println("No se puede conectar a la base de datos: " + this.url);
        }
        return conn;
    }
}
