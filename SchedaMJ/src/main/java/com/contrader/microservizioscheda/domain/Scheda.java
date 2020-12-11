package com.contrader.microservizioscheda.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Scheda.
 */
@Entity
@Table(name = "scheda")
public class Scheda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 250)
    @Column(name = "titolo", length = 250, nullable = false)
    private String titolo;

    @NotNull
    @Size(min = 5, max = 1000)
    @Column(name = "domanda", length = 1000, nullable = false)
    private String domanda;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "primarisposta", length = 100, nullable = false)
    private String primarisposta;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "secondarisposta", length = 100, nullable = false)
    private String secondarisposta;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "terzarisposta", length = 100, nullable = false)
    private String terzarisposta;

    @Column(name = "datacreazione")
    private LocalDate datacreazione;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public Scheda titolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDomanda() {
        return domanda;
    }

    public Scheda domanda(String domanda) {
        this.domanda = domanda;
        return this;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getPrimarisposta() {
        return primarisposta;
    }

    public Scheda primarisposta(String primarisposta) {
        this.primarisposta = primarisposta;
        return this;
    }

    public void setPrimarisposta(String primarisposta) {
        this.primarisposta = primarisposta;
    }

    public String getSecondarisposta() {
        return secondarisposta;
    }

    public Scheda secondarisposta(String secondarisposta) {
        this.secondarisposta = secondarisposta;
        return this;
    }

    public void setSecondarisposta(String secondarisposta) {
        this.secondarisposta = secondarisposta;
    }

    public String getTerzarisposta() {
        return terzarisposta;
    }

    public Scheda terzarisposta(String terzarisposta) {
        this.terzarisposta = terzarisposta;
        return this;
    }

    public void setTerzarisposta(String terzarisposta) {
        this.terzarisposta = terzarisposta;
    }

    public LocalDate getDatacreazione() {
        return datacreazione;
    }

    public Scheda datacreazione(LocalDate datacreazione) {
        this.datacreazione = datacreazione;
        return this;
    }

    public void setDatacreazione(LocalDate datacreazione) {
        this.datacreazione = datacreazione;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Scheda)) {
            return false;
        }
        return id != null && id.equals(((Scheda) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Scheda{" +
            "id=" + getId() +
            ", titolo='" + getTitolo() + "'" +
            ", domanda='" + getDomanda() + "'" +
            ", primarisposta='" + getPrimarisposta() + "'" +
            ", secondarisposta='" + getSecondarisposta() + "'" +
            ", terzarisposta='" + getTerzarisposta() + "'" +
            ", datacreazione='" + getDatacreazione() + "'" +
            "}";
    }
}
