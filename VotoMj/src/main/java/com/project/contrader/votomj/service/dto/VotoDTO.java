package com.project.contrader.votomj.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.project.contrader.votomj.domain.Voto} entity.
 */
public class VotoDTO implements Serializable {
    
    private Long id;

    private Long idUtente;

    private Long idScheda;

    private Long voto;

    
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

    public Long getIdScheda() {
        return idScheda;
    }

    public void setIdScheda(Long idScheda) {
        this.idScheda = idScheda;
    }

    public Long getVoto() {
        return voto;
    }

    public void setVoto(Long voto) {
        this.voto = voto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VotoDTO)) {
            return false;
        }

        return id != null && id.equals(((VotoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VotoDTO{" +
            "id=" + getId() +
            ", idUtente=" + getIdUtente() +
            ", idScheda=" + getIdScheda() +
            ", voto=" + getVoto() +
            "}";
    }
}
