package com.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.contrader.domain.Voto} entity.
 */
public class VotoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long utente;

    @NotNull
    private Long scheda;

    @NotNull
    private Long voto;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtente() {
        return utente;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

    public Long getScheda() {
        return scheda;
    }

    public void setScheda(Long scheda) {
        this.scheda = scheda;
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
            ", utente=" + getUtente() +
            ", scheda=" + getScheda() +
            ", voto=" + getVoto() +
            "}";
    }
}
