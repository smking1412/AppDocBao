package com.example.appdocbao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Fragment1.Data {
    private static final String TAG = "MainActivity";
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPage);
        tabLayout = findViewById(R.id.tabLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        new ReadRSS().execute();
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            Intent intent = new Intent(MainActivity.this, TinDaXemActivity.class);
            startActivity(intent);
        }

        if (id == R.id.nav_timkiem) {
            Intent intentTK = new Intent(MainActivity.this, TimKiemActivity.class);
            startActivity(intentTK);
        }

        if (id == R.id.nav_dangnhap) {
                Intent intentLG = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLG);
                finish();
        }
        if (id == R.id.nav_logout){
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void senData(String link) {
        Intent intent = new Intent(getBaseContext(), ChiTietTinTuc.class);
        intent.putExtra("link", link);
        startActivity(intent);
    }



    private class ReadRSS extends AsyncTask<String, Void, Void> {
        ProgressDialog dialog;
        ArrayList<String> urls;
        ArrayList<String> titles;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {

            urls = new ArrayList<>();
            titles = new ArrayList<>();
            urls.add("https://vnexpress.net/rss/tin-moi-nhat.rss");
            urls.add("https://vnexpress.net/rss/so-hoa.rss");
            urls.add("https://vnexpress.net/rss/thoi-su.rss");
            urls.add("https://vnexpress.net/rss/giai-tri.rss");
            urls.add("https://vnexpress.net/rss/kinh-doanh.rss");
            urls.add("https://vnexpress.net/rss/the-thao.rss");
            urls.add("https://vnexpress.net/rss/phap-luat.rss");
            urls.add("https://vnexpress.net/rss/giao-duc.rss");
            urls.add("https://vnexpress.net/rss/suc-khoe.rss");
            urls.add("https://vnexpress.net/rss/doi-song.rss");
            urls.add("https://vnexpress.net/rss/du-lich.rss");
            urls.add("https://vnexpress.net/rss/khoa-hoc.rss");
            urls.add("https://vnexpress.net/rss/oto-xe-may.rss");

            titles.add("Trang chủ");
            titles.add("Số hóa");
            titles.add("Thời sự");
            titles.add("Giải trí");
            titles.add("Kinh doanh");
            titles.add("Thể thao");
            titles.add("Pháp luật");
            titles.add("Giáo dục");
            titles.add("Sức khỏe");
            titles.add("Đời sống");
            titles.add("Du lịch");
            titles.add("Khoa học");
            titles.add("Xe");

            adapter = new ViewPageAdapter(getSupportFragmentManager());


            for (int i=0; i<urls.size(); i++){
                String url = urls.get(i);
                ArrayList<ItemTinTuc> itemTinTucs = new ArrayList<>();
                try {
                    org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
                    Elements elements = doc.select("item");
                    for (int j=0; j<=10; j++){
                        Element item = elements.get(j);
                        String title = item.select("title").text();
                        String link = item.select("link").text();
                        String pubDate = item.select("pubDate").text();
                        String description = item.select("description").text();

                        Document docIMG = Jsoup.parse(description);
                        String img = "";
                        if(docIMG.select("img").isEmpty()){
                            img = "https://i-vnexpress.vnecdn.net/2020/04/30/settop72871588223888-158822416-4317-2483-1588224181_180x108.jpg";
                        }else {
                            img = docIMG.select("img").get(0).attr("src");
                        }
                        itemTinTucs.add(new ItemTinTuc(title, img, link, pubDate));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("do dai", itemTinTucs.size()+"");
                adapter.add(new Fragment1(itemTinTucs),titles.get(i));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewPager.setAdapter(adapter);
            dialog.dismiss();
        }
    }
}


