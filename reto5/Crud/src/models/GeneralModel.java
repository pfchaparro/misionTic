package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.db.Models;

/**
 *
 * @author pablo
 */
public class GeneralModel extends Models {
    public ArrayList<Story> getStoryList(){
        ArrayList<Story> stoList = new ArrayList();
        
        try(Connection conn = super.conectar()){
            String query = "SELECT sto.id, "
                    + "sto.user_id, "
                    + "sto.story_type_id, "
                    + "sto.title, "
                    + "sto.short_description, "
                    + "sto.story, "
                    + "sto.autor, "
                    + "sto.timestamp "
                    + "FROM story sto";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            while( result.next() ){
                Story sto = new Story();
                sto.setId(result.getInt("id"));
                sto.setStory_type_id(result.getInt("story_type_id"));
                sto.setTitle(result.getString("title"));
                sto.setShort_description(result.getString("short_description"));
                sto.setStory(result.getString("story"));
                sto.setAutor(result.getString("autor"));
                sto.setTimestamp(result.getString("timestamp"));
                stoList.add(sto);
            }
        } catch(Exception e){
            System.out.println("No se puede cargar la lista de story");
        }
        
        return stoList;
    }

    @Override
    public Object find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
