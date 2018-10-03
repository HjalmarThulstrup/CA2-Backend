/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.HobbyDTO;
import interfaces.HobbyFacadeInterface;
import entity.Hobby;
import javax.persistence.Persistence;
import mappers.HobbyMapper;


public class HobbyFacade implements HobbyFacadeInterface{
    HobbyMapper hm = new HobbyMapper(Persistence.createEntityManagerFactory("jpapu"));
    
    @Override
    public HobbyDTO addHobby(Hobby hobby) {
        return hm.addHobby(hobby);
    }

    @Override
    public HobbyDTO deleteHobby(int id) {
        return hm.deleteHobby(id);
    }

    @Override
    public HobbyDTO editHobby(Hobby hobby) {
        return hm.editHobby(hobby);
    }

    @Override
    public int getHobbyPopularity(Integer id) {
        return hm.getHobbyPopularity(id);
    }

    @Override
    public HobbyDTO getHobby(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
