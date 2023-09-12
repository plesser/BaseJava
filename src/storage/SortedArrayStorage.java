package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    void addElement(Resume r) {
        if (countResumes == 0) {
            storage[0] = r;
            countResumes++;
            return;
        }

        Resume[] tempStorage = Arrays.copyOfRange(storage, 0, countResumes);

        int index = Arrays.binarySearch(tempStorage, r);

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

        countResumes++;

    }

    @Override
    void deleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, countResumes);
        storage[countResumes] = null;
        countResumes--;

    }

    @Override
    void updateElement(int index, Resume r) {
        delete(r.getUuid());
        save(r);
    }

}
