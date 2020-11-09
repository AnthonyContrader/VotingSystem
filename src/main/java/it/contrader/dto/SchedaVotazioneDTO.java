package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedaVotazioneDTO {
	
	private Long id;
	
	private String titolo;
	
	private String domanda;
	
	private String risposta1;
	
	private String risposta2;
	
	private String risposta3;
}
