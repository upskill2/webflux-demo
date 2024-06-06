package com.webflux_microservices.order_service.repository;

import com.webflux_microservices.order_service.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Integer> {

    List<PurchaseOrderEntity> findByUserId(UUID userId);
}
