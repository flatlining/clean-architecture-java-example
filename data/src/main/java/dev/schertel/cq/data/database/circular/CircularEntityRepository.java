package dev.schertel.cq.data.database.circular;

import dev.schertel.cq.data.database.circular.entity.CircularEntity;
import org.springframework.data.repository.CrudRepository;

public interface CircularEntityRepository extends CrudRepository<CircularEntity, String> {
}
