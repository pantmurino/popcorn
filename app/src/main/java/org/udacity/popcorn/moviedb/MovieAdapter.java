package org.udacity.popcorn.moviedb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.udacity.popcorn.R;
import org.udacity.popcorn.utility.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> mMovies;
    private final OnPosterClickListener mListener;

    private static final String MOVIES_STATE = "MoviesState";

    public interface OnPosterClickListener {
        void onClick(Movie movie);
    }

    public MovieAdapter(OnPosterClickListener listener) {
        mListener = listener;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener
    {
        private final ImageView mImageView;

        MovieViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.img_movie_thumbnail);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            mListener.onClick(mMovies.get(pos));
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_preview, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        ImageLoader.fetchPosterIntoView(mMovies.get(i).poster_path, movieViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void setMovies(Movies movies) {
        mMovies = movies.list;
        notifyDataSetChanged();
    }

    public void setMovies(List<Movie> movies) {
        mMovies = new ArrayList<>(movies);
        notifyDataSetChanged();
    }

    public void resetMovies() {
        mMovies = null;
        notifyDataSetChanged();
    }

    public boolean hasSavedState(Bundle state) {
        return state.containsKey(MOVIES_STATE);
    }

    public void saveStateTo(Bundle state) {
        state.putParcelableArrayList(MOVIES_STATE, mMovies);
    }

    public void restoreStateFrom(Bundle state) {
        mMovies = state.getParcelableArrayList(MOVIES_STATE);
        notifyDataSetChanged();
    }
}
