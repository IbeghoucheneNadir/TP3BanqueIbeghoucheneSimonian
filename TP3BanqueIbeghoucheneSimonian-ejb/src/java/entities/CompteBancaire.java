package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "SOLDE")
    private double solde;

    @Column(name = "DATECREATION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperationBancaire> listOperationBancaire;

    public List<OperationBancaire> getListOperationBancaire() {
        return listOperationBancaire;
    }

    public CompteBancaire() {
        this.listOperationBancaire = new ArrayList<>();
    }

    public CompteBancaire(Long cbid) {
        this.listOperationBancaire = new ArrayList<>();
        this.id = cbid;
    }

    public CompteBancaire(String nom, int solde) {
        this.listOperationBancaire = new ArrayList<>();
        this.nom = nom;
        this.solde = solde;
        this.dateCreation = new Date();
        addOperation(new OperationBancaire("création de compte"));
    }

    public CompteBancaire(String nom, int solde, Long id) {
        this(nom, solde);
        this.listOperationBancaire = new ArrayList<>();
        this.id = id;
    }
    public void addOperation(OperationBancaire op){
        listOperationBancaire.add(op);
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSolde() {
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

    public void deposer(double montant) {
        solde += montant;
        addOperation(new OperationBancaire("Dépot de "+montant));
    }

    public double retirer(double montant) {
        if (montant <= solde) {
            addOperation(new OperationBancaire("Retrait de "+montant));
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
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Compte[ id=" + id + " nom=" + nom + " solde=" + solde + " ]";
    }
}
