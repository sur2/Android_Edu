package com.pyong.tutorial_api;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Network;
import android.net.Uri;
import android.net.sip.SipSession;
import android.net.wifi.p2p.WifiP2pManager;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.lang.annotation.Documented;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    String[] search_category_name = {"블록", "뉴스", "책", "백과사전", "영화", "카페글", "지식in"};
    String[] search_category_request_name = {"blog", "news", "book", "encyc", "movie", "cafearticle", "kin"};

    int category_position = 0;

    MenuItem item_search;
    SearchView search1;

    ListView list1;

    ArrayList<String> result_title_list;
    ArrayList<String> result_link_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = findViewById(R.id.list1);

        result_link_list = new ArrayList<>();
        result_title_list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, result_title_list
        );
        list1.setAdapter(adapter);

        ResultListListener listListener = new ResultListListener();
        list1.setOnItemClickListener(listListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        item_search = menu.findItem(R.id.item1);

        search1 = (SearchView)item_search.getActionView();
        search1.setQueryHint("검색어 입력");

        SearchViewListener listener = new SearchViewListener();
        search1.setOnQueryTextListener(listener);

        return true;
    }

    class SearchViewListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {

            NetworkThread thread = new NetworkThread(query);
            thread.start();

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }

    public class NetworkThread extends Thread {

        String keyword;

        String client_id = "Jne0DEgDjg61zDpwkNw9";
        String cliet_secret = "ygPjWV8F5P";

        public NetworkThread(String keyword) {
            this.keyword = keyword;
        }

        @Override
        public void run() {
            try {

                result_title_list.clear();
                result_link_list.clear();

                keyword = URLEncoder.encode("바람", "UTF-8");

                String site = "https://openapi.naver.com/v1/search/"+search_category_request_name[category_position]+".xml?query="+keyword;
                URL url = new URL(site);

                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", client_id);
                con.setRequestProperty("X-Naver-Client-Secret", cliet_secret);

                InputStream is = con.getInputStream();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(is);

                Element root = document.getDocumentElement();

                NodeList item_list = root.getElementsByTagName("item");

                for (int i = 0; i < item_list.getLength(); i++) {
                    Element item_tag = (Element)item_list.item(i);

                    NodeList title_list = item_tag.getElementsByTagName("title");
                    NodeList link_list = item_tag.getElementsByTagName("link");

                    Element title_tag = (Element)title_list.item(0);
                    Element link_tag = (Element)link_list.item(0);

                    String title = title_tag.getTextContent();
                    String link = link_tag.getTextContent();

                    result_title_list.add(title);
                    result_link_list.add(link);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> adapter = (ArrayAdapter<String>)list1.getAdapter();
                        adapter.notifyDataSetChanged();
                    }
                });

            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    class ResultListListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String site = result_link_list.get(i);

            Uri uri = Uri.parse(site);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        int id = item_search.getItemId();
        switch (id) {
            case R.id.item1 :
                break;
            case R.id.item2 :
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("검색 카테고리");

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, search_category_request_name);
                DialogListener listener = new DialogListener();
                builder.setAdapter(adapter, listener);
                builder.setNegativeButton("취소", null);
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item_search);
    }

    class DialogListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            category_position = which;
        }
    }
}
