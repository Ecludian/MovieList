package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aspmovie.MovieDetailActivity;
import com.example.aspmovie.R;
import com.example.aspmovie.ShowMovies;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Movies;

public class AdapterMovieList extends RecyclerView.Adapter<AdapterMovieList.ViewHolder> {

    Context context;
    List<Movies> moviesList;


    public AdapterMovieList(Context context, List<Movies> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.moviesitemslayout, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovieList.ViewHolder holder, int position) {
        /** i don't know why
         * i shouldn't have to wonder why
         * but this app somehow can still running perfectly even with project error
         */

        String posterUrl = "https://themoviedb.org/t/p/w500" + moviesList.get(position).getPosterPath();
        String backdropUrl = "https://themoviedb.org/t/p/w500" + moviesList.get(position).getBackdropPath();
        Picasso.with(context).load(posterUrl).fit()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.poster);




        String id = moviesList.get(position).getId().toString();
        holder.txtTitle.setText(moviesList.get(position).getTitle());
        holder.txtVote.setText(moviesList.get(position).getVoteAverage().toString());
        holder.txtDate.setText(moviesList.get(position).getReleaseDate());

        //holder.txtOverview.setText(moviesList.get(position).getOverview());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("title", moviesList.get(position).getTitle());
                intent.putExtra("vote", moviesList.get(position).getVoteAverage().toString());
                intent.putExtra("detail", moviesList.get(position).getOverview());
                intent.putExtra("backdrop", backdropUrl);
                intent.putExtra("date", moviesList.get(position).getReleaseDate());
                //intent.putExtra("backdrop", backdropUrl);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount(){
        return moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster, backdrop;
        TextView txtTitle, txtVote, txtDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.icon);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtVote = itemView.findViewById(R.id.txtVote);
            txtDate = itemView.findViewById(R.id.releaseDate);

            //txtOverview = itemView.findViewById(R.id.txtOverview);
        }
    }

}


