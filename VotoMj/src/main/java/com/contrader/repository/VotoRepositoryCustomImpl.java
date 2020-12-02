package com.contrader.repository;



import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.contrader.domain.Voto;

@Service
public class VotoRepositoryCustomImpl implements VotoRepositoryCustom {
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public double countVoto(Long id_scheda, Long voto) {
		//creteria query per contare il numero di un dato voto per una data
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cqv = cb.createQuery(Long.class);
		
		Root<Voto> votoEntity = cqv.from(Voto.class);
		
		cqv.select(cb.count(votoEntity));
		Predicate votoVoto = cb.equal(votoEntity.get("voto"), voto);
		Predicate schedaVoto = cb.equal(votoEntity.get("scheda"), id_scheda);
		cqv.where(schedaVoto, votoVoto);
		return em.createQuery(cqv).getSingleResult();
	}

	@Override
	public Voto findByIdutenteAndIdscheda(Long id_utente, Long id_scheda) {
		//criteria query per controllare se un utente ha già votato per una data scheda
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Voto> cqv = cb.createQuery(Voto.class);
		
		Root<Voto> voto = cqv.from(Voto.class);
		Predicate utenteVoto = cb.equal(voto.get("utente"), id_utente);
		Predicate schedaVoto = cb.equal(voto.get("scheda"), id_scheda);
		cqv.where(utenteVoto, schedaVoto);
		try {
		if(em.createQuery(cqv).getSingleResult() != null)
			return em.createQuery(cqv).getSingleResult();
		} catch(NoResultException nre) {
			
		}
		return null;
	}
	
}
