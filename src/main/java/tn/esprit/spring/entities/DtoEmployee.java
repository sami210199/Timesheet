package tn.esprit.spring.entities;


import tn.esprit.spring.entities.*;


    public class DtoEmployee {

        private int id;

        private String prenom;

        private String nom;

        private String email;

        private String password;

        private boolean actif;

        private Role role;


        public static Employe modelMap(DtoEmployee dto)
        {
            Employe employe = new Employe();
            if(dto.getId()!=0)
            {
                employe.setId(dto.getId());
            }
            employe.setEmail(dto.email);
            employe.setActif(dto.isActif());
            employe.setNom(dto.getNom());
            employe.setPrenom(dto.getPrenom());
            employe.setPassword(dto.getPassword());
            employe.setRole(dto.getRole());

            return employe;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrenom() {
            return prenom;
        }



        public String getNom() {
            return nom;
        }


        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isActif() {
            return actif;
        }


        public Role getRole() {
            return role;
        }


    }


