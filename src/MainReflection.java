import model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection{
        public static void main(String[] args) throws IllegalAccessException {
            Resume r = new Resume();
            Class clazz = r.getClass();
            Field field = clazz.getDeclaredFields()[0];
            field.setAccessible(true);
            System.out.println(field.getName());
            System.out.println(field.get(r));
            field.set(r, "new_uuid");
            // TODO : invoke r.toString via reflection
            System.out.println(r);
            System.out.println("-----------------------------------------------------------");
            try {
                //
                // специально прошелся от создания инстанса до вызова метода setUuid
                clazz = Class.forName("model.Resume");
                Method methodUuid = clazz.getMethod("toString");
                Method methodSetUuid = clazz.getDeclaredMethod("setUuid", String.class);
                Object o = clazz.newInstance();
                methodSetUuid.invoke(o, "Super new UUID");
                System.out.println(methodUuid.invoke(o));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
}
