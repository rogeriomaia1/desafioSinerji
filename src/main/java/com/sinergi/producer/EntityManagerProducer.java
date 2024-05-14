package com.sinergi.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("cadastrocliente");

	@Produces
	@ApplicationScoped
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
