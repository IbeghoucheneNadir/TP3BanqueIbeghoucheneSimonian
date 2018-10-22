/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import entities.OperationBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
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
    private List<OperationBancaire> allOperation = new ArrayList<>();

    public List<OperationBancaire> getOperation(int a){
     if (this.allOperation.isEmpty()) {
            System.out.println("getAllCompteBancaire CACHED!");
            List<OperationBancaire> cb = gc.getOperations(a);
            allOperation = cb;
        }
        return allOperation;
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
