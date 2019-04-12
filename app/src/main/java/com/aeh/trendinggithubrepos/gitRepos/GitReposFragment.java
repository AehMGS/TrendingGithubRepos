package com.aeh.trendinggithubrepos.gitRepos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeh.trendinggithubrepos.R;
import com.aeh.trendinggithubrepos.rest.ApiUtils;
import com.aeh.trendinggithubrepos.rest.RestInterface;
import com.aeh.trendinggithubrepos.rest.models.GitReposModel;
import com.aeh.trendinggithubrepos.utils.DateUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.paginate.Paginate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aeh.trendinggithubrepos.rest.ApiUtils.REST_STATUS_SUCCESS;

public class GitReposFragment extends Fragment implements Paginate.Callbacks{

    private RecyclerView recyclerView;
    private GitReposAdapter adapter;

    private List<GitReposModel> reposList = new ArrayList<>();
    private int page = 0;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.git_repos_fragment, null, false);

        recyclerView = rootView.findViewById(R.id.repos_recyclerView);
        setUpRecyclerView();
        getRepos();

        return rootView;
    }

    private void setUpRecyclerView() {

        adapter = new GitReposAdapter(reposList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        Paginate.with(recyclerView, this)
                .setLoadingTriggerThreshold(3)
                .addLoadingListItem(true)
                .build();
    }

    private void getRepos() {

        String url = "/search/repositories?q=created:>" + DateUtils.getFormattedDateOneMonthAgo() + "&sort=stars&order=desc&page=" + page;

        RestInterface restInterface = ApiUtils.getAPIService();
        restInterface.getRepos(url).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                if (response.code() == REST_STATUS_SUCCESS && response.body() != null) {

                    isLoading = false;
                    parseJson(response.body());
                    updateList();

                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    private void parseJson(JsonElement response){

        try {

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray items = jsonObject.getJSONArray("items");
            for(int i = 0; i < items.length(); i++){

                GitReposModel gitReposModel = new GitReposModel();
                gitReposModel.setTitle(items.getJSONObject(i).getString("name"));
                gitReposModel.setDescription(items.getJSONObject(i).getString("description"));
                gitReposModel.setAvatarUrl(items.getJSONObject(i).getJSONObject("owner").getString("avatar_url"));
                gitReposModel.setUsername(items.getJSONObject(i).getJSONObject("owner").getString("login"));
                gitReposModel.setRating(items.getJSONObject(i).getString("stargazers_count"));
                reposList.add(gitReposModel);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void updateList(){

        adapter.updateList(reposList);

    }

    @Override
    public void onLoadMore() {

        isLoading = true;
        page++;
        getRepos();

    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return false;
    }
}
