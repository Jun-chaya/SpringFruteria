package fruteria.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fruteria.Entity.ReciboEntity;

@Repository
public interface ReciboRepository extends JpaRepository<ReciboEntity, Long> {
	
	List<ReciboEntity> findByFecha(String fecha);
}
