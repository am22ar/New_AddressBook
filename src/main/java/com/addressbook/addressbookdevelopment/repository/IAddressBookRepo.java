package com.addressbook.addressbookdevelopment.repository;


import com.addressbook.addressbookdevelopment.model.AddressBookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressBookRepo extends JpaRepository<AddressBookModel, Integer> {

    @Query(value = "select * from address_book_model where city= :city ", nativeQuery = true)
    List<String> findByCity(String city);
}
