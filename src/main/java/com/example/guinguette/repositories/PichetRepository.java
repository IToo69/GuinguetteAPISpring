package com.example.guinguette.repositories;

import com.example.guinguette.entities.Pichet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PichetRepository extends JpaRepository<Pichet, Long> {

    @Query("SELECT COUNT(p) FROM Pichet p")
    long countPichets();

    @Query("SELECT SUM(p.prix) FROM Pichet p")
    Double sumAmountTotal();

    
    @Query("SELECT DISTINCT DAYNAME(p.date) " +
           "FROM Pichet p " +
           "GROUP BY DAYNAME(p.date) " +
           "ORDER BY COUNT(p) DESC")
    List<String> findPichetCountByDayOfWeekOrdered();
    

}