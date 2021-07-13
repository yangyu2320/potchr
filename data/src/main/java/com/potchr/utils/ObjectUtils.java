package com.potchr.utils;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;

public interface ObjectUtils {

    /**
     * 实现{@link Serializable}接口类实例的深拷贝
     *
     * @param object 实现{@link Serializable}接口的类实例
     * @return 拷贝结果对象
     */
    static <S> S clone(S object) {
        if (object == null) {
            return null;
        }
        if (object.getClass().isArray()) {
            if (ArrayUtils.isBlank((Object[]) object)) {
                return (S) Array.newInstance(object.getClass().getComponentType(), ((Object[]) object).length);
            }
        }
        if (object instanceof Collection && ((Collection<?>) object).isEmpty()) {
            try {
                return (S) object.getClass().getConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (object instanceof Serializable serializable) {
            return (S) SerializationUtils.clone(serializable);
        }
        throw new IllegalArgumentException();
    }
}
