package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScAnalisis;
import itmm.entities.ScTpanalisis;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

@ManagedBean(name = "analisisB")
@ViewScoped
public class AnalisisBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScAnalisis> analisis;
    private List<ScTpanalisis> tpanalisis;
    private ScAnalisis selectedAnalisis;
    
    private String schema;
    private String usuario;
    private String pass;

    @ManagedProperty("#{attBean}")
    private AuthenticationBean attb;

    @PostConstruct
    public void init() {
        schema = attb.getSchema();
        usuario= attb.getUsername();
        pass = attb.getPassword();
        
        String NPU;
        if(schema.equals("PRODUCTIVO"))
        {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_PRODUCTIVO;
        }else
        {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_CALIDAD;
        }
        
        Map property= DBConnection.Property(usuario, pass);
        
        try
        {
            emf = Persistence.createEntityManagerFactory(NPU, property);
            em = emf.createEntityManager();
            em.getTransaction().begin();
            
            TypedQuery<ScAnalisis> query
                    = em.createNamedQuery("ScAnalisis.findAll", ScAnalisis.class);
            List<ScAnalisis> results= query.getResultList();
            
            analisis = results;
            tpanalisis=getTpAnalisis(em);
            
             for(ScAnalisis a: results)
             {
                 System.out.println(a.getNombre());
             }
        }
        catch(PersistenceException pe)
        {
            System.out.println("Mensaje error analisis bean: "+pe.getMessage());
            System.out.println("Error DBConnection: "+pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public AnalisisBean() {
    }

    public List<ScTpanalisis> getTpanalisis() {
        return tpanalisis;
    }

    public List<ScTpanalisis> getTpAnalisis(EntityManager em)
    {
        TypedQuery<ScTpanalisis> query
                = em.createNamedQuery("ScTpanalisis.findAll",ScTpanalisis.class);
        List<ScTpanalisis> results= query.getResultList();
        
        return results;
    }
    
    public List<ScAnalisis> getAnalisis() {
        return analisis;
    }

    public ScAnalisis getSelectedAnalisis() {
        return selectedAnalisis;
    }

    public void setSelectedAnalisis(ScAnalisis selectedAnalisis) {
        this.selectedAnalisis = selectedAnalisis;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }

}
