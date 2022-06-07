package tn.esprit.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {

    private static final Logger l = LoggerFactory.getLogger(EntrepriseServiceImplTest.class);


    @Autowired
    EntrepriseRepository entrepriseRepository;

    @Autowired
    IEntrepriseService iEntrepriseService;


    @Test

    public void testAddEntreprise() {
        Entreprise entreprise = new Entreprise("Vermeg", "boite de developpement");
        Long dataPreTest = entrepriseRepository.count();
        int savedEntrepriseid = iEntrepriseService.ajouterEntreprise(entreprise);
        Long dataAfterTest = entrepriseRepository.count();
        Assert.assertEquals(dataPreTest.intValue(),dataAfterTest - 1);
        l.info("add entreprise : " + entreprise);
        iEntrepriseService.deleteEntrepriseById(savedEntrepriseid);
    }




    @Test
    public void testSupprimerEntreprise() {
        Entreprise entreprise = new Entreprise("Vermeg", "boite de developpement");
        int savedEntreprise = iEntrepriseService.ajouterEntreprise(entreprise);
        Long dataPreTest = entrepriseRepository.count();
        iEntrepriseService.deleteEntrepriseById(savedEntreprise);
        Long dataAfterTest = entrepriseRepository.count();
        Assert.assertEquals(dataPreTest.intValue(),dataAfterTest + 1);
        l.info(" this entreprise has been deleted : " + entreprise);
    }

}