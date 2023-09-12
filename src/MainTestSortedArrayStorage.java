import model.Resume;
import storage.SortedArrayStorage;

import java.util.Random;

/**
 * Test for your storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    static SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        ARRAY_STORAGE = new SortedArrayStorage();

        System.out.println("add resumes order by");
        for (int i = 0; i <= 10; i++){
            Resume r = new Resume();
            if (i < 10) {
                r.setUuid("uuid0" + i);
            } else {
                r.setUuid("uuid" + i);
            }
            ARRAY_STORAGE.save(r);
        }

        System.out.println("Size: " + ARRAY_STORAGE.size());
        printAll();
        ARRAY_STORAGE = new SortedArrayStorage();

        System.out.println("add resumes order by desc");
        for (int i = 10; i >= 0; i--){
            Resume r = new Resume();
            if (i < 10) {
                r.setUuid("uuid0" + i);
            } else {
                r.setUuid("uuid" + i);
            }
            ARRAY_STORAGE.save(r);
        }

        System.out.println("Size: " + ARRAY_STORAGE.size());
        printAll();

        Random random = new Random();
        System.out.println("add resumes random");
        for (int i = 10; i >= 0; i--){
            int ind = random.nextInt(100);
            Resume r = new Resume();
            if (ind < 10) {
                r.setUuid("uuid0" + ind);
            } else {
                r.setUuid("uuid" + ind);
            }
            ARRAY_STORAGE.save(r);
        }

        System.out.println("Size: " + ARRAY_STORAGE.size());
        printAll();


        // проверяем на возможность записи резюме с уже существующим uuid
        Resume r = new Resume();
        r.setUuid("uuid" + 10);
        ARRAY_STORAGE.save(r);

        // Проверяем на превышение размерности массива
        for (int i = 11; i <= 20; i++){
            r = new Resume();
            r.setUuid("uuid" + i);
            // добавил поле name что бы можно было протестировать функцию update
            ARRAY_STORAGE.save(r);
        }



        // получение резюме по uuid
        System.out.println("get resume");
        System.out.println("Get resume uuid10: " + ARRAY_STORAGE.get("uuid10"));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get resume dummy: " + ARRAY_STORAGE.get("dummy"));

        // удаляем существующие и не существующие резюме
        printAll();
        r = ARRAY_STORAGE.getAll()[10];
        System.out.println("delete resume " + r);
        ARRAY_STORAGE.delete(r.getUuid());
        ARRAY_STORAGE.delete("<wrong resume>");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());


        // обновление существующего и не существующего
        System.out.println("Test update resume");
        r = ARRAY_STORAGE.getAll()[12];
        String uuid = r.getUuid();
        r = new Resume();
        r.setUuid(uuid);
        ARRAY_STORAGE.update(r);
        r = new Resume();
        r.setUuid("<wrong resume>");
        ARRAY_STORAGE.update(r);
        printAll();

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r + " " + r.hashCode());
        }
        System.out.println("-----------------------------------------------------");
    }
}
