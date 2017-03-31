package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.RequestVisit;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.RequestVisitService;
import org.springframework.samples.petclinic.web.PetResource.PetRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
public class RequestVisitResource extends AbstractResourceController{
	   private final RequestVisitService requestVisitService;
	   private final ClinicService clinicService;


	    @Autowired
	    public RequestVisitResource(RequestVisitService requestVisitService, ClinicService clinicService) {
	        this.requestVisitService = requestVisitService;
	        this.clinicService = clinicService;
	    }

	    @InitBinder
	    public void setAllowedFields(WebDataBinder dataBinder) {
	        dataBinder.setDisallowedFields("id");
	    }
	    
	    private RequestVisit retrieveRequestVisit(int rvId) {
	        return this.requestVisitService.findOne(rvId);
	    }

	   
	    /**
	     * Read List of Visits
	     */
	    @GetMapping("/rv/list/all")
	    public Collection<RequestVisit> findAll() {
	        Collection<RequestVisit> debug = requestVisitService.findAllVisits();
	        return debug;
	    }
	    
	    @GetMapping("/rv/list/unfinished/{ownerId}")
	    public Collection<RequestVisit> findPending(@PathVariable("ownerId") Integer ownerId) {
	        return requestVisitService.findPendingVisitsByOwner(ownerId);
	    }
	    
	    @GetMapping("/rv/list/{ownerId}")
	    public Collection<RequestVisit> findByOwner( @PathVariable("ownerId") Integer ownerId) {
	        return requestVisitService.findRequestVisitByOwner(ownerId);
	    }
	    
	    /**
	     * Save Owner
	     */
	    @CrossOrigin
	    @RequestMapping(value = "/rv/save", method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void saveRequestVisit(@RequestBody RequestVisit requestVisit) {
	    	  
	    	RequestVisit rvSave = new RequestVisit();
	    	Owner o = clinicService.findOwnerById(requestVisit.getOwner().getId());
	    	Pet p = clinicService.findPetById(requestVisit.getPet().getId());
	    	Vet v = clinicService.findVetById(requestVisit.getVet().getId());
	    	
	    	rvSave.setDate(requestVisit.getDate());
	    	rvSave.setOwner(o);
	    	rvSave.setPet(p);
	    	rvSave.setVet(v);
	    	rvSave.setState(0);
	    	
	    	requestVisitService.save(rvSave);
	    }
	    
}

