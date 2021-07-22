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
public class Person {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PersonClass person = new PersonClass("123456","Pablo","Chaparro","24","Male");
        
        System.out.println(person.getDocument()); 
        System.out.println(person.getName());
        System.out.println(person.getLastName());
        System.out.println(person.getAge());
        System.out.println(person.getGender());
        
        person.talk();
        person.rest();
        person.eat();
    }
    
}
