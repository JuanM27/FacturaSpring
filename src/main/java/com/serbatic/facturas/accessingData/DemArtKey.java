package com.serbatic.facturas.accessingData;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DemArtKey implements Serializable {

    @Column(name="id_demand")
    long idDemand;

    @Column(name="id_art")
    long idArt;

    public DemArtKey(Long idDemand, Long idArt){
        this.idDemand=idDemand;
        this.idArt=idArt;
    }

    public DemArtKey() {

    }


    public long getIdDemand() {
        return idDemand;
    }

    public void setIdDemand(long idDemand) {
        this.idDemand = idDemand;
    }

    public long getIdArt() {
        return idArt;
    }

    public void setIdArt(long idArt) {
        this.idArt = idArt;
    }
}
