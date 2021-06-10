package com.example.employeelist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    List<Employee> employees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.list);
        ConnectAndParseJson jsonObj = new ConnectAndParseJson();
        jsonObj.execute();
        try {
            employees = jsonObj.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(employees, Employee::compareTo);
        EmployeeAdapter adapter = new EmployeeAdapter(this,employees);
        recyclerView.setAdapter(adapter);
    }
    static class ConnectAndParseJson extends AsyncTask<Void, Void, List<Employee>> {
        public final String JSON_URL = "http://www.mocky.io/v2/5ddcd3673400005800eae483";

        @Override
        protected List<Employee> doInBackground(Void... voids) {
            URL url = null;
            HttpURLConnection connection = null;
            InputStream inputJson = null;
            try {
                url = new URL(JSON_URL);
                connection = (HttpURLConnection) url.openConnection();
                inputJson = new BufferedInputStream(connection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //For API <24
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputJson));
                while ((line = reader.readLine())!=null){
                    sb.append(line+"\n");
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (inputJson != null) {
                        inputJson.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String jsonString = sb.toString();

            // Only for API >=24
            // String jsonString = new BufferedReader(new InputStreamReader(inputJson)).lines().collect(Collectors.joining("\n"));

            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Company>>(){}.getType();
            Map<String, Company> myMap = gson.fromJson(jsonString,type);
            return myMap.get("company").getEmployees();
        }

        @Override
        protected void onPostExecute(List<Employee> employees) {
            super.onPostExecute(employees);
        }

    }

}