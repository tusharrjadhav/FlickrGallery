package com.tushar.flickr.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tushar.flickr.R;
import com.tushar.flickr.adapter.GalleryAdapter;

import java.util.ArrayList;

/**
 * Created by Tushar Jadhav on 30/07/17
 */

public class MainActivity extends AppCompatActivity {

    private ArrayList<Drawable> images;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        images = new ArrayList<>();
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
        images.add(ContextCompat.getDrawable(this, R.drawable.tushar));
    }

    public void onResume() {
        super.onResume();
        mAdapter = new GalleryAdapter(getApplicationContext(), images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
