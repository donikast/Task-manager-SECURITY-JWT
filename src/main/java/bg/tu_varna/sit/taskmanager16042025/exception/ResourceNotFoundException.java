package bg.tu_varna.sit.taskmanager16042025.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(long id, Class<?> item) {
        super(String.format("Resource with id = %d from class = %s", id,  item.getName()));
    }
}