package out.api.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjMapBeanUtil {
    public static String getName(Object obj) {
        if (obj instanceof Field) {
            return ((Field) obj).getName();
        }
        if (obj instanceof Map.Entry) {
            return ((Map.Entry) obj).getKey().toString();
        }
        return "";
    }

    public static void setVal(Object fater, Object obj, Object val) throws IllegalAccessException {
        if (obj instanceof Field) {
            ((Field) obj).set(fater, val);
        }
        if (obj instanceof Map.Entry) {
            ((Map.Entry) obj).setValue(val);
        }
    }

    public static Object getVal(Object fater, Object obj) throws IllegalAccessException {
        if (obj instanceof Field) {
            return ((Field) obj).get(fater);
        }
        if (obj instanceof Map.Entry) {
            return ((Map.Entry) obj).getValue();
        }
        return null;
    }
    public static Object getValbyName(Object fater, String name) throws Exception {

        if (fater instanceof Map) {
           return  ((Map) fater).get(name);
        }else{
            Field field= getField(fater.getClass(),name);
            if(field==null){
                throw new Exception("没有这个属性");
            }
            field.setAccessible(true);
            return field.get(fater);

        }

    }
    public static Set<Object> getFiedlds(Object object) {
        if (object instanceof Map) {
            return ((Map) object).entrySet();
        } else {
            return getAllFieldsList(object.getClass());
        }
    }

    public static Set<Object> getAllFieldsList(final Class<?> cls) {
        Set<Object> collect = getAllFields(cls).stream().map(v -> (Object) v).collect(Collectors.toSet());
        return collect;
    }
    public static Set<Field> getAllFields(final Class<?> cls) {
        final Set<Field> allFields = new HashSet<>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            for (final Field field : declaredFields) {
                field.setAccessible(true);
                allFields.add(field);
            }
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }
    public static Field getField(Class<?> cls, String fieldName) {
        Class<?> currentClass = cls;
        while (currentClass != null) {
            try {
                Field declared = currentClass.getDeclaredField(fieldName);
                if(declared==null){
                    currentClass = currentClass.getSuperclass();
                }else{
                    return declared;
                }
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }


        }
        return null;
    }
}
