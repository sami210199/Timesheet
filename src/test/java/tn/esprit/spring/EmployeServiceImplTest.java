package tn.esprit.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeServiceImplTest {

    @Test
    public void contextLoads() {
    }


    @Autowired
    IEmployeService iEmployeService;


    @Test
    public void testaddOrUpdateEmploye() {
        Employe employe = new Employe("trojette","khalil","khalil.trojette@esprit.com","123456",true, Role.TECHNICIEN);
        int nmbrEmployeeBefore = iEmployeService.getNombreEmployeJPQL();
        int savedEmployeId =  iEmployeService.addOrUpdateEmploye(employe);
        int nmbrEmployeeAfter = iEmployeService.getNombreEmployeJPQL();
        Assert.assertEquals(nmbrEmployeeBefore,nmbrEmployeeAfter-1);
        iEmployeService.deleteEmployeById(savedEmployeId);

    }
}