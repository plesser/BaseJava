package exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String message, String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
