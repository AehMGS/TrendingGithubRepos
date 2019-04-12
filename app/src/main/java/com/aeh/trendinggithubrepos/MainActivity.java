package com.aeh.trendinggithubrepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aeh.trendinggithubrepos.gitRepos.GitReposFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitReposFragment fragment = new GitReposFragment();
        getSupportFragmentManager().beginTransaction()
                .add(
                        R.id.container,
                        fragment,
                        fragment.getClass().getSimpleName()
                )
                .addToBackStack(null)
                .commit();
    }
}
