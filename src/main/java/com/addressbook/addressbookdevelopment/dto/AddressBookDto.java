package com.addressbook.addressbookdevelopment.dto;


import lombok.AllArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
public class AddressBookDto {

    @NotEmpty(message = "Employee Firstname cannot be null!!!")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "FirstName is Invalid!!")
    public String firstName;
    @NotEmpty(message = "Employee Lastname cannot be null!!!")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "LastName is Invalid!!")
    public String lastName;
    @NotEmpty(message = "Address feild cannot be empty..")
    public String address;
   // @NotEmpty(message = "Please enter your mobile number")
    public long phoneNum;

    @NotEmpty(message = "Please enter your e-mail")
    public String email;
    @NotEmpty(message = "Please enter your city")
    public String city;
    @NotEmpty(message = "Please enter your state")
    public String state;

//    public AddressBookDto(String firstName, String lastName, String address, long phoneNum,String email, String city, String state) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.phoneNum = phoneNum;
//        this.email = email;
//        this.city = city;
//        this.state = state;
//    }
}
