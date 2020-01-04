package net.spring.kyao233.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author K.Yao
 * @date 2020/1/4 15:17
 */
public class JsonUtils {

    private static final ObjectMapper objectMappper = new ObjectMapper();


    /**
     * json字符串转JsonNode对象的方法
     */
    public static JsonNode str2JsonNode(String str){
        try {
            return  objectMappper.readTree(str);
        } catch (IOException e) {
            return null;
        }
    }



}
