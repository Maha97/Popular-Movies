package com.example.android.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Soham on 27-May-17.
 */

public class NetworkUtils {
    public final static String BASE_URL = "http://api.themoviedb.org/3/movie/popular";
    //TODO insert API Key below
    private final static String API_KEY = "INSERT API KEY HERE";
    public final static String QUERY_PARAM = "api_key";

    public static URL buildURL() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.e("NetworksUtils", e.toString());
        }
        return url;
    }

    public static String getResponseFromServer(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        try{
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            if(scanner.hasNext()){
                return scanner.next();
            }
            else
            {
                return null;
            }

        }
        finally {
            httpURLConnection.disconnect();
        }
    }
}