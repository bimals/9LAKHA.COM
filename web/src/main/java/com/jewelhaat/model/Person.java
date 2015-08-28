package com.jewelhaat.model;

import org.springframework.data.annotation.Id;

public class Person {
	
	public Person(String id, String name, String address, int age, String gender, String userName) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.userName = userName;
	}

	@Id
    private String id;
    
    private String name;
    private String address;
	int age;
	String gender;
	String userName;
		
	public Person() {
		
	}
	
	public Person(String id, String name, String address, int age, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void printPerson() {
		
	}
	
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
     
    @Override
    public String toString(){
        return id+"::"+name+"::"+address;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}


