package com.sinergi.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

public class GenericDao<T> {

	@Inject
	private EntityManager entityManager;

	
	public void salvar(T entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
       
        try {
            transaction.begin();
            entityManager.persist(entidade);
            transaction.commit();
            
	        entityManager.clear();  // Limpa o contexto de persistência
            
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

	@Transactional
	public T atualizar(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();

			T entity = entityManager.merge(entidade);

			transaction.commit();
			entityManager.clear(); // Limpa o contexto de persistência

			return entity;

		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Transactional
	public void remover(T entidade) {
		
		
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();

			entityManager.remove(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));

			transaction.commit();
			entityManager.clear(); // Limpa o contexto de persistência

		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
