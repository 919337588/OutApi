package out.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * JOSN工具
 *
 * @author Owen Wu
 * @email wcw@kuick.cn
 * @date 2019年6月17日
 *
 */
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper = new ObjectMapper();
        objectMapper
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"))
                .setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    public static String toJson(Object src) {
        try {
			return objectMapper.writeValueAsString(src);
		} catch (JsonProcessingException e) {
			log.error("error in toJson:" + src, e);
			return null;
		}
    }
//
//    public static <T> T fromJson(String json, Class<T> classOfT) {
//        if (StringUtils.isBlank(json)){
//            log.error("the json parameter should not be blank");
//            throw new RuntimeException("the parameter should not be blank");
//        }
//
//        try {
//        	return objectMapper.readValue(json, classOfT);
//        } catch (IOException e) {
//			log.error("error in fromJson:" + json, e);
//			return null;
//		}
//    }

	public static <T> T fromJson(String json, TypeReference<T> typeOfT) {
		if (StringUtils.isEmpty(json)){
            log.error("the json parameter should not be blank");
            throw new RuntimeException("the parameter should not be blank");
        }

		try {
        	return objectMapper.readValue(json, typeOfT);
        } catch (IOException e) {
			log.error("error in fromJson:" + json, e);
			return null;
		}
	}
    public static <K, V> Map<K, V> toMap(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Map<K, V>>() {
            });
        } catch (IOException e) {
            return null;
        }
    }
}