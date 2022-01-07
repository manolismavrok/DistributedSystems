package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import gr.hua.dit.ds.DistributedSystems.Entities.Validations;

public interface OaedDAO {
    Validations validate(Validations validation);
    Applications reject(Applications application);
}
