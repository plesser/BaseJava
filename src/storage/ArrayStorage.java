package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{


    @Override
    void addElement(Resume r) {
        storage[countResumes] = r;

    }

    @Override
    void deleteElement(int index) {
        storage[index] = storage[countResumes - 1];
    }

}
