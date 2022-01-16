package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;

public interface OasaDAO {
    Applications confirmApplication(Applications application);
    Applications rejectApplication(Applications application);
}
