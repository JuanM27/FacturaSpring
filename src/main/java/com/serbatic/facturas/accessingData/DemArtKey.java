package com.serbatic.facturas.accessingData;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable

public class DemArtKey implements Serializable {
    @Column(name="idDemand")
    long idDemand;

    @Column(name="idArt")
    long idArt;
}
