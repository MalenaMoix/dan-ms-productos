package jms.dan.productos.repository;

import jms.dan.productos.model.Provision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProvisionRepository extends JpaRepository<Provision, Integer> {
}
