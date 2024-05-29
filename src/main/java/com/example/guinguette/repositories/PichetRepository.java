package com.example.guinguette.repositories;


import com.example.guinguette.entities.Pichet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PichetRepository extends JpaRepository<Pichet, Long> {

}