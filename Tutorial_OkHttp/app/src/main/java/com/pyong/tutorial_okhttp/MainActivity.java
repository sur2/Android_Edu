
package com.pyong.tutorial_okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask();
        httpAsyncTask.execute("https://goo.gl/eIXu9l");
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
    // 네트워크를 비동기로 받음
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            // 파라메터로 주소를 받음

            List<Weather> weatherList = new ArrayList<>();
            String result = null;
            String strURL = params[0];

            try {
                // 데이터를 요청하기 위한 객체 생성
                Request request = new Request.Builder().url(strURL).build();

                // 데이터를 응답받는 객체 생성
                Response response = client.newCall(request ).execute();

                // 응답받은 데이터를 JSONArray jsonArray 객체에 담는다.
                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    // JSONArray jsonArray에 담겨있는 JSONObject jsonObject 객체는 데이터를 가지고 있다.
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    // 속성에 따른 데이터 값
                    String country = jsonObject.getString("country");
                    String weather = jsonObject.getString("weather");
                    String temperature = jsonObject.getString("temperature");
                    // Weather weather 객체에 데이터를 전달
                    Weather w = new Weather(country, weather, temperature);
                    weatherList.add(w);
                }
                Log.d(TAG, "doInBackground : "+weatherList.toString());
                result = weatherList.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            // String doInBackground()의 반환값이 전달인수 s로 넘겨진다.
            super.onPostExecute(s);
            if(s != null) {
                Log.d(TAG, s);
            }
            TextView textView = findViewById(R.id.result);
            textView.setText(s);
        }
    }
}
