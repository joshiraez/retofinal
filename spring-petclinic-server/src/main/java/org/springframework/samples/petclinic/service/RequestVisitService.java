package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.RequestVisit;
import org.springframework.samples.petclinic.repository.RequestVisitRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestVisitService {

	@Autowired
	private RequestVisitRepository requestVisitRepository;
	
	/*ERA UNA TRAMPAAAA D:
	//GETTERS Y SETTERS - DEBEN SER PUBLICOS PARA SPRING 3 Y ANTERIOR, PERO ***NO*** LO USAMOS FUERA DEL CONTROLADOR, a todos los efectos es **private**
	public OwnerRepository getOwnerRepository(){
		return ownerRepository;
	}
	
	public void setOwnerRepository(OwnerRepository ownerRepository){
		this.ownerRepository=ownerRepository;
	}*/
		
		//IMPLEMENTAMOS LOS METODOS DEL REPOSITORIO (y la logica si hubiera)
		public RequestVisit findOne(int RequestVisitId){
			return requestVisitRepository.findOne(RequestVisitId);
		}
	
		public void save(RequestVisit rv){
			requestVisitRepository.save(rv);
		}
		
		public List<RequestVisit> findPendingVisitsByOwner(Integer ownerId){
			return requestVisitRepository.findPendingVisitsByOwner(ownerId);
		}
		
		public List<RequestVisit> findAllVisits(){
			return requestVisitRepository.findAll();
		}
		
		 public List<RequestVisit> findRequestVisitByOwner(Integer ownerId){
			 return requestVisitRepository.findRequestVisitByOwner(ownerId);
		 }
	
}
