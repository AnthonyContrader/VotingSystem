package com.contrader.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.contrader.domain.Domande} entity.
 */
public class DomandeDTO implements Serializable {
    
    private Long id;

    private String testo;


    private Long questionarioId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Long getQuestionarioId() {
        return questionarioId;
    }

    public void setQuestionarioId(Long questionarioId) {
        this.questionarioId = questionarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomandeDTO)) {
            return false;
        }

        return id != null && id.equals(((DomandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DomandeDTO{" +
            "id=" + getId() +
            ", testo='" + getTesto() + "'" +
            ", questionarioId=" + getQuestionarioId() +
            "}";
    }
}
