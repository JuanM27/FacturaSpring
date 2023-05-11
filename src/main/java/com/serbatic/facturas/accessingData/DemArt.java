package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;

@Entity
public class DemArt {
    @EmbeddedId
    DemArtKey id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idDemand")
    @JoinColumn(name="id_demand")
    Demand demand;


    @ManyToOne(cascade = CascadeType.REFRESH) // Sirve por si en algun momento queremos añadir la funcion de cambiar el ID del pedido (por ejemplo para reasignar un
    //                                          pedido a otro usuario).
    @MapsId("idArt") //Sirve para indicar que forma parte de una PK compuesta
    @JoinColumn(name="id_art")
    Article article;
    private int amount;

    public DemArt(Article art, Demand dem){
        this.article=art;
        this.demand=dem;
    }

    public DemArt() {

    }


    public Article getArticle() {
        return this.article;
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
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DemArtKey getId() {
        return id;
    }

    public void setId(DemArtKey id) {
        this.id = id;
    }
}
