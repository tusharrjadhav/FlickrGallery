package com.tushar.flickr.fragment.gallery;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tushar.flickr.R;
import com.tushar.flickr.adapter.GalleryAdapter;
import com.tushar.flickr.model.PhotoItem;

import java.util.List;

/**
 * Created by Tushar Jadhav on 31/07/17
 */

public class GalleryFrament extends Fragment implements GalleryView {

    private RecyclerView recyclerView;

    //region Fragment lifecycle method

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Creating presenter and fetching server data
        new GalleryPresenter(this).fetchServerData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gallery, container, false);
        // Inflate the layout for this fragment
        recyclerView = rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    //endregion


    //region GalleryView method

    /**
     * update UI item
     */
    public void renderUI(List<PhotoItem> photoItems) {
        GalleryAdapter mAdapter = new GalleryAdapter(getActivity().getApplicationContext(), photoItems);

        recyclerView.setAdapter(mAdapter);
    }

    //endregion
}
