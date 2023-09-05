/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
//        Resume r1 = new Resume();
//        r1.uuid = "uuid1";
//        Resume r2 = new Resume();
//        r2.uuid = "uuid2";
//        Resume r3 = new Resume();
//        r3.uuid = "uuid3";

//        ARRAY_STORAGE.save(r1);
//        ARRAY_STORAGE.save(r2);
//        ARRAY_STORAGE.save(r3);
        System.out.println("add resumes");
        for (int i = 0; i <= 20; i++){
            Resume r = new Resume();
            r.uuid = "uuid" + i;
            ARRAY_STORAGE.save(r);
        }

        System.out.println("get resume");
        System.out.println("Get resume uuid10: " + ARRAY_STORAGE.get("uuid10"));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get resume dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        System.out.println("delete resume uuid12");
        ARRAY_STORAGE.delete("uuid12");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

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
