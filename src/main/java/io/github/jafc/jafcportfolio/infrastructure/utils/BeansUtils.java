package io.github.jafc.jafcportfolio.infrastructure.utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.lang.Nullable;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class BeansUtils {

    private BeansUtils() {
    }

    public static <S, T> void copyPropertiesIgnoreNull(S source, T target) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Class<?> targetClass = target.getClass();

        for (Field field : FieldUtils.getAllFields(targetClass)) {
            String fieldName = field.getName();
            Object srcValue = getPropertyValue(target, fieldName);
            if (srcValue != null) {
                setProperty(source, fieldName, srcValue);
            }
        }
    }

    public static <S, T> void copyPropertiesIgnoreNull(S source, T target, String... ignoreProperties) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Class<?> targetClass = target.getClass();

        for (Field field : FieldUtils.getAllFields(targetClass)) {
            String fieldName = field.getName();
            Object srcValue = getPropertyValue(target, fieldName);
            if (srcValue != null && !Arrays.asList(ignoreProperties).contains(fieldName)) {
                setProperty(source, fieldName, srcValue);
            }
        }
    }

    private static Object getPropertyValue(Object object, String propertyName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, object.getClass());
        Method getter = descriptor.getReadMethod();
        return getter.invoke(object);
    }

    private static void setProperty(Object object, String propertyName, @Nullable Object value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, object.getClass());
        Method setter = descriptor.getWriteMethod();
        setter.invoke(object, value);
    }
}
