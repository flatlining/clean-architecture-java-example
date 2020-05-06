package dev.schertel.cq.data.repository.circular;

import dev.schertel.cq.data.repository.circular.entity.CircularEntity;
import org.springframework.data.repository.CrudRepository;

public interface CircularEntityRepository extends CrudRepository<CircularEntity, String> {
}
