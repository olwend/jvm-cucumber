package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Common_Methods {
    public static Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

    public static String getJsonAsString(JSONObject jsonObject, String key) {
        return jsonObject.getString(key);

    }

    public static void printResponseArray(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        JSONObject firstElement = jsonArray.getJSONObject(0);
        System.out.println("First element from List: " + firstElement);
        System.out.println();

        for (int counter = 0; counter < jsonArray.length(); counter++) {
            JSONObject jsonArrayNestedContents = jsonArray.getJSONObject(counter);

            System.out.println("Json array contents: " + jsonArrayNestedContents);

            String lastNameFromPosting = jsonArrayNestedContents.getString("last_name");
            String avatarFromPosting = jsonArrayNestedContents.getString("avatar");
            String emailFromPosting = jsonArrayNestedContents.getString("email");

            System.out.println();
            System.out.println("array list index: " + counter);
            System.out.println("lastName: " + lastNameFromPosting);
            System.out.println("Avatar: " + avatarFromPosting);
            System.out.println("Email: " + emailFromPosting);

        }
        System.out.println();

        JSONObject somethingFromArray1 = jsonArray.getJSONObject(1);
        System.out.println("Second item from Array: " + somethingFromArray1);

        JSONObject somethingFromArray2 = jsonArray.getJSONObject(2);
        System.out.println("Third item from Array: " + somethingFromArray2);
    }

    public static String getJsonArray(JSONObject jsonObject, String keyValue) {
        JSONArray jsonArray = jsonObject.getJSONArray(keyValue);
        String session = "";

        for (int counter = 0; counter < jsonArray.length(); counter++) {
            JSONObject jsonArrayNestedContents = jsonArray.getJSONObject(counter);

            System.out.println("Json array contents: " + jsonArrayNestedContents);

            session = jsonArrayNestedContents.getString("value");

        }
        return session;
    }

    public static void printJsonResponse(JSONObject jsonObject, String keyValue) {
        System.out.println("Full response: " + jsonObject);

        String topLevel = jsonObject.getString(keyValue);
        System.out.println("Top level object which is " + keyValue + ": " + topLevel);
    }
}
