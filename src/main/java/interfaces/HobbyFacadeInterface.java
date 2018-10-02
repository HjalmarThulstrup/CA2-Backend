/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author wicktus
 */
public interface HobbyFacadeInterface {

    public HobbyDTO addHobby(Hobby hobby);

    public HobbyDTO deleteHobby(int id);

    public HobbyDTO editHobby(Hobby hobby);

    public int getHobbyPopularity(Hobby hobby);
	
	public HobbyDTO getHobby(int id);
}
