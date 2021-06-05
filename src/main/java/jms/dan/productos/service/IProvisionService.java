package jms.dan.productos.service;

import jms.dan.productos.model.Provision;

import java.util.List;

public interface IProvisionService {
    void createProvision(Provision provision);

    List<Provision> getProvisions();

    Provision getProvisionById(Integer id);
}
