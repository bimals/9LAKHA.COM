package com.jewelhaat.db.service;

import org.springframework.stereotype.Service;

import com.jewelhaat.model.Person;

@Service
public interface PersonDAO {
 
    public void create(Person p);
     
    public Person readById(String id);
     
    public void update(Person p);
     
    public int deleteById(String id);
}