package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	private static final Logger L = LoggerFactory.getLogger(TimesheetServiceImpl.class);

	public int ajouterMission(Mission mission) {
		L.info("Start ajouterMission().");
		L.debug("Add Mission : " + mission.getId());
		missionRepository.save(mission);
		L.debug("Mission " + mission.getId() + " added.");
		L.info("End ajouterMission().");
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		L.info("Start affecterMissionADepartement().");
		Optional <Mission> missionOpt = missionRepository.findById(missionId);
		Optional<Departement> depOpt = deptRepoistory.findById(depId);
		if (missionOpt.isPresent() && depOpt.isPresent()) {
			Mission mission = missionOpt.get();
			Departement dep = depOpt.get();
			L.debug("Affect Departement : " + dep.getId() + " to Mission : " + mission.getId());
			mission.setDepartement(dep);
			missionRepository.save(mission);
			L.debug("Departement " + dep.getId() + " affected.");
			L.info("End affecterMissionADepartement().");
		}
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		L.info("In valider Timesheet");

		Optional<Employe> e = employeRepository.findById(validateurId);
		if (e.isPresent()) {
			Employe validateur = e.get();
			Optional<Mission> m = missionRepository.findById(missionId);
			if (m.isPresent()) {
				Mission mission = m.get();
				//verifier s'il est un chef de departement (interet des enum)
				if (!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)) {
					L.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
					return;
				}
				//verifier s'il est le chef de departement de la mission en question
				boolean chefDeLaMission = false;
				for (Departement dep : validateur.getDepartements()) {
					if (dep.getId() == mission.getDepartement().getId()) {
						chefDeLaMission = true;
						break;
					}
				}
				if (!chefDeLaMission) {
					L.info("l'employe doit etre chef de departement de la mission en question");
					return;
				}
//
				TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
				Timesheet timesheet = timesheetRepository.findBytimesheetPK(timesheetPK);
				timesheet.setValide(true);

				//Comment Lire une date de la base de données
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				L.debug(dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
			}
		}
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		L.info("Start findAllMissionByEmployeJPQL().");
		L.debug("EmployeId   : " + employeId);
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		L.info("Start getAllEmployeByMission().");
		L.debug("MissionId   : " + missionId);
		return timesheetRepository.getAllEmployeByMission(missionId);
	}


	public void deleteMissionById(int missionId) {
		L.info("Start deleteMissionById().");
		L.debug("Id mission à supprimer : " + missionId);
		missionRepository.deleteById(missionId);
		L.debug("Mission " + missionId + " supprmée.");
		L.info("End deleteMissionById().");
	}

}
