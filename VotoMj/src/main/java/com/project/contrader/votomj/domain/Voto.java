package com.project.contrader.votomj.domain;


import javax.persistence.*;

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

    @Column(name = "id_utente")
    private Long idUtente;

    @Column(name = "id_scheda")
    private Long idScheda;

    @Column(name = "voto")
    private Long voto;

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

    public Voto idUtente(Long idUtente) {
        this.idUtente = idUtente;
        return this;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public Long getIdScheda() {
        return idScheda;
    }

    public Voto idScheda(Long idScheda) {
        this.idScheda = idScheda;
        return this;
    }

    public void setIdScheda(Long idScheda) {
        this.idScheda = idScheda;
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
            ", idUtente=" + getIdUtente() +
            ", idScheda=" + getIdScheda() +
            ", voto=" + getVoto() +
            "}";
    }
}
