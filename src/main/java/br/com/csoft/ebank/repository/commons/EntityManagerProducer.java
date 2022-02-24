package br.com.csoft.ebank.repository.commons;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 
    @PersistenceUnit(unitName = "appDS")
    private EntityManagerFactory appFactory;
  
    @RequestScoped
    @Produces
    @Default
    public EntityManager createAppEntityManager() {
        return appFactory.createEntityManager();
    }
  
    public void closeEntityManager(@Disposes EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }
 
}
