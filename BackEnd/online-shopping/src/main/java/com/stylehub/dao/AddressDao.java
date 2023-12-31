package com.stylehub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stylehub.model.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

}
