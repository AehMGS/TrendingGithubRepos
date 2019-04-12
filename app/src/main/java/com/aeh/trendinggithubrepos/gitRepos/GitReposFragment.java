package com.aeh.trendinggithubrepos.gitRepos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeh.trendinggithubrepos.R;

public class GitReposFragment extends Fragment {

    private RecyclerView recyclerView;
    private GitReposAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.git_repos_fragment, null, false);
        recyclerView = rootView.findViewById(R.id.repos_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //setUpRecyclerView();

        return rootView;
    }
}
