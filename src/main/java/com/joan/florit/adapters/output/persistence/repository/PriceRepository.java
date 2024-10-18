package com.joan.florit.adapters.output.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joan.florit.adapters.output.persistence.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long>{

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer brandId, Integer productId,  LocalDateTime startDat,  LocalDateTime endDat);

}
