package com.sinergi.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory = null;
	
	static {
		if (factory == null ) {
			factory = Persistence.createEntityManagerFactory("cadastrocliente");
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
