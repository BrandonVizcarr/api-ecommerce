package com.api_ecommerce.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api_ecommerce.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating,Long>{
    
    Boolean existsByUserIdAndTargetTypeAndTargetId(UUID userId, String targetType, String targetId);

}
