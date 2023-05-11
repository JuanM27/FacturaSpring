package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
public class Demand {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idDemand;

  @Temporal(TemporalType.DATE)
  private Date date;

  private boolean invoiced=false;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "user_id")
  private User user;

  public Long getId() {
    return idDemand;
  }

  public void setId(Long id) {
    this.idDemand = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isInvoiced() {
    return invoiced;
  }

  public void setInvoiced(boolean invoiced) {
    this.invoiced = invoiced;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
