package dev.schertel.cq.usecase;

import dev.schertel.cq.usecase.output.GenerateId;

import java.util.UUID;

public class UUIDUseCase implements GenerateId {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
