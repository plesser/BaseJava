package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    public static final int CAPACITY = 100000;
    protected Resume[] storage = new Resume[CAPACITY];
    public int countResumes = 0;

    public void clear() {
        Arrays.fill(storage, null);
        countResumes = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());

        if (index > 0) {
            storage[index] = r;
        } else {
            //System.out.println("ERROR UPDATE: Don't find resume with uuid " + r.getUuid());
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (countResumes == storage.length){
            //System.out.println("ERROR: storage is full");
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index >= 0) {
            //System.out.println("ERROR: model.Resume already exist " + r.getUuid());
            throw new ExistStorageException(r.getUuid());
        } else {
            addElement(r, index);
            countResumes++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0){
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
            storage[countResumes-1] = null;
            countResumes--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResumes);
    }

    public int size() {
        return countResumes;
    }

    abstract void addElement(Resume r, int index);
    abstract void deleteElement(int index);

    abstract int getIndex(String uuid);
}
