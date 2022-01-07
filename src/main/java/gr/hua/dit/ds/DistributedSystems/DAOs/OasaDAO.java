package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Validations;

public interface OasaDAO {
    Validations reject(Validations validation);
}
