package com.addressbook.addressbookdevelopment.controller;


import com.addressbook.addressbookdevelopment.Util.AddressBookToken;
import com.addressbook.addressbookdevelopment.dto.AddressBookDto;
import com.addressbook.addressbookdevelopment.dto.ResponseDto;
import com.addressbook.addressbookdevelopment.exception.AddressBookException;
import com.addressbook.addressbookdevelopment.model.AddressBookModel;
import com.addressbook.addressbookdevelopment.model.EmailModel;
import com.addressbook.addressbookdevelopment.service.IAddressBookService;
import com.addressbook.addressbookdevelopment.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class AddressBookController {

    @Autowired
    IEmailService emailService;
    @Autowired
    IAddressBookService addressBookService;

    @Autowired
    AddressBookToken addressBookToken;
    List<Object> list = new ArrayList<>();

    @PostMapping("/addPerson")
    public ResponseEntity<ResponseDto> addPerson(@Valid @RequestBody AddressBookDto addressBookDto){
        AddressBookModel addressBookModel = addressBookService.addPerson(addressBookDto);
        String idToken = addressBookToken.createToken(addressBookModel.getPersonId());
        EmailModel emailModel = new EmailModel(addressBookModel.getEmail(),
                "New Person data added..","Name: "+addressBookModel.getFirstName()+
                "  ->token  ->"+idToken);
        emailService.sendEmail(emailModel);
        ResponseDto responseDto = new ResponseDto("New Contact Added: ",addressBookModel,idToken);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        log.info(String.valueOf(idToken));
        return response;
        //list.add(addressBookService.addPerson(addressBookDto));
        //return addressBookService.addPerson(addressBookDto);
    }
    @GetMapping("/getById/{personId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable(value = "personId") int personId) throws AddressBookException {
        AddressBookModel addressBookModel = addressBookService.getById(personId);
        ResponseDto responseDto = new ResponseDto("Data of Given ID: ",addressBookModel,null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
//        list.add(addressBookService.getById(personId));
//        return addressBookService.getById(personId);
    }
    @GetMapping("/getByToken/{token}")
    public ResponseEntity<ResponseDto> getByToken(@PathVariable String token){
        AddressBookModel addressBookModel = addressBookService.getByToken(token);
        ResponseDto responseDto = new ResponseDto("Data of Given Token: ",addressBookModel,token);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto,HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAll() throws AddressBookException{
        List<AddressBookModel> addressBookModel = addressBookService.getAll();
        ResponseDto responseDto = new ResponseDto("Fetch all data: ",addressBookModel,null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
//        list.add(addressBookService.getAll());
//        return addressBookService.getAll();
    }
    @PutMapping("/updateById/{personId}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable int personId, @Valid @RequestBody AddressBookDto addressBookDto) throws AddressBookException {
        AddressBookModel addressBookModel = addressBookService.updateById(personId,addressBookDto);
        ResponseDto responseDto = new ResponseDto("Updated Data: ",addressBookModel,null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
//        list.add(addressBookService.updateById(personId,addressBookDto));
//        return addressBookService.updateById(personId,addressBookDto);
    }
    @PutMapping("/updateByToken")
    public ResponseEntity<ResponseDto> updateByToken(@RequestParam int personId, @RequestParam String token,@RequestBody AddressBookDto addressBookDto){
        //passing id along with token because we are not saving the token generated in database
        AddressBookModel addressBookModel = addressBookService.updateByToken(personId,token,addressBookDto);
        ResponseDto responseDto = new ResponseDto("Update By Token: ",addressBookModel,token);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponseDto> deleteById(@RequestParam int personId)throws AddressBookException{
        AddressBookModel addressBookModel = addressBookService.deleteById(personId);
        ResponseDto responseDto = new ResponseDto("Deleted Data: ",addressBookModel,null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
//        list.add(addressBookService.deleteById(personId));
//        addressBookService.deleteById(personId);
//        return "Deletion Success...";
    }
    @GetMapping("/getByCity/{city}")
    public ResponseEntity<ResponseDto> findByCity(@PathVariable("city") String city){
        List<String> list1= null;
        list1 = addressBookService.findByCity(city);
        ResponseDto responseDto = new ResponseDto("Employee of Specific City!!!",list1,null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}
