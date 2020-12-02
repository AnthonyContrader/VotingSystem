package com.contrader.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Questionario.
 */
@Entity
@Table(name = "questionario")
public class Questionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "index_grade")
    private Integer indexGrade;

    @Column(name = "commenti")
    private String commenti;

    @OneToMany(mappedBy = "questionario")
    private Set<Domande> domandes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndexGrade() {
        return indexGrade;
    }

    public Questionario indexGrade(Integer indexGrade) {
        this.indexGrade = indexGrade;
        return this;
    }

    public void setIndexGrade(Integer indexGrade) {
        this.indexGrade = indexGrade;
    }

    public String getCommenti() {
        return commenti;
    }

    public Questionario commenti(String commenti) {
        this.commenti = commenti;
        return this;
    }

    public void setCommenti(String commenti) {
        this.commenti = commenti;
    }

    public Set<Domande> getDomandes() {
        return domandes;
    }

    public Questionario domandes(Set<Domande> domandes) {
        this.domandes = domandes;
        return this;
    }

    public Questionario addDomande(Domande domande) {
        this.domandes.add(domande);
        domande.setQuestionario(this);
        return this;
    }

    public Questionario removeDomande(Domande domande) {
        this.domandes.remove(domande);
        domande.setQuestionario(null);
        return this;
    }

    public void setDomandes(Set<Domande> domandes) {
        this.domandes = domandes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Questionario)) {
            return false;
        }
        return id != null && id.equals(((Questionario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Questionario{" +
            "id=" + getId() +
            ", indexGrade=" + getIndexGrade() +
            ", commenti='" + getCommenti() + "'" +
            "}";
    }
}
