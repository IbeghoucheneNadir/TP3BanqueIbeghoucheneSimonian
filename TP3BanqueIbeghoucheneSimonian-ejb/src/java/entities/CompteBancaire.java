/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package entities;



import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.NamedQueries;

import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;




/**

 *

 * @author katzenmaul

 */

@Entity

@Table(name = "COMPTESBANQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findById", query = "SELECT c FROM CompteBancaire c WHERE c.id = :id"),
    @NamedQuery(name = "CompteBancaire.findByNom", query = "SELECT c FROM CompteBancaire c WHERE c.nom = :nom"),
    @NamedQuery(name = "CompteBancaire.findBySolde", query = "SELECT c FROM CompteBancaire c WHERE c.solde = :solde")})
public class CompteBancaire implements Serializable {



    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOM")
    private String nom;

    @Column(name = "SOLDE")
    private int solde;

    

    public CompteBancaire(){

    }

    

    public CompteBancaire(Long cbid) {

        this.id = cbid;

    }

    

    public CompteBancaire(String nom, int solde) {

        this.nom = nom;

        this.solde = solde;

    }



    public CompteBancaire(String nom, int solde, Long id) {

        this(nom,solde);

        this.id = id;

    }



    public String getNom() {

        return nom;

    }



    public void setNom(String nom) {

        this.nom = nom;

    }



    public int getSolde() {

        return solde;

    }



    public void setSolde(int solde) {

        this.solde = solde;

    }



    public Long getId() {

        return id;

    }



    public void setId(Long id) {

        this.id = id;

    }

    

  public void deposer(int montant) {  

    solde += montant;  

  }  

    

  public int retirer(int montant) {  

    if (montant < solde) {  

      solde -= montant;  

      return montant;  

    } else {  

      return 0;  

    }  

  }  



    @Override

    public int hashCode() {

        int hash = 0;

        hash += (id != null ? id.hashCode() : 0);

        return hash;

    }



    @Override

    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the id fields are not set

        if (!(object instanceof CompteBancaire)) {

            return false;

        }

        CompteBancaire other = (CompteBancaire) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));

    }



    @Override

    public String toString() {

        return "entities.Compte[ id=" + id + " ]";

    }

    

}