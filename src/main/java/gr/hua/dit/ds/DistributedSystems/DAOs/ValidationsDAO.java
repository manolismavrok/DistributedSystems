package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Validations;

import java.util.List;

public interface ValidationsDAO {
    List<Validations> getAllValidations();
    Validations getValidationsById(int id);
}
