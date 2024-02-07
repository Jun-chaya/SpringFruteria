package fruteria.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fruteria.Entity.OrigenEntity;

public interface OrigenRepository extends JpaRepository<OrigenEntity, Long>{

	List<OrigenEntity> findByPais(String nombre);
	
	List<OrigenEntity> findByCiudad(String nombre);
}
