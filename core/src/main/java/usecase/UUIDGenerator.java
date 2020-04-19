package usecase;

import usecase.output.GenerateId;

import java.util.UUID;

public class UUIDGenerator implements GenerateId {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
