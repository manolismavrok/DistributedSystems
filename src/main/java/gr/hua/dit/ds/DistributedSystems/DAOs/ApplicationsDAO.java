package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;

import java.util.List;

public interface ApplicationsDAO {
    List<Applications> getAllApplications();
    Applications getApplicationById(int id);
    List<Applications> getNewApplications();
    List<Applications> getValidatedApplications();
    Boolean applicationExists(int id);
}


