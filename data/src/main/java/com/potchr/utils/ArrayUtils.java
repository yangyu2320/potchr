package com.potchr.utils;

public interface ArrayUtils {

    /**
     * 是否为空数组。
     * NULL,长度为0,所有单元都为NULL
     *
     * @param array 需要判断的数组
     * @return 是否为空数组
     */
    static boolean isBlank(Object[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        boolean isBlank = true;
        for (Object o : array) {
            if (o != null) {
                isBlank = false;
                break;
            }
        }
        return isBlank;
    }
}
