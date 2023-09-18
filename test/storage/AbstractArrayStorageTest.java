package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(new Resume(UUID_1), array[0]);
        assertEquals(new Resume(UUID_2), array[1]);
        assertEquals(new Resume(UUID_3), array[2]);
    }

    @Test(expected = ExistStorageException.class)
    public void save() throws Exception {
        Resume newResume = new Resume("uuid4");
        storage.save(newResume);
        Assert.assertEquals(4, storage.size());
        assertEquals(storage.get("uuid4"), newResume);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow(){
        try {
            for (int i = 4; i < AbstractArrayStorage.CAPACITY + 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e){
            Assert.fail();
        }
        storage.save(new Resume());
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        Resume newResume = new Resume("uuid2");
        storage.delete(newResume.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test(expected = NotExistStorageException.class)
    public void get() throws Exception {
        assertEquals(storage.get("uuid1"), new Resume(UUID_1));
        assertEquals(storage.get("uuid2"), new Resume(UUID_2));
        assertEquals(storage.get("uuid3"), new Resume(UUID_3));
        assertEquals(storage.get("uuid3a"), new Resume(UUID_3));

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}