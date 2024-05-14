package com.sinergi.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.sinergi.dto.PessoaDto;
import com.sinergi.model.Pessoa;

@Named
@ApplicationScoped
public class ClienteDao extends GenericDao<Pessoa> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Transactional
	public List<Pessoa> buscarPessoas(PessoaDto pessoaDto) {
	    EntityManager entityManager = super.getEntityManager();
	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
	    Root<Pessoa> root = criteria.from(Pessoa.class);
	    
	    root.fetch("enderecos", JoinType.LEFT); // Adicionando o join fetch aqui
	    
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    
	    if (pessoaDto.getNome() != null && !pessoaDto.getNome().isEmpty()) {
	        predicates.add(builder.like(builder.lower(root.get("nome")), "%" + pessoaDto.getNome().toLowerCase() + "%"));
	    }
	    
	    if (pessoaDto.getDataNascimento() != null ) {
	        LocalDate dataNascimento = pessoaDto.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate nextDay = dataNascimento.plusDays(1);
	        predicates.add(builder.greaterThanOrEqualTo(root.get("dataNascimento"), Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant())));
	        predicates.add(builder.lessThan(root.get("dataNascimento"), Date.from(nextDay.atStartOfDay(ZoneId.systemDefault()).toInstant())));
	    }
	    
	    if (pessoaDto.getSexo() != null && !pessoaDto.getSexo().isEmpty()) {
	        predicates.add(builder.equal(root.get("sexo"), pessoaDto.getSexo()));
	    }
	    
	    criteria.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
	    
	    return entityManager.createQuery(criteria).getResultList();
	}


	
	public Pessoa buscarPessoaPorId(Long idPessoa) {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
	
        Predicate predicate = null;
        
        if (idPessoa != null) {
			predicate = builder.equal(root.get("id"),  idPessoa );
		}
        
        criteria.select(root).where(predicate);
        
        try {
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Pessoa buscarPessoaPorNome(String nome) {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate predicate = null;
		
		if (nome != null) {
			predicate = builder.equal(root.get("nome"),  nome );
		}
		
		criteria.select(root).where(predicate);
		
		try {
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
