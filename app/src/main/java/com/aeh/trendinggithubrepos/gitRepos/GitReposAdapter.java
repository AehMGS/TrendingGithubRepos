package com.aeh.trendinggithubrepos.gitRepos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aeh.trendinggithubrepos.R;
import com.aeh.trendinggithubrepos.rest.models.GitReposModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GitReposAdapter extends RecyclerView.Adapter<GitReposAdapter.ViewHolder> {

    private List<GitReposModel> repos;

    GitReposAdapter(List<GitReposModel> repos) {
        this.repos = repos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_list_row, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GitReposModel repo = repos.get(position);

        holder.title.setText(repo.getTitle());
        holder.description.setText(repo.getDescription());
        holder.username.setText(repo.getUsername());
        int ratings = Integer.parseInt(repo.getRating());
        holder.rating.setText(formatValue(ratings));
        Picasso.get().load(repo.getAvatarUrl()).into(holder.avatar);
    }

    public static String formatValue(double value) {
        int power;
        String suffix = " kmbt";
        String formattedNumber;

        NumberFormat formatter = new DecimalFormat("#,###.#");
        power = (int)StrictMath.log10(value);
        value = value/(Math.pow(10,(power/3)*3));
        formattedNumber=formatter.format(value);
        formattedNumber = formattedNumber + suffix.charAt(power/3);
        return formattedNumber.length()>4 ?  formattedNumber.replaceAll("\\.[0-9]+", "") : formattedNumber;
    }

    void updateList(List<GitReposModel> reposList) {

        this.repos.addAll(reposList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, username, rating;
        ImageView avatar;

        ViewHolder(View view){
            super(view);

            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            username = view.findViewById(R.id.name);
            rating = view.findViewById(R.id.stars_count);
            avatar = view.findViewById(R.id.avatar);
        }
    }
}
