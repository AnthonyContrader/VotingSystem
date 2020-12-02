package com.contrader.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Voto.
 */
@Entity
@Table(name = "voto")
public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "utente", nullable = false)
    private Long utente;

    @NotNull
    @Column(name = "scheda", nullable = false)
    private Long scheda;

    @NotNull
    @Column(name = "voto", nullable = false)
    private Long voto;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtente() {
        return utente;
    }

    public Voto utente(Long utente) {
        this.utente = utente;
        return this;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

    public Long getScheda() {
        return scheda;
    }

    public Voto scheda(Long scheda) {
        this.scheda = scheda;
        return this;
    }

    public void setScheda(Long scheda) {
        this.scheda = scheda;
    }

    public Long getVoto() {
        return voto;
    }

    public Voto voto(Long voto) {
        this.voto = voto;
        return this;
    }

    public void setVoto(Long voto) {
        this.voto = voto;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Voto)) {
            return false;
        }
        return id != null && id.equals(((Voto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Voto{" +
            "id=" + getId() +
            ", utente=" + getUtente() +
            ", scheda=" + getScheda() +
            ", voto=" + getVoto() +
            "}";
    }
}
