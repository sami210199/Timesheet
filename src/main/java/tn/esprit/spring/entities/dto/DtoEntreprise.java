package tn.esprit.spring.entities.dto;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.spring.entities.*;

public class DtoEntreprise {
    private int id;

    private String name;


    private String raisonSocial;
    private List<Departement> departements = new ArrayList<>();



    public static Entreprise modelMap(DtoEntreprise dtoentreprise) {
        Entreprise entreprise = new Entreprise();
        if(dtoentreprise.getId()!=0)
        {
            entreprise.setId(dtoentreprise.getId());
        }
        entreprise.setName(dtoentreprise.getName());
        entreprise.setDepartements(dtoentreprise.getDepartements());
        entreprise.setRaisonSocial(dtoentreprise.getRaisonSocial());

        return entreprise;

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

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public List<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }
}
