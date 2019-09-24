package ru.scudstir.chucknorrisjokes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeViewHolder> {
    private LayoutInflater inflater;
    private List<Value> jokesList;

    public JokeAdapter(Context ctx, List<Value> jokesList) {
        inflater = LayoutInflater.from(ctx);
        this.jokesList = jokesList;
    }

    public void setJokesList(List<Value> jokesList) {
        this.jokesList = jokesList;
    }

    @NonNull
    @Override
    public JokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tv_for_recycle_view, parent, false);
        JokeViewHolder holder = new JokeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JokeViewHolder holder, int position) {
        holder.listJokeView.setText(jokesList.get(position).getJoke());

    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }


    class JokeViewHolder extends RecyclerView.ViewHolder {
        final TextView listJokeView;

        JokeViewHolder(@NonNull View itemView) {
            super(itemView);
            listJokeView = itemView.findViewById(R.id.tv_joke);
        }

    }
}
