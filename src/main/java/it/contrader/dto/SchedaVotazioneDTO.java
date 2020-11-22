package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedaVotazioneDTO {
	
	private int id;
	
	private String Titolo;
	
	private String Domanda;
	
	private String Risposta1;
	
	private String Risposta2;
	
	private String Risposta3;
}
