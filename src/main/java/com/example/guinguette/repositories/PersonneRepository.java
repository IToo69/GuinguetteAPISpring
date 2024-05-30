package com.example.guinguette.repositories;

import com.example.guinguette.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    

    @Query("SELECT COUNT(p) FROM Personne p")
    long countPersonnes();

    // Méthode pour calculer le nombre de pichets pour une personne donnée
    @Query("SELECT COUNT(p) FROM Pichet p WHERE p.personne.id = :personneId")
    long countPichetsByPersonneId(@Param("personneId") Long personneId);

    @Query("SELECT SUM(p.prix) FROM Pichet p WHERE p.personne.id = :personneId")
    long sumAmountByPersonneId(@Param("personneId") Long personneId);
}