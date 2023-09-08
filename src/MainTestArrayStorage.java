/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {

        System.out.println("add resumes");
        for (int i = 0; i <= 10; i++){
            Resume r = new Resume();
            r.uuid = "uuid" + i;
            // добавил поле name что бы можно было протестировать функцию update
            ARRAY_STORAGE.save(r);
        }

        // проверяем на возможность записи резюме с уже существующим uuid
        Resume r = new Resume();
        r.uuid = "uuid" + 10;
        ARRAY_STORAGE.save(r);

        // Проверяем на превышение размерности массива
        for (int i = 11; i <= 20; i++){
            r = new Resume();
            r.uuid = "uuid" + i;
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
        System.out.println("delete resume uuid12");
        ARRAY_STORAGE.delete("uuid12");
        ARRAY_STORAGE.delete("<wrong resume>");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        // обновление существующего и не существующего
        System.out.println("Test update resume");
        r = ARRAY_STORAGE.get("uuid10");
        String uuid = r.uuid;
        r = new Resume();
        r.uuid = uuid;
        ARRAY_STORAGE.update(r);
        r = new Resume();
        r.uuid = "<wrong resume>";
        ARRAY_STORAGE.update(r);
        printAll();

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
