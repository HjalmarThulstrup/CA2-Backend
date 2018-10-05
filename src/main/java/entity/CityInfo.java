/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ralle
 */
@Entity
@Table(name = "CityInfo")
@NamedQueries({
    @NamedQuery(name = "CityInfo.findAll", query = "SELECT c FROM CityInfo c")
    , @NamedQuery(name = "CityInfo.findByZipCode", query = "SELECT c FROM CityInfo c WHERE c.zipCode = :zipCode")
    , @NamedQuery(name = "CityInfo.findByCity", query = "SELECT c FROM CityInfo c WHERE c.city = :city")})
@XmlRootElement
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "zipCode")
    private String zipCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "City")
    private String city;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityInfo")
    private List<Address> addressList;

    public CityInfo() {
    }

    public CityInfo(String zipCode) {
        this.zipCode = zipCode;
    }

    public CityInfo(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zipCode != null ? zipCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CityInfo)) {
            return false;
        }
        CityInfo other = (CityInfo) object;
        if ((this.zipCode == null && other.zipCode != null) || (this.zipCode != null && !this.zipCode.equals(other.zipCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return zipCode + " " + city;
    }
    
}
