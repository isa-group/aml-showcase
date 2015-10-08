/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.papamoscas.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author isa-tecnico
 */
@Entity
@XmlRootElement
public class Bird implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private UUID id;
    private String specie;
    private String place;
    private float legDiameter;
    private float wingSize;
    private int eggs;
    private int hatches;

    public Bird() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public float getLegDiameter() {
        return legDiameter;
    }

    public void setLegDiameter(float legDiameter) {
        this.legDiameter = legDiameter;
    }

    public float getWingSize() {
        return wingSize;
    }

    public void setWingSize(float wingSize) {
        this.wingSize = wingSize;
    }

    public int getEggs() {
        return eggs;
    }

    public void setEggs(int eggs) {
        this.eggs = eggs;
    }

    public int getHatches() {
        return hatches;
    }

    public void setHatches(int hatches) {
        this.hatches = hatches;
    }

    public Bird(UUID id, String specie, String place, float legDiameter, float wingSize, int eggs, int hatches) {
        this.id = id;
        this.specie = specie;
        this.place = place;
        this.legDiameter = legDiameter;
        this.wingSize = wingSize;
        this.eggs = eggs;
        this.hatches = hatches;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bird)) {
            return false;
        }
        Bird other = (Bird) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.us.isa.papamoscas.entities.NewEntity[ id=" + id + " ]";
    }

}
