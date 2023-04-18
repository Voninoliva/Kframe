   
package model;

import etu1790.framework.annotations.Urls;
import etu1790.framework.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Emp {
    private int id;
    private String nom;
    public Emp(){}
    public Emp(int i,String n){
        this.id=i;
        this.nom=n;
    }
    @Urls(url = "/Emp-All")
    public ModelView findAllAFind()
    {
        ArrayList<Emp> l=new ArrayList();
        ModelView m=new ModelView("a.jsp");
        l.add(new Emp(1,"Miora"));
        l.add(new Emp(2,"AAA"));
        m.addData("anarana",l);
        return m;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
}

