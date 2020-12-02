package com.contrader.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.contrader.domain.Questionario} entity.
 */
public class QuestionarioDTO implements Serializable {
    
    private Long id;

    private Integer indexGrade;

    private String commenti;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndexGrade() {
        return indexGrade;
    }

    public void setIndexGrade(Integer indexGrade) {
        this.indexGrade = indexGrade;
    }

    public String getCommenti() {
        return commenti;
    }

    public void setCommenti(String commenti) {
        this.commenti = commenti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionarioDTO)) {
            return false;
        }

        return id != null && id.equals(((QuestionarioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionarioDTO{" +
            "id=" + getId() +
            ", indexGrade=" + getIndexGrade() +
            ", commenti='" + getCommenti() + "'" +
            "}";
    }
}
