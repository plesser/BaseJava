package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    void addElement(Resume r, int index) {
        if (countResumes == 0) {
            storage[0] = r;
            return;
        }
        //Resume[] tempStorage = Arrays.copyOfRange(storage, 0, countResumes);
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, countResumes - insertIdx);
        storage[insertIdx] = r;

    }

    @Override
    void deleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, countResumes);
    }

    @Override
    int getIndex(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResumes, r);
    }


}
