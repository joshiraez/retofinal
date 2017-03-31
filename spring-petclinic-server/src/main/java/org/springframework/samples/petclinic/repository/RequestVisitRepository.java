package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.RequestVisit;

public interface RequestVisitRepository extends JpaRepository<RequestVisit, Integer> {

   /*Trae una coleccion de Visitas que no esten tratadas */
   @Query("select rv from RequestVisit rv where rv.owner.id=:ownerId and rv.state=0")
   public List<RequestVisit> findPendingVisitsByOwner(Integer ownerId);
   
   @Query("select rv from RequestVisit rv where rv.owner.id=:ownerId")
   public List<RequestVisit> findRequestVisitByOwner(Integer ownerId);
}

