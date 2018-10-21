/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import entities.OperationBancaire;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import session.CompteBancaireFacade;

/**
 *
 * @author mbdse
 */
@Named(value = "operationCompteMBean")
@ViewScoped
public class OperationCompteMBean implements Serializable{
    
    @EJB
    private CompteBancaireFacade gc;
    private int id;
    public List<OperationBancaire> getOperation(){
        return gc.getOperations(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public OperationCompteMBean() {
    }
    
}
