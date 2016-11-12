package br.edu.unicatolica.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("economicPU");
	
	public static EntityManager createEntityManager() {
		return emf.createEntityManager();
	}
}
