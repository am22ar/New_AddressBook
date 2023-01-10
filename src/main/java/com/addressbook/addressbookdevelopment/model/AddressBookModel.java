package com.addressbook.addressbookdevelopment.model;

import com.addressbook.addressbookdevelopment.dto.AddressBookDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AddressBookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;
    private String firstName;
   private String lastName;
   private String address;
   private long phoneNum;
   private String email;
   private String city;
   private String state;

    public AddressBookModel() {

    }

    public AddressBookModel(AddressBookDto addressBookDto) {
        this.firstName = addressBookDto.firstName;
        this.lastName = addressBookDto.lastName;
        this.address = addressBookDto.address;
        this.phoneNum = addressBookDto.phoneNum;
        this.email = addressBookDto.email;
        this.city = addressBookDto.city;
        this.state = addressBookDto.state;
    }

    public AddressBookModel(int personId, AddressBookDto addressBookDto) {
        this.personId = personId;
        this.firstName = addressBookDto.firstName;
        this.lastName = addressBookDto.lastName;
        this.address = addressBookDto.address;
        this.phoneNum = addressBookDto.phoneNum;
        this.email = addressBookDto.email;
        this.city = addressBookDto.city;
        this.state = addressBookDto.state;
    }


//    public int getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(int personId) {
//        this.personId = personId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public long getPhoneNum() {
//        return phoneNum;
//    }
//
//    public void setPhoneNum(long phoneNum) {
//        this.phoneNum = phoneNum;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
}
