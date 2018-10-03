/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entity.Person;
import java.util.List;
import entity.Hobby;
import java.util.ArrayList;

/**
 *
 * @author ralle
 */
public class HobbyDTO {
    private Integer id;
    private String name;
    private String desc;
    private List<String> personList;
    
    public HobbyDTO() {
        
    }
    public HobbyDTO(Hobby hobby) {
        this.name = hobby.getName();
        this.desc = hobby.getDescription();
        this.id = hobby.getId();
        personList = new ArrayList<>();
        if(hobby.getPersonList() != null) {
            hobby.getPersonList().forEach((p) -> {
                this.personList.add(p.getFirstName());
            });
        }
        
        

    }
    
    public HobbyDTO(String name, String desc, Integer id) {
        this.name = name;
        this.desc = desc;
        
//        for(Person p : personList) {
//            this.personList.add(p.toString());
//        }
        this.id = id;
            
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getPersonList() {
        return personList;
    }

    public void setPersonList(List<String> personList) {
        this.personList = personList;
    }
    
    
}
