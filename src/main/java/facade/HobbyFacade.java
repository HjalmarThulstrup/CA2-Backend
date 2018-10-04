/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.HobbyDTO;
import interfaces.HobbyFacadeInterface;
import entity.Hobby;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import mappers.HobbyMapper;


public class HobbyFacade implements HobbyFacadeInterface{
    HobbyMapper hm = new HobbyMapper(Persistence.createEntityManagerFactory("jpaputest"));
    
    @Override
    public HobbyDTO addHobby(Hobby hobby) {
        return new HobbyDTO(hm.addHobby(hobby));
    }

    @Override
    public HobbyDTO deleteHobby(int id) {
        return new HobbyDTO(hm.deleteHobby(id));
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
        return new HobbyDTO(hm.getHobby(id));
    }
    
    public List<HobbyDTO> getHobby() {
        List<Hobby> hobbys = hm.getHobby();
        List<HobbyDTO> hdtos = new ArrayList<>();
        
        hobbys.forEach((hobby) -> {
            hdtos.add(new HobbyDTO(hobby));
        });
        
        return hdtos;
    }
    
}
