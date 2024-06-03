package com.example.guinguette.repositories;

import com.example.guinguette.entities.Personne;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    @Query("SELECT p FROM Personne p ORDER BY p.nom ASC")
    List<Personne> orderedByNom();

    @Query("SELECT COUNT(p) FROM Personne p")
    long countPersonnes();

    // Méthode pour calculer le nombre de pichets pour une personne donnée
    @Query("SELECT COUNT(p) FROM Pichet p WHERE p.personne.id = :personneId")
    long countPichetsByPersonneId(@Param("personneId") Long personneId);

    @Query("SELECT SUM(p.prix) FROM Pichet p WHERE p.personne.id = :personneId")
    long sumAmountByPersonneId(@Param("personneId") Long personneId);

    @Query("SELECT p.personne, COUNT(p) as pichetCount " +
           "FROM Pichet p " +
           "GROUP BY p.personne " +
           "ORDER BY pichetCount DESC " +
           "LIMIT 5")
    List<Object[]> findTop5PersonnesWithMostPichets();

    @Query("SELECT p.personne, COUNT(p) as pichetCount " +
           "FROM Pichet p " +
           "WHERE DATE(p.date) = CURRENT_DATE " +
           "GROUP BY p.personne " +
           "ORDER BY pichetCount DESC " +
           "LIMIT 5")
    List<Object[]> findTop5PersonnesWithMostPichetsToday();

    @Query("SELECT p.personne, COUNT(p) as pichetCount " +
           "FROM Pichet p " +
           "WHERE YEAR(p.date) = YEAR(CURRENT_DATE) AND WEEK(p.date) = WEEK(CURRENT_DATE) " +
           "GROUP BY p.personne " +
           "ORDER BY pichetCount DESC " +
           "LIMIT 5")
    List<Object[]> findTop5PersonnesWithMostPichetsThisWeek();

    @Query("SELECT p.personne, COUNT(p) as pichetCount " +
           "FROM Pichet p " +
           "WHERE YEAR(p.date) = YEAR(CURRENT_DATE) AND MONTH(p.date) = MONTH(CURRENT_DATE) " +
           "GROUP BY p.personne " +
           "ORDER BY pichetCount DESC " +
           "LIMIT 5")
    List<Object[]> findTop5PersonnesWithMostPichetsThisMonth();

    @Query("SELECT p.personne, COUNT(p) as pichetCount " +
           "FROM Pichet p " +
           "WHERE YEAR(p.date) = YEAR(CURRENT_DATE) " +
           "GROUP BY p.personne " +
           "ORDER BY pichetCount DESC " +
           "LIMIT 5")
    List<Object[]> findTop5PersonnesWithMostPichetsThisYear();

}