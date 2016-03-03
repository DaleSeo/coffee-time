package time.coffee.util;

import org.springframework.beans.BeanUtils;

public class BeanConverter {

    public static <T> T convert(Object source, Class<T> targetType) {
        if(source == null) return null;
        T targetObject;
        try {
            targetObject = targetType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(source, targetObject);
        return targetObject;
    }
}
