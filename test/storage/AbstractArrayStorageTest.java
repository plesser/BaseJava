package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class AbstractArrayStorageTest {
    final private Storage storage;

    private static final String UUID_1 = "uuid1";
    private final Resume resume1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private final Resume resume2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private final Resume resume3 = new Resume(UUID_3);


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
        assertArrayEquals(storage.getAll(), new Resume[]{});
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_2);

        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume newResume = new Resume("uuid4");
        storage.update(newResume);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] actual = storage.getAll();
        assertEquals(3, actual.length);
        assertArrayEquals(storage.getAll(), actual);
    }

    @Test(expected = ExistStorageException.class)
    public void save() throws Exception {
        Resume newResume = new Resume("uuid4");
        storage.save(newResume);
        assertSize(4);
        assertGet(newResume);
        storage.save(newResume);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow(){
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e){
            Assert.fail();
        }
        storage.save(new Resume());
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(resume2.getUuid());
        assertSize(2);
        storage.get(resume2.getUuid());

    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        Resume newResume = new Resume("uuid4");

        storage.delete(newResume.getUuid());
        storage.get(newResume.getUuid());

    }

    @Test
    public void get() throws Exception {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    private void assertGet(Resume resume) {
        assertEquals(storage.get(resume.getUuid()), resume);

    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}