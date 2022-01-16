package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;

public interface OaedDAO {
    Applications validateApplication(Applications application);
    Applications rejectApplication(Applications application);
}
