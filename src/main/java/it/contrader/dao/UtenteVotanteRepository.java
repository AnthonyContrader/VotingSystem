package it.contrader.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.contrader.model.UtenteVotante;

@Repository
@Transactional
public interface UtenteVotanteRepository extends CrudRepository<UtenteVotante, Long> {
	
	@Query("SELECT COUNT(*) AS total FROM utentevotante u WHERE u.id_scheda = :id_scheda AND u.voto = :voto")
    public double countVoto(@Param("id_scheda") long id_scheda, @Param("voto") int voto);
	
	
	@Query("SELECT * FROM utentevotante u WHERE u.id_utente = :id_utente AND u.id_scheda = :id_scheda")
    public UtenteVotante findByIdutenteAndIdscheda(@Param("id_utente") long id_utente, @Param("id_scheda") long id_scheda);
}
