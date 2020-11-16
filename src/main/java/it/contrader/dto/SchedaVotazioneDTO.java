package it.contrader.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class SchedaVotazioneDTO {
	
	private int id;
	
	private String Titolo;
	
	private String Domanda;
	
	private String Risposta1;
	
	private String Risposta2;
	
	private String Risposta3;
}
