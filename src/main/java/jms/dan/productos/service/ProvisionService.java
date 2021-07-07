package jms.dan.productos.service;

import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.model.Provision;
import jms.dan.productos.repository.IProvisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvisionService implements IProvisionService {
    final IProvisionRepository provisionRepository;

    @Autowired
    public ProvisionService(IProvisionRepository provisionRepository) {
        this.provisionRepository = provisionRepository;
    }

    @Override
    public void createProvision(Provision provision) {
        provisionRepository.save(provision);
    }

    @Override
    public List<Provision> getProvisions() {
        return provisionRepository.findAll();
    }

    @Override
    public Provision getProvisionById(Integer id) {
        Provision provision = provisionRepository.findById(id).orElse(null);
        if (provision == null) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Provision not found", HttpStatus.NOT_FOUND.value());
        }
        return provision;
    }
}
