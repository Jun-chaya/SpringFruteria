package fruteria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fruteria.Entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
