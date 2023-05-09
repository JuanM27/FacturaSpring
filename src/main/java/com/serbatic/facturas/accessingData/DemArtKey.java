package com.serbatic.facturas.accessingData;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DemArtKey implements Serializable {

    public long getIdDemand() {
        return idDemand;
    }

    public void setIdDemand(long idDemand) {
        this.idDemand = idDemand;
    }

    @Column(name="id_demand")
    long idDemand;

    public long getIdArt() {
        return idArt;
    }

    public void setIdArt(long idArt) {
        this.idArt = idArt;
    }

    @Column(name="id_art")
    long idArt;
}
