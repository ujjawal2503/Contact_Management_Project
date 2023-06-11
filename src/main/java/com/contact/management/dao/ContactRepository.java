package com.contact.management.dao;

import com.contact.management.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,String> {
    public List<ContactEntity> findByFirstName(String firstName);
    public List<ContactEntity> findByLastName(String lastName);
    public Optional<ContactEntity> findByEmail(String email);
}
