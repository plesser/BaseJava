import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int CAPACITY = 10000;
    Resume[] storage = new Resume[CAPACITY];
    int countResumes = 0;

    public void clear() {
        Arrays.fill(storage, null);
        countResumes = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.uuid);
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR UPDATE: Don't find resume with uuid " + r.uuid);
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.uuid);
        if (countResumes == storage.length){
            System.out.println("ERROR: storage is full");
        } else if (index == -1 && countResumes <= storage.length) {
            storage[countResumes] = r;
            countResumes++;
        } else {
            System.out.println("ERROR: Resume already exist " + r.uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1){
            return storage[index];
        } else {
            System.out.println("ERROR: Don't find resume with uuid " + uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
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
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResumes);
    }

    public int size() {
        return countResumes;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }

}
