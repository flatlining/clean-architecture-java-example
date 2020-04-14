package dev.schertel.cq.circular.exception;

public class CircularQueueNotFoundException extends RuntimeException {
    private String id;

    public CircularQueueNotFoundException(String id) {
        super(String.format("Unable to find CircularQueue for id: %s", id));
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
