package com.example.testniw.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.testniw.R;
import com.example.testniw.activity.adapter.DataAdapter;
import com.example.testniw.activity.adapter.RealmAdapter;
import com.example.testniw.activity.database.MyHelper;
import com.example.testniw.activity.model.Example;
import com.example.testniw.activity.model.Story;
import com.example.testniw.activity.presenter.LoadDataPresenter;
import com.example.testniw.activity.service.LoaderService;
import com.example.testniw.activity.view.LoadDataView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements LoadDataView, SearchView.OnQueryTextListener {
    public static final String BASE_URL = "https://api.github.com/";
    int page = 1;
    private LoadDataPresenter loadDataPresenter;
    List<Example> exampleList;
    private RecyclerView rvList;
    DataAdapter dataAdapter;
    LinearLayoutManager linearLayoutManager;
    Realm realm;
    MyHelper myHelper;
    RealmAdapter realmAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        realm = Realm.getDefaultInstance();
        exampleList = new ArrayList<>();
        loadDataPresenter = new LoadDataPresenter(this, page);
        boolean status = isNetworkOnline();
        if (status) {
            loadDataPresenter.showData();
        } else {
            myHelper = new MyHelper(realm);
            myHelper.selectFromDB();
            Log.e("Size",myHelper.selectFromDB().size()+"");
            realmAdapter = new RealmAdapter(this, myHelper.selectFromDB());
            linearLayoutManager = new LinearLayoutManager(this);
            rvList.setAdapter(realmAdapter);
            rvList.setLayoutManager(linearLayoutManager);
        }
    }

    public boolean isNetworkOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getNetworkInfo(0);
        return netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    private void init() {
        rvList = (RecyclerView) findViewById(R.id.rvList);

    }

    @Override
    public void loadData(int page) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        LoaderService loaderService = retrofit.create(LoaderService.class);
        loaderService.getAll(page, 10).enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                List<Example> newlist = response.body();
                exampleList.addAll(newlist);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        List<Story> stories=new ArrayList<>();
                        for (int i = 0; i < newlist.size(); i++) {
                            Log.e("III",i+"");
                            stories = Arrays.asList(new Story(newlist.get(i).getName()));
                            realm.copyToRealmOrUpdate(stories);
                        }
                    }
                });
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.e("Fail", t.getMessage());
            }
        });
    }

    @Override
    public void showData() {
        exampleList = new ArrayList<>();
        dataAdapter = new DataAdapter(MainActivity.this, exampleList);
        rvList.setAdapter(dataAdapter);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvList.setLayoutManager(linearLayoutManager);
        loadData(page);

        rvList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                MainActivity.this.page++;
                Log.e("Page", MainActivity.this.page + "");
                loadData(MainActivity.this.page);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(MainActivity.this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText.toLowerCase().trim();
        List<Example> newList = new ArrayList<>();
        for (Example example : exampleList) {
            if (example.getName().contains(text)) {
                newList.add(example);
            }
        }
        dataAdapter.updateList(newList);
        return false;
    }
}