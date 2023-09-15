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
        if (index == -1) {
            // вставить значение слево
            System.arraycopy(storage, 0, storage, 1, countResumes);
            storage[0] = r;
        } else if (index * (-1) -1  < countResumes) {
            // вставить значение посередине
            System.arraycopy(storage,
                    index * (-1) - 1,
                    storage,
                    index * (-1),
                    countResumes - (index) * (-1) + 1);
            storage[index * (-1) - 1] = r;
        } else {
            //вставить значение справа
            storage[countResumes] = r;
        }


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
