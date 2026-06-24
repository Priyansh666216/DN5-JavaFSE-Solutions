package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository — marks this as a Spring-managed DAO component
// JpaRepository<Country, String>
//   Country → the entity this repository manages
//   String  → the type of the primary key (co_code is a String)
//
// By extending JpaRepository, we get these methods for FREE:
//   findAll()         — fetch all rows
//   findById(id)      — fetch one row by primary key
//   save(entity)      — insert or update a row
//   deleteById(id)    — delete a row by primary key
//   count()           — count total rows
//   ... and many more
//
// No implementation needed — Spring Data JPA generates it at runtime.
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

}
