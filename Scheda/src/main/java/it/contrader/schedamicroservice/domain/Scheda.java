package it.contrader.schedamicroservice.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

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
    @Size(min = 5, max = 1000)
    @Column(name = "domanda", length = 1000, nullable = false)
    private String domanda;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "risposta_1", length = 100, nullable = false)
    private String risposta1;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "risposta_2", length = 100, nullable = false)
    private String risposta2;

    @Size(min = 2, max = 100)
    @Column(name = "risposta_3", length = 100)
    private String risposta3;

    @NotNull
    @Size(min = 5, max = 250)
    @Column(name = "titolo", length = 250, nullable = false)
    private String titolo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRisposta1() {
        return risposta1;
    }

    public Scheda risposta1(String risposta1) {
        this.risposta1 = risposta1;
        return this;
    }

    public void setRisposta1(String risposta1) {
        this.risposta1 = risposta1;
    }

    public String getRisposta2() {
        return risposta2;
    }

    public Scheda risposta2(String risposta2) {
        this.risposta2 = risposta2;
        return this;
    }

    public void setRisposta2(String risposta2) {
        this.risposta2 = risposta2;
    }

    public String getRisposta3() {
        return risposta3;
    }

    public Scheda risposta3(String risposta3) {
        this.risposta3 = risposta3;
        return this;
    }

    public void setRisposta3(String risposta3) {
        this.risposta3 = risposta3;
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
            ", domanda='" + getDomanda() + "'" +
            ", risposta1='" + getRisposta1() + "'" +
            ", risposta2='" + getRisposta2() + "'" +
            ", risposta3='" + getRisposta3() + "'" +
            ", titolo='" + getTitolo() + "'" +
            "}";
    }
}
