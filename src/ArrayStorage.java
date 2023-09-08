import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int CAPACITY = 20;
    Resume[] storage = new Resume[CAPACITY];
    int countResumes = 0;

    void clear() {
        Arrays.fill(storage, null);
        countResumes = 0;
    }

    void update(Resume r) {
        if (resumeIsNull(r)) return;

        int index = getIndexResume(r.uuid);
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR UPDATE: Don't find resume with uuid " + r.uuid);
        }

    }


    void save(Resume r) {
        if (resumeIsNull(r)) return;

        int index = getIndexResume(r.uuid);

        if (countResumes == storage.length){
            System.out.println("ERROR: storage is full");
            return;
        }

        if (index == -1 && countResumes <= storage.length) {
            storage[countResumes] = r;
            countResumes++;
        } else {
            System.out.println("ERROR: Resume already exist " + r.uuid);
        }
    }

    Resume get(String uuid) {
        if (uuidIsNull(uuid)){
            return null;
        }
        int index = getIndexResume(uuid);
        if (index != -1){
            return storage[index];
        } else {
            System.out.println("ERROR: Don't find resume with uuid " + uuid);
            return null;
        }
    }

    void delete(String uuid) {
        if (uuidIsNull(uuid)){
            return;
        }

        int index = getIndexResume(uuid);
        if (index != -1) {
            storage[index] = storage[countResumes - 1];
            storage[countResumes - 1] = null;
            countResumes--;
        } else {
            System.out.println("ERROR: Don't find resume with uuid " + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        //Resume[] allResume = new Resume[countResumes];
        //System.arraycopy(storage, 0, allResume, 0, countResumes);
        return Arrays.copyOfRange(storage, 0, countResumes);
    }

    int size() {
        return countResumes;
    }

    private int getIndexResume(String uuid) {
        int index = -1;
        for (int i = 0; i < countResumes; i++) {
            if (uuid.equals(storage[i].uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static boolean resumeIsNull(Resume r) {
        if (r.uuid == null) {
            System.out.println("ERROR: Resume with null uuid");
            return true;
        }
        return false;
    }

    private static boolean uuidIsNull(String uuid) {
        if (uuid == null) {
            System.out.println("ERROR: Uuid is null");
            return true;
        }
        return false;
    }

}
