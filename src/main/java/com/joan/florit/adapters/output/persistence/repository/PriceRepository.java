package com.joan.florit.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joan.florit.adapters.output.persistence.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long>{
    
}
