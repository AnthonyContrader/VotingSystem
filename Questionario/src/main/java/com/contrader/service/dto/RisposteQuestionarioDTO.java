package com.contrader.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.contrader.domain.RisposteQuestionario} entity.
 */
public class RisposteQuestionarioDTO implements Serializable {
    
    private Long id;

    private Long idUtente;

    private String testo;


    private Long domandeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Long getDomandeId() {
        return domandeId;
    }

    public void setDomandeId(Long domandeId) {
        this.domandeId = domandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RisposteQuestionarioDTO)) {
            return false;
        }

        return id != null && id.equals(((RisposteQuestionarioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RisposteQuestionarioDTO{" +
            "id=" + getId() +
            ", idUtente=" + getIdUtente() +
            ", testo='" + getTesto() + "'" +
            ", domandeId=" + getDomandeId() +
            "}";
    }
}
