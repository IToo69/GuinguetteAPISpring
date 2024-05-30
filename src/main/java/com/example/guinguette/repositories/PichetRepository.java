package com.example.guinguette.repositories;


import com.example.guinguette.dto.DayOfWeekCount;
import com.example.guinguette.entities.Pichet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PichetRepository extends JpaRepository<Pichet, Long> {

    @Query("SELECT COUNT(p) FROM Pichet p")
    long countPichets();

    @Query("SELECT SUM(p.prix) FROM Pichet p")
    Double sumAmountTotal();

    @Query("SELECT new com.example.DayOfWeekCount(FUNCTION('DAYNAME', p.date), COUNT(p)) " +
           "FROM Pichet p " +
           "GROUP BY FUNCTION('DAYNAME', p.date) " +
           "ORDER BY COUNT(p) ASC")
    List<DayOfWeekCount> findPichetCountByDayOfWeekOrdered();

}