package com.example.javaassign2;

//public class FormDataUtils {
//}

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class FormDataUtils {
    private static final String TAG = "FormDataUtils";

    public static String convertToJson(List<FormData> formDataList) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<FormData>>() {}.getType();
        return gson.toJson(formDataList, type);
    }

    public static List<FormData> convertFromJson(String jsonData) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<FormData>>() {}.getType();
        try {
            return gson.fromJson(jsonData, type);
        } catch (Exception e) {
            Log.e(TAG, "Error converting JSON to FormData: " + e.getMessage());
            return null;
        }
    }
}
