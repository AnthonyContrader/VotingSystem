package com.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Domande.
 */
@Entity
@Table(name = "domande")
public class Domande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "testo")
    private String testo;

    @Column(name = "risposta")
    private String risposta;

    @OneToMany(mappedBy = "domande")
    private Set<RisposteQuestionario> risposteQuestionarios = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "domandes", allowSetters = true)
    private Questionario questionario;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public Domande testo(String testo) {
        this.testo = testo;
        return this;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getRisposta() {
        return risposta;
    }

    public Domande risposta(String risposta) {
        this.risposta = risposta;
        return this;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Set<RisposteQuestionario> getRisposteQuestionarios() {
        return risposteQuestionarios;
    }

    public Domande risposteQuestionarios(Set<RisposteQuestionario> risposteQuestionarios) {
        this.risposteQuestionarios = risposteQuestionarios;
        return this;
    }

    public Domande addRisposteQuestionario(RisposteQuestionario risposteQuestionario) {
        this.risposteQuestionarios.add(risposteQuestionario);
        risposteQuestionario.setDomande(this);
        return this;
    }

    public Domande removeRisposteQuestionario(RisposteQuestionario risposteQuestionario) {
        this.risposteQuestionarios.remove(risposteQuestionario);
        risposteQuestionario.setDomande(null);
        return this;
    }

    public void setRisposteQuestionarios(Set<RisposteQuestionario> risposteQuestionarios) {
        this.risposteQuestionarios = risposteQuestionarios;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public Domande questionario(Questionario questionario) {
        this.questionario = questionario;
        return this;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Domande)) {
            return false;
        }
        return id != null && id.equals(((Domande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Domande{" +
            "id=" + getId() +
            ", testo='" + getTesto() + "'" +
            ", risposta='" + getRisposta() + "'" +
            "}";
    }
}
