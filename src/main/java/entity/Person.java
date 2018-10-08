/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DTO.PersonDTO;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ralle
 */
@Entity
@Table(name = "Person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
    , @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email")
    , @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Person.findByPhone", query = "SELECT DISTINCT p FROM Person p INNER JOIN p.phoneList pl WHERE pl.number = :phoneNumber")
    , @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName")})
@XmlRootElement
public class Person implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "lastName")
    private String lastName;
    @ManyToMany(mappedBy = "personList", cascade = CascadeType.PERSIST)
    private List<Hobby> hobbyList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "person")
    private List<Phone> phoneList;
    @JoinColumn(name = "fkAddressId", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Address address;

    public Person()
    {
    }

    public Person(Integer id)
    {
        this.id = id;
    }

    public Person(String email, String firstName, String lastName, List<Hobby> hobbyList, List<Phone> phoneList, Address address)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbyList = hobbyList;
        this.phoneList = phoneList;
        this.address = address;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @XmlTransient
    public List<Hobby> getHobbyList()
    {
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList)
    {
        this.hobbyList = hobbyList;
    }

    @XmlTransient
    public List<Phone> getPhoneList()
    {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList)
    {
        this.phoneList = phoneList;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Person[ id=" + id + " ]";
    }

}
