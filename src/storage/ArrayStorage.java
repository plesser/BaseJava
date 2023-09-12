package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{


    @Override
    void addElement(Resume r) {
        storage[countResumes] = r;
        countResumes++;

    }

    @Override
    void deleteElement(int index) {
        storage[index] = storage[countResumes - 1];
        storage[countResumes - 1] = null;
        countResumes--;
    }

    @Override
    void updateElement(int index, Resume r) {
        storage[index] = r;
    }
}
