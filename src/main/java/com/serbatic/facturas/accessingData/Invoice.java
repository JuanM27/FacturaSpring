package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInvoice;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="idDemand")
    private Demand demand;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }
}
