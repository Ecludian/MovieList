package com.example.aspmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterMovieList;
import model.Movies;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowMovies extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final List<Movies> viewItems = new ArrayList<>();

    @BindView(R.id.lst_movies)
    RecyclerView lstMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_movies);

        ButterKnife.bind(this);

        lstMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstMovies.setHasFixedSize(true);
        lstMovies.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AdapterMovieList adapterMovieList = new AdapterMovieList(this, viewItems);
        lstMovies.setAdapter(adapterMovieList);
        
        addItemFromJSON();

    }

    private void addItemFromJSON() {
        try {
            String jsonDataString = readJSONdataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject itemObj = jsonArray.getJSONObject(i);

                Movies movies = new Movies();
                movies.setTitle(itemObj.getString("title"));
                movies.setId(itemObj.getInt("id"));
                movies.setVoteAverage(itemObj.getDouble("vote_average"));
                movies.setPosterPath(itemObj.getString("poster_path"));
                movies.setBackdropPath(itemObj.getString("backdrop_path"));
                movies.setOverview(itemObj.getString("overview"));
                movies.setReleaseDate(itemObj.getString("release_date"));

                viewItems.add(movies);

            }
        }catch (IOException | JSONException e){
            Log.d(TAG, "addItemFromJSON", e);
        }

    }

    private String readJSONdataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.movielist);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while((jsonString = bufferedReader.readLine()) != null){
                builder.append(jsonString);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
        return new String(builder);
    }

}