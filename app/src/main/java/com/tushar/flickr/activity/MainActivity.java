package com.tushar.flickr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tushar.flickr.R;
import com.tushar.flickr.fragment.gallery.GalleryFrament;

/**
 * Created by Tushar Jadhav on 30/07/17
 */

public class MainActivity extends AppCompatActivity {

    //region Activity lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getFragmentManager().beginTransaction().add(R.id.container, new GalleryFrament()).commit();
    }
    //endregion

}
