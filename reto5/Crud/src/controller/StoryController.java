/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import models.Story;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author pablo
 */
public class StoryController {
    /*
    public ArrayList<Story> getDepartamentoList(){
        ArrayList<Story> depList;
        
        depList = new GeneralModel().getDepartamentoList();
        
        return depList;
    }
    */
    
    public void deleteStory(Integer id){
        Story sto = (Story) new Story().find(id);
        sto.delete();
    }
    
    public void saveStory(Integer story_type_id, String title, String short_description, String story, String autor) throws Exception{
        Story new_sto = null;
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        new_sto = new Story();
        new_sto.setStory_type_id(story_type_id);
        new_sto.setTitle(title);
        new_sto.setShort_description(short_description);
        new_sto.setStory(story);
        new_sto.setAutor(autor);

        calendar.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new_sto.setTimestamp(formatter.format(calendar.getTime())); 
        
        new_sto.save();
    }
}
