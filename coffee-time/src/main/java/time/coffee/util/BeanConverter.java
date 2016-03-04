package time.coffee.util;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

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

    public interface ListTransformListener {
        void copyDone(Object source, Object target);
    }

    public static <T> List<T> listTransform(List<?> source, Class<T> targetType) {
        return listTransform(source, targetType, null);
    }

    /**
     * Bean 복사 완료 이후 추가 작업
     * - 변수명이 상이하여 복사 되지 않았을 경우 수동으로 복사해야 할 경우 사용
     * - 복사가 완료된 객체에 특정값을 추가하고 싶을 경우 사용
     *
     * 사용법 :
     *  BeanConverter.listTransform(source, MemberDto.class, (source, target) -> {
     *      // Copy 완료 이후 추가 작업
     *      //
     *  });
     * @param source
     * @param targetType
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> List<T> listTransform(List<?> source, Class<T> targetType, ListTransformListener callback) {
        return Lists.transform(source, o -> {
            T targetObject = convert(o, targetType);
            if (callback != null) callback.copyDone(o, targetObject);
            return targetObject;
        });
    }

}
