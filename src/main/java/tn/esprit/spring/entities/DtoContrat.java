package tn.esprit.spring.entities;

import java.util.Date;


    public class DtoContrat {

        private int reference;
        private Date dateDebut;

        private String typeContrat;

        private Employe employe;

        private float salaire;


        public static Contrat modelMap(DtoContrat dtocontrat) {
            Contrat contrat = new Contrat();
            if(dtocontrat.getReference()!=0)
            {
                contrat.setReference(dtocontrat.getReference());
            }
            contrat.setEmploye(dtocontrat.getEmploye());
            contrat.setDateDebut(dtocontrat.getDateDebut());
            contrat.setSalaire(dtocontrat.getSalaire());
            contrat.setTypeContrat(dtocontrat.getTypeContrat());

            return contrat;

        }


        public Date getDateDebut() {
            return dateDebut;
        }

        public void setDateDebut(Date dateDebut) {
            this.dateDebut = dateDebut;
        }

        public int getReference() {
            return reference;
        }

        public void setReference(int reference) {
            this.reference = reference;
        }

        public String getTypeContrat() {
            return typeContrat;
        }

        public void setTypeContrat(String typeContrat) {
            this.typeContrat = typeContrat;
        }

        public float getSalaire() {
            return salaire;
        }

        public void setSalaire(float salaire) {
            this.salaire = salaire;
        }

        public Employe getEmploye() {
            return employe;
        }

        public void setEmploye(Employe employe) {
            this.employe = employe;
        }
    }


