/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */

//TODO Crea una entidad mapeada en la tabla "request_visits"
//TODO
@Entity
@Table(name = "request_visits")
public class RequestVisit extends BaseEntity {

	
	//El id esta al heredar de BaseEntity
	
    /**
     * Holds value of property date.
     */
    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    /**
     * Holds value of property pet.
     */
    // TODO Relación Muchos a uno con la columna "pet_id"
    // TODO 
    /*Ponemos Fetch a Eager por que al ser una intermedia de una N:M:K camuflada, nos convendrá traernos todo. 
    * y el join Column para traernos la FK y asegurarnos integridad referencial de sus registros.
    * NOTA: En la hoja pone claramente una linea de código, pero por ejemplo el Entity + Table del principio es imposible en solo una linea.
    * He supuesto que no era literal. */
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn (name="pet_id")
	private Pet pet;
    
    /**
     * Holds value of property pet.
     */
	// TODO Relación Muchos a uno con la columna "vet_id"
    // TODO
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn (name="vet_id")
    private Vet vet;
    
    /**
     * Holds value of property pet.
     */
    // TODO Relación Muchos a uno con la columna "owner_id"
    // TODO
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn (name="owner_id")
    private Owner owner;
    
    //Deberiamos validar que state sea 0 o 1, pero no parece que podamos escribir aqui.
    @Column(name = "state")
    private Integer state;


    /**
     * Creates a new instance of Visit for the current date
     */
    public RequestVisit() {
        this.date = new Date();
    }


    /**
     * Getter for property date.
     *
     * @return Value of property date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Setter for property date.
     *
     * @param date New value of property date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter for property pet.
     *
     * @return Value of property pet.
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Setter for property pet.
     *
     * @param pet New value of property pet.
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }


	public Vet getVet() {
		return vet;
	}


	public void setVet(Vet vet) {
		this.vet = vet;
	}


	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "RequestVisit [date=" + date + ", pet=" + pet + ", vet=" + vet + ", owner=" + owner + ", state=" + state
				+ "]";
	}
    
}
