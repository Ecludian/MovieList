package com.example.aspmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView poster;
    TextView txtTitle, txtVote;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_movie_detail);
        TextView titleName = findViewById(R.id.txtTitle);
        TextView voteTxt = findViewById(R.id.txtVote);
        TextView dateTxt = findViewById(R.id.releaseDate);
        TextView detailMovie = findViewById(R.id.txtOverview);
        ImageView icon = findViewById(R.id.icon);

        String title = "";
        String vote = "";
        String date = "";
        String detail = "";
        String backdropPath = "";


        Bundle extras = getIntent().getExtras();
        if(extras != null){

            title = extras.getString("title");
            vote = extras.getString("vote");
            date = extras.getString("date");
            detail = extras.getString("detail");
            backdropPath = extras.getString("backdrop");

        }

        titleName.setText(title);
        voteTxt.setText(vote);
        detailMovie.setText(detail);
        dateTxt.setText(date);

        this.context = context;
        Picasso.with(context).load(backdropPath).fit()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(icon);

    }

}