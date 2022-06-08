package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;



@SpringBootTest
public class TimesheetServiceImplTests {


	private static final Logger l = Logger.getLogger(TimesheetServiceImplTests.class);

	@Autowired
	DepartementRepository deptRepoistory;

	@Autowired
	IDepartementService idepartementService;

	@Autowired
	IEntrepriseService entrepriseService;

	@Autowired
	ContratRepository contratRepository;
	@Autowired
	ContratServiceImpl contratService;

	@Autowired
	MissionRepository missionRepository;

	@Autowired
	ITimesheetService iTimesheetService;

	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Autowired
	IEmployeService iEmployeService;

	@Test
	public void testAddMission() {
		Mission mission = new Mission("Consulting", "Testing and Quality Assurance");
		Long dataPreTest = missionRepository.count();
		Mission savedMission = missionRepository.save(mission);
		Long dataAfterTest = missionRepository.count();
		assertThat(dataPreTest).isEqualTo(dataAfterTest - 1);
		assertThat(savedMission.getId()).isEqualTo(mission.getId());
		l.info("add mission : " + mission);
		iTimesheetService.deleteMissionById(mission.getId());
	}

	@Test
	public void testSupprimerMission() {
		Mission mission = new Mission("Consulting", "Testing and Quality Assurance");
		Mission savedMission = missionRepository.save(mission);
		Long dataPreTest = missionRepository.count();
		iTimesheetService.deleteMissionById(savedMission.getId());
		Long dataAfterTest = missionRepository.count();
		assertThat(dataPreTest).isEqualTo(dataAfterTest + 1);
		l.info(" this mission has been deleted : " + mission);
	}

}
