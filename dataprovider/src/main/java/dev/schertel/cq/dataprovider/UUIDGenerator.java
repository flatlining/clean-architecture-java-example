package dev.schertel.cq.dataprovider;

import dev.schertel.cq.circular.usecase.input.IdGenerator;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UUIDGenerator implements IdGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
