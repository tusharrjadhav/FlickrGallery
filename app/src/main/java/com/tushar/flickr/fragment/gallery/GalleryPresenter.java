package com.tushar.flickr.fragment.gallery;

import com.tushar.flickr.model.PhotoItem;
import com.tushar.flickr.network.FlickrConnection;
import com.tushar.flickr.utility.ExecutionHelper;

import java.util.List;

/**
 * Created by Tushar Jadhav on 31/07/17
 */

public class GalleryPresenter {

    private GalleryView view;

    public GalleryPresenter(GalleryView view) {
        this.view = view;
    }

    /**
     * Get the Photo data from Server on separate thread and handling data on Main thread
     */
    public void fetchServerData() {
        ExecutionHelper.runNonMainThreadSafe(new Runnable() {
            @Override
            public void run() {
                final List<PhotoItem> photoItems = new FlickrConnection().fetchPhotoItems();
                ExecutionHelper.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        view.renderUI(photoItems);
                        //TODO: To use RxJava
                    }
                });
            }
        });
    }
}
