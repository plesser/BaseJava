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
        if (r.uuid == null) {
            System.out.println("Resume with null uuid");
            return;
        }

        int index = getIndexResume(r.uuid);
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Don't find resume with uuid " + r.uuid);
        }

    }

    void save(Resume r) {
        if (r.uuid == null) {
            System.out.println("Resume with null uuid");
            return;
        }

        int index = getIndexResume(r.uuid);

        if (countResumes == storage.length){
            System.out.println("storage is full");
            return;
        }

        if (index == -1 && countResumes <= storage.length) {
            storage[countResumes] = r;
            countResumes++;
        } else {
            System.out.println("Resume already exist " + r.uuid);
        }
    }

    Resume get(String uuid) {
        if (uuid != null) {
            int index = getIndexResume(uuid);
            if (index != -1){
                return storage[index];
            }
        }
        System.out.println("Don't find resume with uuid " + uuid);
        return null;
    }

    void delete(String uuid) {
//        int index = -1;
        if (uuid != null) {
            int index = getIndexResume(uuid);
            if (index != -1) {
                storage[index] = storage[countResumes - 1];
                storage[countResumes - 1] = null;
                countResumes--;
            } else {
                System.out.println("Don't find resume with uuid " + uuid);
            }
        } else {
            System.out.println("Resume with null uuid");
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

}
