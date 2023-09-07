/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {

        System.out.println("add resumes");
        for (int i = 0; i <= 25; i++){
            Resume r = new Resume();
            r.uuid = "uuid" + i;
            // добавил поле name что бы можно было протестировать функцию update
            r.name = "name #" + i;
            ARRAY_STORAGE.save(r);
        }

        System.out.println("get resume");
        System.out.println("Get resume uuid10: " + ARRAY_STORAGE.get("uuid10"));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get resume dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        System.out.println("delete resume uuid12");
        ARRAY_STORAGE.delete("uuid12");
        ARRAY_STORAGE.delete("uuid18");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());


        System.out.println("Test update resume");
        Resume r = ARRAY_STORAGE.get("uuid10");
        System.out.println(r);
        r.name = "updated name";
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
