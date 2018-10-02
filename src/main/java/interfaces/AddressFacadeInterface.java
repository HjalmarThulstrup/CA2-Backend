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
public interface AddressFacadeInterface {

    public AddressDTO createAddress(Address addr);

    public AddressDTO deleteAddress(int id);

    public AddressDTO editAddress(Address addr);
	
	public AddressDTO getAddress(int id);
}
