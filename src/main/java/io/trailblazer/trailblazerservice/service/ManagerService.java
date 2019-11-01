package io.trailblazer.trailblazerservice.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerService {


  private final EntityManagerFactory em;
  private final EntityManager manager;

  public ManagerService() {
    em = Persistence.createEntityManagerFactory("trail");
    manager = em.createEntityManager();
  }

  public static ManagerService getInstance() {
    return Holder.INSTANCE;
  }

  public EntityManager getManager() {
    return manager;
  }

  private static class Holder {

    private static final ManagerService INSTANCE = new ManagerService();

  }
}
