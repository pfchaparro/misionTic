/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;

/**
 *
 * @author pablo
 */
public class PersonClass {
    private String document;
    private String name;
    private String lastName;    
    private String age;
    private String gender;

    public PersonClass(String document, String name, String lastName, String age, String gender) {
        this.document = document;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    /**
     * @return the document
     */
    public String getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

 
    public void talk(){
        System.out.println( this.name + " is talking.");
    }  
    
    public void rest(){
        System.out.println(this.name + " is resting.");
    }
    
    public void eat(){
        System.out.println(this.name + " is eating.");
    }
}
