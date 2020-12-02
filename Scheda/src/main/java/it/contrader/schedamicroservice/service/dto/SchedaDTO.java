package it.contrader.schedamicroservice.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.contrader.schedamicroservice.domain.Scheda} entity.
 */
public class SchedaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 5, max = 1000)
    private String domanda;

    @NotNull
    @Size(min = 1, max = 100)
    private String risposta1;

    @NotNull
    @Size(min = 1, max = 100)
    private String risposta2;

    @Size(min = 1, max = 100)
    private String risposta3;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getRisposta1() {
        return risposta1;
    }

    public void setRisposta1(String risposta1) {
        this.risposta1 = risposta1;
    }

    public String getRisposta2() {
        return risposta2;
    }

    public void setRisposta2(String risposta2) {
        this.risposta2 = risposta2;
    }

    public String getRisposta3() {
        return risposta3;
    }

    public void setRisposta3(String risposta3) {
        this.risposta3 = risposta3;
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
            ", domanda='" + getDomanda() + "'" +
            ", risposta1='" + getRisposta1() + "'" +
            ", risposta2='" + getRisposta2() + "'" +
            ", risposta3='" + getRisposta3() + "'" +
            "}";
    }
}
