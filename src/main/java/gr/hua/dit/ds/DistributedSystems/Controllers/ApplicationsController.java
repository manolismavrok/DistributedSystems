package gr.hua.dit.ds.DistributedSystems.Controllers;

import gr.hua.dit.ds.DistributedSystems.DAOs.ApplicationsDAO;
import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationsController {

    @Autowired
    private ApplicationsDAO applicationsDAO;

    @GetMapping("/api/applications/getAllApplications")
    public List<Applications> getAllApplications() {
        List<Applications> applications = applicationsDAO.getAllApplications();

        return applications;
    }

    @GetMapping("/api/applications/getApplicationsById")
    public Applications getApplicationsById(@RequestParam int id) {
        Applications application = applicationsDAO.getApplicationById(id);

        return application;
    }

}
