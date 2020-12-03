package com.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A RisposteQuestionario.
 */
@Entity
@Table(name = "risposte_questionario")
public class RisposteQuestionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_utente")
    private Long idUtente;

    @Column(name = "testo")
    private String testo;

    @ManyToOne
    @JsonIgnoreProperties(value = "risposteQuestionarios", allowSetters = true)
    private Domande domande;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUtente() {
        return idUtente;
    }

    public RisposteQuestionario idUtente(Long idUtente) {
        this.idUtente = idUtente;
        return this;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public String getTesto() {
        return testo;
    }

    public RisposteQuestionario testo(String testo) {
        this.testo = testo;
        return this;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Domande getDomande() {
        return domande;
    }

    public RisposteQuestionario domande(Domande domande) {
        this.domande = domande;
        return this;
    }

    public void setDomande(Domande domande) {
        this.domande = domande;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RisposteQuestionario)) {
            return false;
        }
        return id != null && id.equals(((RisposteQuestionario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RisposteQuestionario{" +
            "id=" + getId() +
            ", idUtente=" + getIdUtente() +
            ", testo='" + getTesto() + "'" +
            "}";
    }
}
