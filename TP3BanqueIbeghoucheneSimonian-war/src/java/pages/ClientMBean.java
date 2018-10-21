/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import entities.Client;
import entities.CompteBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.ClientFacade;

@Named(value = "clientMBean")
@ViewScoped
public class ClientMBean implements Serializable {

    @EJB
    private ClientFacade clientFacade;

    private static final long serialVersionUID = 1L;
    private List<Client> allClient = new ArrayList<>();
    private String message;
    
    public ClientMBean() {
    }
    
    public List<Client> getAllClient() {
        System.out.println("getAllClient Called!");
        if (this.allClient.isEmpty()) {
            System.out.println("getAllClient CACHED!");
            List<Client> cb = clientFacade.getAllClient();
            allClient = cb;
        }
        return allClient;
    }

    public void resetCache() {
        this.allClient = new ArrayList<>();
    }
    
    public void delete(long id) {
        System.out.println("deleting...");
//        if(clientFacade.delete(id)){
//            handleMessage(true,"le compte bancaire " + id + " est supprime");
//        }else{
//            handleMessage(true,"le compte bancaire " + id + "le compte bancaire " + id + " n'existe pas");
//        }
    }
    
    private void handleMessage(boolean isSuccess, String leMessage){
          resetCache();
          FacesContext context = FacesContext.getCurrentInstance();
          message = leMessage;
          if(isSuccess){
            context.addMessage(null, new FacesMessage("Successful", "Your message: " + message));
            return;
          }
          context.addMessage(null, new FacesMessage("Unsuccessful", "Your message: " + message));
      }
    
}
