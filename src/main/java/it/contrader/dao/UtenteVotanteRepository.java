package it.contrader.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.contrader.model.UtenteVotante;

@Repository
@Transactional
public interface UtenteVotanteRepository extends CrudRepository<UtenteVotante, Integer> {
	
	@Query(value = "SELECT COUNT(*) AS total FROM utente_votante u WHERE u.id_scheda = :id_scheda AND u.voto = :voto", nativeQuery = true)
    public double countVoto(@Param("id_scheda") int id_scheda, @Param("voto") int voto);
			  
	
	@Query(value = "SELECT * FROM utente_votante u WHERE u.id_utente = :id_utente AND u.id_scheda = :id_scheda", nativeQuery = true)
    public UtenteVotante findByIdutenteAndIdscheda(@Param("id_utente") int id_utente, @Param("id_scheda") int id_scheda);
}