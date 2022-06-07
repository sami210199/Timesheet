package tn.esprit.spring.entities;

import java.util.List;

import tn.esprit.spring.entities.*;

public class DtoDepartement {


    private int id;

    private String name;
    private List<Employe> employes;
    private List<Mission> missions;
    private Entreprise entreprise;


    public static Departement modelMap(DtoDepartement dtodep) {
        Departement dep = new Departement();
        if(dtodep.getId()!=0)
        {
            dep.setId(dtodep.getId());
        }
        dep.setEmployes(dtodep.getEmployes());
        dep.setName(dtodep.getName());
        dep.setMissions(dtodep.getMissions());
        dep.setEntreprise(dtodep.getEntreprise());

        return dep;

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

}
