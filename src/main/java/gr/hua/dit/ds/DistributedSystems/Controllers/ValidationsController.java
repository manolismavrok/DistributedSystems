package gr.hua.dit.ds.DistributedSystems.Controllers;

import gr.hua.dit.ds.DistributedSystems.DAOs.ValidationsDAO;
import gr.hua.dit.ds.DistributedSystems.Entities.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidationsController {

    @Autowired
    private ValidationsDAO validationsDAO;

    @GetMapping("/api/validations/getAllValidations")
    public List<Validations> getAllValidations() {
        List<Validations> validations = validationsDAO.getAllValidations();

        return validations;
    }

    @GetMapping("/api/validations/getValidationsById")
    public Validations getValidationsById(@RequestParam int id) {
        Validations validation = validationsDAO.getValidationsById(id);

        return validation;
    }
}
