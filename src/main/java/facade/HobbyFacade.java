/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.HobbyDTO;
import interfaces.HobbyFacadeInterface;
import entity.Hobby;
import exceptions.HobbyNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import mappers.HobbyMapper;


public class HobbyFacade implements HobbyFacadeInterface{
    HobbyMapper hm = new HobbyMapper(Persistence.createEntityManagerFactory("jpapu"));
    
    @Override
    public HobbyDTO addHobby(Hobby hobby) {
        return new HobbyDTO(hm.addHobby(hobby));
    }

    @Override
    public HobbyDTO deleteHobby(int id) {
        Hobby hobby = hm.deleteHobby(id);
        if (hobby == null)
            return null;
        
        return new HobbyDTO(hobby);
    }

    @Override
    public HobbyDTO editHobby(Hobby hobby) {
        return new HobbyDTO(hm.editHobby(hobby));
    }

    @Override
    public int getHobbyPopularity(Integer id) {
        return hm.getHobbyPopularity(id);
    }

    @Override
    public HobbyDTO getHobby(int id) {
        Hobby returnHobby = hm.getHobby(id);
        if(returnHobby == null) {
            return null;
        }
         
        return new HobbyDTO(returnHobby);
    }
    
    public List<HobbyDTO> getHobby() throws HobbyNotFoundException {
        List<Hobby> hobbys = hm.getHobby();
        List<HobbyDTO> hdtos = new ArrayList<>();
        
        hobbys.forEach((hobby) -> {
            hdtos.add(new HobbyDTO(hobby));
        });
        
        return hdtos;
    }
    
}
