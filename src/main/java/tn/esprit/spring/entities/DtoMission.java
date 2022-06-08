package tn.esprit.spring.entities;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
public class DtoMission {

        private int id;
        private String name;
        private String description;
        private Departement departement;

        public Departement getDepartement() {
            return departement;
        }

        public void setDepartement(Departement departement) {
            this.departement = departement;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public static Mission modelMap(DtoMission dto) {
            Mission mission = new Mission();
            if (dto.getId() != 0) {
                mission.setId(dto.getId());
            }
            mission.setId(dto.getId());
            mission.setDescription(dto.getDescription());
            mission.setName(dto.getName());
            mission.setDepartement(dto.getDepartement());
            return mission;
        }

    }

