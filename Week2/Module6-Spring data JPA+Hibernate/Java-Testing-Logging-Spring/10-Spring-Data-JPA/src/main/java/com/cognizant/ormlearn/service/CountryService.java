package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Service — marks this as a Spring-managed service component (business layer)
@Service
public class CountryService {

    // @Autowired — Spring automatically injects the CountryRepository bean here
    // No need to call 'new CountryRepository()' — Spring handles it
    @Autowired
    private CountryRepository countryRepository;

    // @Transactional — wraps this method in a database transaction
    // If an exception occurs, the transaction is rolled back automatically
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
