/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.contatojpa.dao.connection;

import br.com.contatojpa.model.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joel Rodrigues
 */
public class JPAUtil {

    private static final String PERSISTENCE_UNIT = "contato_jpa";
    private static EntityManagerFactory factory = null;
    
    public static EntityManager getEntityManager(){
        if(factory == null || !factory.isOpen()){
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("####################################FECHANDO A FABRICA DE CONEXÕES!");
                    factory.close();
                }
            }));
        }
        return factory.createEntityManager();
    }
    
    public static void close(EntityManager entityManager){
        if(entityManager != null && entityManager.isOpen()){
            entityManager.clear();
            entityManager.close();
        }
    }

}
