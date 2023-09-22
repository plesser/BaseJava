package storage;

import exception.ExistStorageException;
import exception.StorageException;
import model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage{

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


    abstract void addElement(Resume r, int index);
    abstract void deleteElement(int index);
    abstract int getIndex(String uuid);
}
