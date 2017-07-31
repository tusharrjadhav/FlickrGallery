package com.tushar.flickr.fragment.gallery;

import com.tushar.flickr.model.PhotoItem;

import java.util.List;

/**
 * Created by Tushar Jadhav on 31/07/17
 */

public interface GalleryView {

    /**
     * Updating fragment UI
     *
     * @param photoItems list with Photo data to show on UI
     */
    void renderUI(List<PhotoItem> photoItems);
}
