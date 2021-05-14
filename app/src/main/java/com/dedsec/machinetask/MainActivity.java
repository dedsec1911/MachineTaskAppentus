package com.dedsec.machinetask;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dedsec.machinetask.Adapter.DataAdapter;
import com.dedsec.machinetask.Model.Data;
import com.dedsec.machinetask.ViewModel.DataViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataAdapter.DataAdapterListener {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<Data> dataList;
    private ShimmerFrameLayout shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        shimmer = findViewById(R.id.shimmer_view_container);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        DataViewModel model = ViewModelProviders.of(this).get(DataViewModel.class);

        model.getData().observe(this, dataList -> {
            adapter = new DataAdapter(MainActivity.this, dataList, this);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onGetData(int size) {
        if (size > 0) {
            shimmer.stopShimmer();
            shimmer.setVisibility(View.GONE);
        }
    }
}