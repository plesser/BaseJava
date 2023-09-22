package storage;

import exception.ExistStorageException;
import exception.StorageException;
import model.Resume;

public class ListStorage extends AbstractStorage{

    public static final double SCALE = 0.75;

    @Override
    int getIndex(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    void deleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, countResumes);
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            //System.out.println("ERROR: model.Resume already exist " + r.getUuid());
            throw new ExistStorageException(r.getUuid());
        } else if (countResumes >= storage.length * SCALE){
            Resume[] tempStorage = new Resume[countResumes];
            System.arraycopy(
                    storage, 0, tempStorage, 0, countResumes);

            storage = new Resume[storage.length * 2];
            System.arraycopy(
                    tempStorage, 0, storage, 0, countResumes);

        }
        storage[countResumes] = r;
        countResumes++;
    }
}
