package com.daitu_liang.study.mytest.http.netapi;


import com.daitu_liang.study.mytest.util.GetSign;
import com.daitu_liang.study.mytest.util.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;




public class ModelUtil {

    private static final String TAG ="ModelUtil" ;

    public static HashMap<String, String> getRequestParamsFromObject(Object obj) {
        HashMap<String, String> params = new  HashMap<String, String>();

        HashMap<String, String> map = new HashMap<String, String>();
        if (obj != null) {

            Class classType = obj.getClass();

            Field[] parentFields = classType.getFields();
            Field[] fields = classType.getDeclaredFields();
            Field[] resFields =  concat(parentFields, fields);
            if (resFields != null) {

                int length = resFields.length;
                for (int i = 0; i < length; i++) {
                    Field field = resFields[i];
                    String fieldName = field.getName();
                    if(fieldName.equals("saasRequest")) {
                        continue;
                    }
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);

                    try {
                        Method getMethod;
                        try {
                            getMethod = classType.getMethod(getMethodName, new Class[]{});

                        } catch (NoSuchMethodException e1) {
                            e1.printStackTrace();
                            continue;
                        }
                        Object value = getMethod.invoke(obj, new Object[]{});
                        Log.getLogger("").i(TAG,fieldName+"="+value+"------"+i);
                        if(!(value instanceof File)){
                            map.put(fieldName, (String) value);
                        }


                        if (value instanceof File) {
                            try {
//                                params.put(fieldName, (File) value);
//                                map.put(fieldName, (File)value);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if(value instanceof HashMap<?, ?>) {    //处理多文件上传
                            try {
                                HashMap<String, File> data = (HashMap<String, File>) value;
                                for(String key : data.keySet()) {
                                    Log.getLogger("").i(TAG, "------fileName-----" + key +
                                            " ---filePath--------" + data.get(key).getAbsolutePath());
//                                    params.put(key, data.get(key));
//                                    Log.i(TAG,"HashMap="+data.get(key));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            params.put(fieldName, value != null ? String.valueOf(value) : (String) null);

                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            params.put("sign", GetSign.giveSign(map));
        }

        map.clear();
        return params;
    }

    /**
     * 合并2个数组
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
