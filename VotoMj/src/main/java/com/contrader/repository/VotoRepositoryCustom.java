package com.contrader.repository;

import com.contrader.domain.Voto;

public interface VotoRepositoryCustom {
	
	public double countVoto(Long id_scheda, Long voto);
	
	public Voto findByIdutenteAndIdscheda(Long id_utente, Long id_scheda);
	
}
