package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;

@Entity
public class DemArt {

    @EmbeddedId
    DemArtKey id;

    @ManyToOne
    @MapsId("idDemand")
    @JoinColumn(name="idDemand")
    Demand demand;

    @ManyToOne
    @MapsId("idArt") //Sirve para indicar que forma parte de una PK compuesta
    @JoinColumn(name="idArt")
    Article article;

    private int amount;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
