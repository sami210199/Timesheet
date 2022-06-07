package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	private static final Logger L = LoggerFactory.getLogger(EntrepriseServiceImpl.class);
	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	public int ajouterEntreprise(Entreprise entreprise) {
		L.info("Start ajouterEntreprise().");
		L.debug("Entreprise to save : " + entreprise );
		L.info("Saving.");
		entreprise = entrepriseRepoistory.save(entreprise);
		L.debug("Entreprise saved : " + entreprise );
		L.info("Returning saved Entreprise");
		return entreprise.getId();
	}

	@Override
	public int ajouterDepartement(Departement dep) {
		return 0;
	}


	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {

		L.info("Start affecterDepartementAEntreprise().");
		L.debug("Entreprise Id = : " + entrepriseId );
		L.debug("Get By Id entreprise");

		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		if(entrepriseManagedEntity.isPresent()) {
			Entreprise e = entrepriseManagedEntity.get();
			L.debug("Entreprise received = " + e);

			L.debug("departement Id = : " + depId);
			L.debug("Get By Id departement");
			Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
			if (depManagedEntity.isPresent()) {
				Departement d = depManagedEntity.get();
				L.debug("departement received = " + d);
				L.debug("Setting entreprise to departement");
				d.setEntreprise(e);
				L.debug("Setting entreprise to departement Done " + d);
				L.debug("Saving department after Editing ");
				deptRepoistory.save(d);
			}
		}
	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		if(entrepriseManagedEntity.isPresent()) {
			Entreprise e = entrepriseManagedEntity.get();
			List<String> depNames = new ArrayList<>();
			for (Departement dep : e.getDepartements()) {
				depNames.add(dep.getName());
			}

			return depNames;
		}
		return null;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		L.info("Start deleteEntrepriseById().");
		L.debug("Id Entreprise : " + entrepriseId);
		Optional<Entreprise>  e = entrepriseRepoistory.findById(entrepriseId);
		if (e.isPresent()) {
			entrepriseRepoistory.delete(e.get());
			L.debug("Delete Entreprise " + entrepriseId + " deleted.");
			L.info("End deleteEntrepriseById().");
		}

	}

	@Override
	public void deleteDepartementById(int depId) {

	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		L.info("Start getEntrepriseById().");
		L.debug("EntrepriseId   : " + entrepriseId);
		Optional <Entreprise> e = entrepriseRepoistory.findById(entrepriseId);
		if(e.isPresent()) {
			return e.get();
		}
		return null;
	}

}
