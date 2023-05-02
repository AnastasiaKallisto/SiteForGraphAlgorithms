package graph.alghoritms.dto;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import graph.alghoritms.error.ErrorCode;
import graph.alghoritms.error.ServerException;

public class ValidationUtils {
    public static <T> T getClassInstanceFromJson(Gson gson, String jsonString, Class<T> clazz) throws ServerException {
        try {
            return gson.fromJson(jsonString, clazz);
        } catch (JsonSyntaxException e) {
            throw new ServerException(ErrorCode.INCORRECT_JSON);
        }
    }


}
