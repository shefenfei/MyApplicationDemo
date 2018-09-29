package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.Student;
import com.fenfei.myapplicationdemo.views.ChatBottomLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WechatActivity extends AppCompatActivity {

    private ChatBottomLayout mChatBottomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);


        String bane = "asasas";
        Log.e("......", "onCreate: " + bane);

//        () findViewById(R.id.);
        int aa = 12;

        String json = "{\n" +
                "                \"student\": null,\n" +
                "                \"baseBodyConstitutionItem\": {\n" +
                "                    \"itemName\": \"总分\",\n" +
                "                    \"icon\": \"http://oq6t96i6t.bkt.clouddn.com/image/body/TOTAL-SCORE.png\"\n" +
                "                },\n" +
                "                \"point\": null,\n" +
                "                \"isSuccess\": false,\n" +
                "                \"score\": 81.8,\n" +
                "                \"year\": \"2017\",\n" +
                "                \"level\": null\n" +
                "            }";


//        try {
//            JSONObject jsobj = new JSONObject(json);
//            JSONArray jarray = jsobj.getJSONArray("body");
//            for (int i = 0; i < jarray.length(); i++) {
//                jarray.get(i);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        Gson gson = new Gson();
        Student student = gson.fromJson(json, Student.class);
        Log.e("STUDENT", "onCreate: " + student);

        Log.e("Result", "onCreate: " + student.getLevel());


        Student s1 = new Student();
        s1.setStudent(null);
        s1.setYear("2017-07-20");
        s1.setLevel(0);

        float a = 1.1f;

        Log.e("convert", "onCreate: " + a);


        String stuJson = gson.toJson(s1);
        Log.e("stu json", "onCreate: " + stuJson);


        /*
        String responseJson = new String(responseBody); // from the service endpoint


        // which format has the response of the server
        final TypeToken<ServiceResponse> requestListTypeToken = new TypeToken<ServiceResponse>() {};

        // adding all different container classes with their flag
        final RuntimeTypeAdapterFactory<AbstractContainer> typeFactory = RuntimeTypeAdapterFactory
                .of(Animal.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(Dog.class, "dog") // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
                .registerSubtype(Cat.class, "cat");

        // add the polymorphic specialization
        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();

        // do the mapping
        final ServiceResponse deserializedRequestList = gson.fromJson(responseJson, requestListTypeToken.getType() );

        */


        String str = "{code\": 200,\n" +
                "  \"desc\": \"success\",\n" +
                "  \"body\": {\n" +
                "    \"year\": [\n" +
                "      \"2017\"\n" +
                "    ]\n" +
                "  }\n" +
                "}";


        JsonParser parse = new JsonParser();
        JsonObject jsonObject = parse.parse(str).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonObject("body").getAsJsonArray("year");
        for (int i=0; i< jsonArray.size() ; i++) {
            String s = jsonArray.get(i).getAsString();
            Log.e("Result", "onCreate: " + s );
        }


        /*
        Type type = new TypeToken<ArrayList<Student>>() {}.getType();
        List<Student> students = gson.fromJson(json, type);
        */
    }
}
