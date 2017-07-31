package com.tushar.flickr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tushar.flickr.R;
import com.tushar.flickr.adapter.GalleryAdapter;
import com.tushar.flickr.model.PhotoItem;
import com.tushar.flickr.network.FlickrConnection;
import com.tushar.flickr.utility.ExecutionHelper;

import java.util.ArrayList;

/**
 * Created by Tushar Jadhav on 30/07/17
 */

public class MainActivity extends AppCompatActivity {

    private ArrayList<PhotoItem> photoItems;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;

    //region Activity lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        photoItems = new ArrayList<>();

        fetchServerData();
    }
    //endregion

    //region custom method

    /**
     * Get the Photo data from Server on separate thread and handling data on Main thread
     */
    private void fetchServerData() {
        ExecutionHelper.runNonMainThreadSafe(new Runnable() {
            @Override
            public void run() {
                photoItems = new FlickrConnection().fetchPhotoItems();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                    }
                });
            }
        });
    }

    /**
     * update UI item
     */
    private void updateUI() {
        mAdapter = new GalleryAdapter(getApplicationContext(), photoItems);

        recyclerView.setAdapter(mAdapter);
    }

    //endregion
}
