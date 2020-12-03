package com.contrader.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Notifica.
 */
@Entity
@Table(name = "notifica")
public class Notifica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "messaggio", nullable = false)
    private String messaggio;

    @Column(name = "data")
    private LocalDate data;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public Notifica messaggio(String messaggio) {
        this.messaggio = messaggio;
        return this;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public LocalDate getData() {
        return data;
    }

    public Notifica data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notifica)) {
            return false;
        }
        return id != null && id.equals(((Notifica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Notifica{" +
            "id=" + getId() +
            ", messaggio='" + getMessaggio() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
