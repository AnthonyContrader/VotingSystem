package it.contrader.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteVotanteDTO {
	
	private int id;
	
	private int id_utente;
	
	private int id_scheda;
	
	private int voto;
}
