package com.contrader.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.contrader.domain.Notifica} entity.
 */
public class NotificaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String messaggio;

    private LocalDate data;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificaDTO)) {
            return false;
        }

        return id != null && id.equals(((NotificaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotificaDTO{" +
            "id=" + getId() +
            ", messaggio='" + getMessaggio() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
