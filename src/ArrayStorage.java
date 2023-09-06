/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int CAPACITY = 10000;
    Resume[] storage = new Resume[CAPACITY];
    int countResumes = 0;

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

        if (index == -1 && countResumes <= storage.length) {
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
                    break;
                }
            }
        }
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
