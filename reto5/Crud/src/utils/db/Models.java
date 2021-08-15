/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.db;
import java.sql.Connection;

/**
 *
 * @author pablo
 */
public abstract class Models {
    private DB db = new DB();

    public Models() {
    }
    
    public abstract Object find(Integer id);
    public abstract Integer save();
    public abstract void delete();
    
    
    public Connection conectar(){
        return this.db.conectar();
    }
}
