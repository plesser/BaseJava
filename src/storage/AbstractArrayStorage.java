package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    static final int CAPACITY = 100000;
    protected Resume[] storage = new Resume[CAPACITY];
    public int countResumes = 0;

    public void clear() {
        Arrays.fill(storage, null);
        countResumes = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR UPDATE: Don't find resume with uuid " + r.getUuid());
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (countResumes == storage.length){
            System.out.println("ERROR: storage is full");
        } else if (index >= 0) {
            System.out.println("ERROR: model.Resume already exist " + r.getUuid());
        } else {
            addElement(r, index);
            countResumes++;
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
            deleteElement(index);
            storage[countResumes-1] = null;
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

//    private int getIndex(String uuid) {
//        for (int i = 0; i < countResumes; i++) {
//            if (uuid.equals(storage[i].getUuid())) {
//                return i;
//            }
//        }
//        return -1;
//    }


    abstract void addElement(Resume r, int index);
    abstract void deleteElement(int index);

    abstract int getIndex(String uuid);
}
