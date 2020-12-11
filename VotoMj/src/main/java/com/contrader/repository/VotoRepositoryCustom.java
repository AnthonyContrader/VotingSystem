package com.contrader.repository;

import java.util.List;

import com.contrader.domain.Voto;

public interface VotoRepositoryCustom {
	
	public double countVoto(Long id_scheda, Long voto);
	
	public double countUtentiVoti();
	
	public Voto findByIdutenteAndIdscheda(Long id_utente, Long id_scheda);
	
	public List<Voto> getAllIdSchedaByUser(Long id_utente);
	
}
