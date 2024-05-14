package com.sinergi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sinergi.dto.EnderecoDto;
import com.sinergi.model.Endereco;
import com.sinergi.util.JPAUtil;

@Named
@ApplicationScoped
public class EnderecoDao extends GenericDao<Endereco> {
	

    public List<Endereco> buscarEnderecos(EnderecoDto enderecoDto) {
    	
    	EntityManager entityManager = JPAUtil.getEntityManager();
    	
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Endereco> criteria = builder.createQuery(Endereco.class);
        Root<Endereco> root = criteria.from(Endereco.class);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        
        if (enderecoDto.getLogradouro() != null) {
            predicates.add(builder.equal(root.get("logradouro"), enderecoDto.getLogradouro()));
        }
        
        criteria.select(root).where(predicates.toArray(new Predicate[0]));
        
        return entityManager.createQuery(criteria).getResultList();
    }
}
