/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import utils.db.DB;
import utils.db.Models;

/**
 *
 * @author pablo
 */
public class Story extends Models {
    private DB db = new DB();
    
    private Integer id;
    private Integer user_id;
    private Integer story_type_id;
    private String title;
    private String short_description;
    private String story;
    private String autor;
    private String timestamp;
    private String update;
    
    public Story() {
    }
    
    public Object find(String title, String short_description) {
        Story sto = null;
        
        try(Connection conn = super.conectar()){
            String query = 
                    "SELECT sto.id, "
                    + "sto.user_id, "
                    + "sto.story_type_id, "
                    + "sto.title, "
                    + "sto.short_description "
                    + "sto.story "
                    + "sto.autor "
                    + "sto.timestamp "
                    + "FROM story sto WHERE sto.title LIKE '%?%' OR sto.short_description LIKE '%?%'";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, short_description);
            ResultSet result = statement.executeQuery();
            
            int row_count = 0;
            while( result.next() ){
                row_count++;
                   
                this.id = result.getInt("id");
                this.story_type_id = result.getInt("story_type_id");
                this.title = result.getString("title");
                this.short_description = result.getString("short_description");
                this.story = result.getString("story");
                this.autor = result.getString("autor");
            //    this.timestamp = result.getDate("timestamp");
                sto = this;
            }
            
            if( row_count == 0)
                throw new Exception("No se encontro el registro con el titulo= "+ title + " o descripcion corta = " + short_description +  "en la tabla story");
            
        } catch(Exception e){
            System.out.println("No se encontro el registro con el titulo= "+ title + " o descripcion corta = " + short_description +  "en la tabla story");
        }
        
        return sto;
    }
    
    @Override
    public Integer save() {
        Integer id = null;
        String query;
        try(Connection conn = super.conectar()){
            
            if( this.getId() == null){
                query = "INSERT INTO story (story_type_id, title, short_description, story, timestamp, autor) " 
                        + "VALUES (?,?,?,?,?,?)";
            } else {
                query = "UPDATE story set story_type_id = ?,title = ?, short_description = ?, story = ?, autor = ?, last_update = ?" 
                        + " WHERE id = ? ";
            }

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, this.getStory_type_id());
            statement.setString(2, this.getTitle());
            statement.setString(3, this.getShort_description());
            statement.setString(4, this.getStory());
            statement.setString(5, this.getAutor());
            statement.setString(6, this.timestamp); 
            statement.setString(7, this.update);
            
            if( this.getId() != null)
                statement.setInt(9, this.getId());
            
            int rows = statement.executeUpdate();
            
            if( rows > 0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if( generatedKeys.next() )
                    id = generatedKeys.getInt(1);
            }
            
        } catch(Exception e){
            System.out.println(e);
            System.err.println("No se puedo crear el registro en la tabla story.");
        }
        
        return id;
    }

    @Override
    public void delete() {
        try(Connection conn = super.conectar()){
            String query;
            query = "DELETE FROM story WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, this.getId());
            statement.executeUpdate();
        } catch(Exception e){
            System.err.println("No se puede eliminar el registro id= " + this.getId() + " de la tabla story");
        }
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStory_type_id() {
        return story_type_id;
    }

    public void setStory_type_id(Integer story_type_id) {
        this.story_type_id = story_type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    } 

    @Override
    public Object find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
