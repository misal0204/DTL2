package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScFormas;
import itmm.entities.ScMenus;
import itmm.entities.ScModulos;
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

/**
 *
 * @author Misael
 */
@ManagedBean(name = "menuB")
@ViewScoped
public class MenuBean implements Serializable{

    DBConnection DB = new DBConnection();
    private EntityManager em;
    private EntityManagerFactory emf;

    private String schema;
    private String usuario;
    private String pass;
    String NPU;

    private List<ScModulos> listModulos;
    private List<ScMenus> listMenus;

    private List<ScFormas> formProcess;
    private List<ScFormas> formClt;
    private List<ScFormas> formRList;
    private List<ScFormas> formEstad;
    private List<ScFormas> formConfig;

    @ManagedProperty("#{attBean}")
    private AuthenticationBean attb;

    @PostConstruct
    public void init() {
        schema = attb.getSchema();
        usuario = attb.getUsername();
        pass = attb.getPassword();

        if (schema.equals("PRODUCTIVO")) {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_PRODUCTIVO;
        } else {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_CALIDAD;
        }
        String menu;
        Map property = DBConnection.Property(usuario, pass);

        try {

            emf = Persistence.createEntityManagerFactory(NPU, property);
            em = emf.createEntityManager();

            TypedQuery<ScModulos> modulos
                    = em.createNamedQuery("ScModulos.findAll", ScModulos.class);

            listModulos = modulos.getResultList();
            //List_analisis = list_modulos;
            for (ScModulos mod : listModulos) {
                //System.out.println("modulo: " + mod.getNombre());
                //Listado de menus
                TypedQuery<ScMenus> menus
                        = em.createNamedQuery("ScMenus.findByModulo",
                                ScMenus.class).setParameter("moduloId", mod);
                listMenus = menus.getResultList();
                for (ScMenus men : listMenus) {
                    menu = men.getNombre();
                    //System.out.println("menu: " + menu);
                    //Seleccion el IdMenu(int) para llenar los listado correspondientes
                    switch (men.getMenuId().intValue()) {
                        case 1:
                            formProcess = asignarIconos(men);
                            break;
                        case 2:
                            formClt = asignarIconos(men);
                            break;
                        case 3:
                            formRList = asignarIconos(men);
                            break;
                        case 4:
                            formEstad = asignarIconos(men);
                            break;
                        case 5:
                            formConfig = asignarIconos(men);
                            break;
                    }
                }
            }
            em.close();
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error en logueo usuario: " + pe.getMessage());
            System.err.println("Error DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
    }

    public MenuBean() {
    }

    private List<ScFormas> asignarIconos(ScMenus men) {
        List<ScFormas> listFormas;
        TypedQuery<ScFormas> formas
                = em.createNamedQuery("ScFormas.findByMenu",
                        ScFormas.class).setParameter("menuId", men);
        listFormas = formas.getResultList();
        /*for (ScFormas vista : listFormas) {
         System.out.println("vista: " + vista.getNombre());
         }*/
        return listFormas;
    }

    public List<ScFormas> getFormProcess() {
        return formProcess;
    }

    public List<ScFormas> getFormClt() {
        return formClt;
    }

    public List<ScFormas> getFormRList() {
        return formRList;
    }

    public List<ScFormas> getFormEstad() {
        return formEstad;
    }

    public List<ScFormas> getFormConfig() {
        return formConfig;
    }

    public List<ScModulos> getListModulos() {
        return listModulos;
    }

    public List<ScMenus> getListMenus() {
        return listMenus;
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

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
}
