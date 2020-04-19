package dev.schertel.cq.circular.dataprovider;

import dev.schertel.cq.circular.usecase.input.IdGeneratorDataProvider;

import java.util.UUID;

public class UUIDGenerator implements IdGeneratorDataProvider {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
