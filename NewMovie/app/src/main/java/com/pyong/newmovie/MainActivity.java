package com.pyong.newmovie;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ItemObject> list = new ArrayList();
    private int orderindex = 0;
    private String[] mOrder = {"reserve", "point"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();
        }

        String mUrl = "https://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=" +mOrder[orderindex];

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(mUrl).get();
                Elements mElementDataSize = doc.select("ul[class=lst_detail_t1]").select("li");
                int mElementSize = mElementDataSize.size();
                for(Element elem : mElementDataSize){
                    String my_title = elem.select("li dt[class=tit] a").text();
                    String my_link = elem.select("li div[class=thumb] a").attr("href");
                    String my_imgUrl = elem.select("li div[class=thumb] a img").attr("src");
                    Element rElem = elem.select("dl[class=info_txt1] dt").next().first();
                    String my_release = rElem.select("dd").text();
                    int gindex = 0;
                    for (int i = 0; i < my_release.length(); i++) {
                        if(my_release.charAt(i) == '|') {
                            gindex = i;
                            break;
                        }
                    }
                    String my_genre = my_release.substring(0, gindex);
                    int dindex = 0;
                    int dCount = 0;
                    for (int i = 0; i < my_release.length(); i++) {
                        if(my_release.charAt(i) == '|') {
                            dindex = i;
                            dCount++;
                        }
                        if (dCount == 2) {
                            break;
                        }
                    }
                    String my_pupDate = my_release.substring(dindex+2, my_release.lastIndexOf(" "));;

                    Element rsElem = elem.select("dt[class=tit_t1]").next().first();
                    String info = rsElem.select("dd").text();
                    String my_rating = info.substring(0, 4);
                    String my_reserve = "예매율 :"+elem.select("div[class=star_t1 b_star]").text();
                    Element dElem = elem.select("dt[class=tit_t2]").next().first();
                    String my_director = dElem.select("a").text();
                    if(orderindex == 1) {
                        my_reserve = "";
                        dElem = elem.select("dl[class=info_txt1] dt[class=tit_t2]").next().first();
                        my_director = dElem.select("a").text();
                    }
                    Log.d("test : ", elem.toString());
                    list.add(new ItemObject(my_title, my_genre ,my_imgUrl, my_link, my_pupDate, my_rating, my_reserve, my_director));
                }

                Log.d("debug :", "List " + mElementDataSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            MyAdapter myAdapter = new MyAdapter(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);

            progressDialog.dismiss();
        }
    }
}
