package com.jewelhaat.db.service;
 
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jewelhaat.model.Person;
 
public class SpringMongoTest {
 
    public static final String DB_NAME = "BIMALSAHAY";
    public static final String PERSON_COLLECTION = "Person";
    public static final String MONGO_HOST = "localhost";
    public static final int MONGO_PORT = 27017;
 
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/application-config.xml");
        
        PersonDAO personDAO = ctx.getBean("personDAO", PersonDAO.class);
         
        Person p = new Person("6", "Nikunj Singh", "Cupertino", 31, "MALE");
         
        //create
        personDAO.create(p);
        System.out.println("Generated ID="+p.getId());
         
        //read
        Person p1 = personDAO.readById(p.getId());
        System.out.println("Retrieved Person="+p1);
         
        //update
        p1.setName("David");p1.setAddress("SFO, USA");
        personDAO.update(p1);
        Person temp = personDAO.readById(p1.getId());
        System.out.println("Retrieved Person after update="+temp);
         
        //delete
       // int count = personDAO.deleteById(p1.getId());
        //System.out.println("Number of records deleted="+count);
         
        ctx.close();
    }
}