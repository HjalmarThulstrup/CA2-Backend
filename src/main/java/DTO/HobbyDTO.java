/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entity.Person;
import java.util.List;

/**
 *
 * @author ralle
 */
public class HobbyDTO {
    private String name;
    private String desc;
    private List<String> personList;
    
    public HobbyDTO() {
        
    }
    public HobbyDTO(String name, String desc, List<Person> personList) {
        this.name = name;
        this.desc = desc;
        
        for(Person p : personList) {
            this.personList.add(p.toString());
        }
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
