/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.AddressDTO;
import entity.Address;
import interfaces.AddressFacadeInterface;
import mappers.AddressMapper;

/**
 *
 * @author juanni420
 */
public class AddressFacade implements AddressFacadeInterface {

    private AddressMapper addressmapper;

    public AddressFacade(AddressMapper addressmapper) {
        this.addressmapper = addressmapper;
    }

    @Override
    public AddressDTO createAddress(Address addr) {
        return addressmapper.createAddress(addr);
    }

    @Override
    public boolean deleteAddress(int id) {
        return addressmapper.deleteAddress(id);
    }

    @Override
    public AddressDTO editAddress(Address addr) {
        return addressmapper.editAddress(addr);
    }

    @Override
    public AddressDTO getAddress(int id) {
        return addressmapper.getAddress(id);
    }

}