package com.addressbook.addressbookdevelopment.service;

import com.addressbook.addressbookdevelopment.Util.AddressBookToken;
import com.addressbook.addressbookdevelopment.dto.AddressBookDto;
import com.addressbook.addressbookdevelopment.exception.AddressBookException;
import com.addressbook.addressbookdevelopment.model.AddressBookModel;
import com.addressbook.addressbookdevelopment.repository.IAddressBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService implements IAddressBookService{
    @Autowired
    IAddressBookRepo iAddressBookRepo;

    @Autowired
    AddressBookToken addressBookToken;
    List<AddressBookModel> addressBookModelList = new ArrayList<>();

    public AddressBookModel addPerson(AddressBookDto addressBookDto) throws AddressBookException {
        AddressBookModel addressBookModel1 =new AddressBookModel(addressBookDto);
       // String token = addressBookToken.createToken(Integer.valueOf(addressBookModel1.getPersonId()));
        return iAddressBookRepo.save(addressBookModel1);
    }

    public AddressBookModel getById(int personId) {
        //checking whether the id is
        Optional<AddressBookModel> addressBookModel= iAddressBookRepo.findById(personId);
        if(addressBookModel.isPresent()){
            return addressBookModel.get();
        }
        else {
            throw new AddressBookException("id is not present");
        }
    }
    public AddressBookModel getByToken(String token){
        //decode token
        int personId = addressBookToken.decodeToken(token);
        Optional<AddressBookModel> addressBookModel = iAddressBookRepo.findById(personId);
        if(addressBookModel.isPresent()){
            return iAddressBookRepo.findById(Integer.valueOf(personId)).get();
        }
        else {
            throw new AddressBookException("Token not found...");
        }
    }
    public List<AddressBookModel> getAll() throws AddressBookException{
        List<AddressBookModel> addressBookModelList = iAddressBookRepo.findAll();
        return addressBookModelList;
    }
    public AddressBookModel updateById(int personId, AddressBookDto addressBookDto) throws  AddressBookException{
        AddressBookModel addressBookModel = addressBookModelList.stream()
                .filter(addressBookModel1 -> addressBookModel1.getPersonId()==personId)
                .findFirst().orElseThrow (()->new AddressBookException("Person doesn't exists.."));
        return addressBookModel;
    }

    public AddressBookModel updateByToken(int personId, String token, AddressBookDto addressBookDto){
        //first decode the token and will get an id
        int id = addressBookToken.decodeToken(token);
        //find data of that id in database
       Optional<AddressBookModel> addressBookModel = iAddressBookRepo.findById(id);
        if(addressBookModel.isPresent()){

            AddressBookModel addressBookModel1 = new AddressBookModel(addressBookDto);
            //setting the id to update data
            addressBookModel1.setPersonId(personId);
            //save changes/modifications done
            AddressBookModel addressBookModel2 = iAddressBookRepo.save(addressBookModel1);
            return addressBookModel2;
        }
        else {
            throw new AddressBookException("Data is not updated for this token...");
        }
    }

    public AddressBookModel deleteById(int personId) throws AddressBookException{
          iAddressBookRepo.deleteById(personId);
         return null;
    }
    public List<String> findByCity(String city){
        return iAddressBookRepo.findByCity(city);
    }
}
