package dev.schertel.cq.dataprovider;

import dev.schertel.cq.circular.usecase.input.IdGenerator;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
