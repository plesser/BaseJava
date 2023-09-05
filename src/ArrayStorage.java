/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int CAPACITY = 10000;
    Resume[] storage = new Resume[CAPACITY];
    int countResumes = 0;
//    static final double SCALE = 0.75;

    void clear() {
        //storage = new Resume[CAPACITY];
        for (int i = 0; i < countResumes; i++) {
            storage[i] = null;
        }
        countResumes = 0;
    }

    void save(Resume r) {
        if (r.uuid == null) {
            return;
        }

        int index = -1;
        for (int i = 0; i < countResumes; i++) {
            if (r.uuid.equals(storage[i].uuid)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
//            if (countResumes > (int) (storage.length * SCALE)) {
//                System.out.println("resize storage " + storage.length * 2);
//                Resume[] tempStorage = new Resume[countResumes];
//                System.arraycopy(storage, 0, tempStorage, 0, countResumes);
//                storage = new Resume[storage.length * 2];
//                System.arraycopy(tempStorage, 0, storage, 0, countResumes);
//            }
            storage[countResumes] = r;
            countResumes++;
        }
    }

    Resume get(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < countResumes; i++) {
                if (uuid.equals(storage[i].uuid)) {
                    return storage[i];
                }
            }
        }

        return null;
    }

    void delete(String uuid) {
//        int index = -1;
        if (uuid != null) {
            for (int i = 0; i < countResumes; i++) {
                if (uuid.equals(storage[i].uuid)) {
//                    index = i;
                    storage[i] = storage[countResumes - 1];
                    storage[countResumes - 1] = null;
                    countResumes--;
                }
            }
        }

//        if (index != -1) {
////            for (int i = index; i < countResumes; i++) {
////                storage[i] = storage[i + 1];
////            }
//            storage[index] = storage[countResumes - 1];
//            storage[countResumes - 1] = null;
//            countResumes--;
//        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[countResumes];
        System.arraycopy(storage, 0, allResume, 0, countResumes);
        return allResume;
    }

    int size() {
        return countResumes;
    }
}
