package com.contrader.microservizioscheda.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.contrader.microservizioscheda.domain.Scheda} entity.
 */
public class SchedaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 5, max = 250)
    private String titolo;

    @NotNull
    @Size(min = 5, max = 1000)
    private String domanda;

    @NotNull
    @Size(min = 2, max = 100)
    private String primarisposta;

    @NotNull
    @Size(min = 2, max = 100)
    private String secondarisposta;

    @NotNull
    @Size(min = 2, max = 100)
    private String terzarisposta;

    private LocalDate datacreazione;
    
    private boolean votato;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getPrimarisposta() {
        return primarisposta;
    }

    public void setPrimarisposta(String primarisposta) {
        this.primarisposta = primarisposta;
    }

    public String getSecondarisposta() {
        return secondarisposta;
    }

    public void setSecondarisposta(String secondarisposta) {
        this.secondarisposta = secondarisposta;
    }

    public String getTerzarisposta() {
        return terzarisposta;
    }

    public void setTerzarisposta(String terzarisposta) {
        this.terzarisposta = terzarisposta;
    }

    public LocalDate getDatacreazione() {
        return datacreazione;
    }

    public void setDatacreazione(LocalDate datacreazione) {
        this.datacreazione = datacreazione;
    }
    
    public boolean getVotato() {
    	return this.votato;
    }
    
    public void setVotato(boolean b) {
    	this.votato = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SchedaDTO)) {
            return false;
        }

        return id != null && id.equals(((SchedaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SchedaDTO{" +
            "id=" + getId() +
            ", titolo='" + getTitolo() + "'" +
            ", domanda='" + getDomanda() + "'" +
            ", primarisposta='" + getPrimarisposta() + "'" +
            ", secondarisposta='" + getSecondarisposta() + "'" +
            ", terzarisposta='" + getTerzarisposta() + "'" +
            ", datacreazione='" + getDatacreazione() + "'" +
            ", votato='" + getVotato() + "'" +
            "}";
    }
}
