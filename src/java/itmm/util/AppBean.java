package itmm.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ManagedBean(name = "appB")
@ApplicationScoped
public class AppBean {

   private EntityManager em;
    private EntityManagerFactory emf;
    
    public AppBean() {
    }
    
    public String getBaseUrl() {
        return MyUtil.baseUrl();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
