package com.tushar.flickr.network;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tushar.flickr.model.JsonFlickrFeed;
import com.tushar.flickr.model.PhotoItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Tushar Jadhav on 31/07/17
 */

public class FlickrConnection {

    private static final String TAG = FlickrConnection.class.getSimpleName();

    /**
     * End point URL for Flickr
     */
    private static final String MAIN_URL =
            "https://api.flickr.com/services/feeds/photos_public.gne";


    private static final String PHOTO_GET_IDS = "ids";
    private static final String FORMAT = "format";
    private static final String LANG = "lang";

    /**
     * Open URL connection and read data
     *
     * @param urlSpec to fetch data
     * @return url data in byte[]
     * @throws IOException throwing exception while Network connection
     */
    private byte[] getUrlBytes(String urlSpec) throws IOException {
        Log.e(TAG, "Download from URL: " + urlSpec);

        URL url = new URL(urlSpec);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        byte[] outBytes = null;

        try {
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int bytesRead;
                byte[] buffer = new byte[1024];

                // Call read repeatedly until the connection runs out of data
                // InputStream will yield bytes as they are available
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                outBytes = out.toByteArray();
            }
        } catch (SocketTimeoutException ste) {
            Log.e(TAG, "Socket timeout: ", ste);
        } catch (UnknownServiceException use) {
            Log.e(TAG, "Unknown service exception: ", use);
        } catch (IOException ioe) {
            Log.e(TAG, "IO Exception: ", ioe);
        } finally {
            connection.disconnect();
        }

        return outBytes;
    }

    /**
     * Building url for fetching all ids in Json format
     *
     * @return list of PhotoItem
     */
    public ArrayList<PhotoItem> fetchPhotoItems() {
        String url = Uri.parse(MAIN_URL).buildUpon()
                .appendQueryParameter(PHOTO_GET_IDS, "")
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(LANG, "en-us")
                .build().toString();
        return downloadPhotosData(url);
    }

    /**
     * Fetch data from url in Json format and parse it using Gson
     *
     * @param url server url for fetching data
     * @return list of PhotoItem
     */
    private ArrayList<PhotoItem> downloadPhotosData(String url) {
        ArrayList<PhotoItem> items = new ArrayList<>();

        try {
            String jsonString = new String(getUrlBytes(url));
            Log.i(TAG, "PhotosData Json: " + jsonString);

            jsonString = jsonString.substring("jsonFlickrFeed(".length(), jsonString.length() - 1);

            Gson gson = new GsonBuilder().create();
            JsonFlickrFeed flickrFeed = gson.fromJson(jsonString, JsonFlickrFeed.class);
            items.addAll(flickrFeed.items);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch PhotosData", ioe);
        }
        return items;
    }

}
