package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{


    @Override
    void addElement(Resume r, int index) {
        storage[countResumes] = r;

    }

    @Override
    void deleteElement(int index) {
        storage[index] = storage[countResumes - 1];
    }

    @Override
    int getIndex(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
