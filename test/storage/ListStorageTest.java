package storage;

import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest{
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    public void saveOverflow(){
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.CAPACITY * 2; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e){
            Assert.fail();
        }
        storage.save(new Resume());
    }

}