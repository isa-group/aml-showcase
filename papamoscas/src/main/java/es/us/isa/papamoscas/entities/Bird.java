/*******************************************************************************
 * AML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AML. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) ISA Research Group - University of Sevilla, 2015
 * Licensed under GPL (https://github.com/isa-group/aml/blob/master/LICENSE.txt)
 *******************************************************************************/
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
