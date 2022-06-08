package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	private static final Logger L = Logger.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		return employeRepository.findEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		L.info("Start method addOrUpdateEmploye()");
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		L.info("Start of method mettreAjourEmailByEmployeId()");

		L.debug("Getting Employee where Id = " + employeId);
		Optional<Employe> employeOpt = employeRepository.findById(employeId);
		if (employeOpt.isPresent()) {
			Employe employe = employeOpt.get();
			L.debug("Employee Before update = " + employe);
			employe.setEmail(email);
			L.debug("Employee Updated.");
			employeRepository.save(employe);
			L.debug("Employee  saved.");
			L.info("End of Method mettreAjourEmailByEmployeId()");
		}

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		L.info("Start of method affecterEmployeADepartement()");

		Optional<Departement> depManagedEntityOPT = deptRepoistory.findById(depId);
		L.debug("Check if Departement exists");
		if (depManagedEntityOPT.isPresent())
		{
			Departement depManagedEntity = depManagedEntityOPT.get();

			Optional<Employe> employeManagedEntityOPT = employeRepository.findById(employeId);
			L.debug("Check if Employee exists");
			if (employeManagedEntityOPT.isPresent())
			{
				Employe employeManagedEntity = employeManagedEntityOPT.get();
				if (depManagedEntity.getEmployes() == null) {

					List<Employe> employes = new ArrayList<>();
					employes.add(employeManagedEntity);
					depManagedEntity.setEmployes(employes);
				} else {

					depManagedEntity.getEmployes().add(employeManagedEntity);
				}
				L.debug("Affect Employee to Departement");
				deptRepoistory.save(depManagedEntity);
			}


		}


	}

	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		L.info("Start of method desaffecterEmployeDuDepartement()");

		Optional<Departement> dep = deptRepoistory.findById(depId);
		if(dep.isPresent()) {

			int employeNb = dep.get().getEmployes().size();
			for (int index = 0; index < employeNb; index++) {
				if (dep.get().getEmployes().get(index).getId() == employeId) {
					dep.get().getEmployes().remove(index);
					break;
				}
			}
		}

	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		L.info("Start of method affecterContratAEmploye()");

		Optional<Contrat> contratManagedEntityOPT = contratRepoistory.findById(contratId);
		L.debug("check if contract exists");
		if(contratManagedEntityOPT.isPresent())
		{
			Contrat contratManagedEntity = contratManagedEntityOPT.get();
			Optional<Employe> employeManagedEntityOPT = employeRepository.findById(employeId);
			L.debug("check if employee exists");

			if (employeManagedEntityOPT.isPresent())
			{
				Employe employeManagedEntity = employeManagedEntityOPT.get();
				contratManagedEntity.setEmploye(employeManagedEntity);
				contratRepoistory.save(contratManagedEntity);
			}

		}
		L.info("end of method affecterContratAEmploye()");


	}

	public String getEmployePrenomById(int employeId) {
		Optional<Employe> employeeOPT = employeRepository.findById(employeId);
		if(employeeOPT.isPresent()) {
			return employeeOPT.get().getPrenom();
		}
		else {
			return null;
		}

	}
	 
	public void deleteEmployeById(int employeId)
	{
		Optional<Employe> employeeOPT = employeRepository.findById(employeId);
		if(employeeOPT.isPresent()) {
			Employe employe = employeeOPT.get();
			//Desaffecter l'employe de tous les departements
			//c'est le bout master qui permet de mettre a jour
			//la table d'association
			for (Departement dep : employe.getDepartements()) {
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);
		}

	}

	public void deleteContratById(int contratId) {
		Optional<Contrat> contratOPT = contratRepoistory.findById(contratId);
		if(contratOPT.isPresent())
			contratRepoistory.delete(contratOPT.get());



	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}
