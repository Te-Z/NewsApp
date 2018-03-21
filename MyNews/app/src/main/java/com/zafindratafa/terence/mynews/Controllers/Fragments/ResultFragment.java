package com.zafindratafa.terence.mynews.Controllers.Fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zafindratafa.terence.mynews.Controllers.Activities.SearchActivity;
import com.zafindratafa.terence.mynews.Controllers.Activities.WebActivity;
import com.zafindratafa.terence.mynews.Models.ArticleSearch.ArticleSearch;
import com.zafindratafa.terence.mynews.Models.ArticleSearch.Doc;
import com.zafindratafa.terence.mynews.R;
import com.zafindratafa.terence.mynews.Utils.ItemClickSupport;
import com.zafindratafa.terence.mynews.Utils.NYTService;
import com.zafindratafa.terence.mynews.Utils.NYTStreams;
import com.zafindratafa.terence.mynews.Views.Adapters.ResultAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_result_recycler_view)
    RecyclerView mRecyclerView;

    // Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_result_swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    // FOR DATA
    private Disposable mDisposable;
    private List<Doc> mDocs = new ArrayList<>();
    private ResultAdapter mResultAdapter;

    // For data from SearchActivity
    private String QUERY;
    private String FROM_DATE;
    private String TO_DATE;
    private String CHECKBOXES;

    public static ResultFragment newInstance() {
        return (new ResultFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, result);
        this.configureResultRecyclerView();
        this.executeHttpResultRequest();
        this.configureSwipeRefreshLayout();
        this.configureOnClickRecyclerView();

        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTION
    // -----------------

    // Configure click on RecyclerView's item
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_page_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(getContext(), WebActivity.class);
                        intent.putExtra("openUrl", mDocs.get(position).getWebUrl());
                        startActivity(intent);
                    }
                });
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureResultRecyclerView(){
        this.mDocs = new ArrayList<>();
        this.mResultAdapter = new ResultAdapter(this.mDocs, Glide.with(this));
        this.mRecyclerView.setAdapter(this.mResultAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void configureSwipeRefreshLayout(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpResultRequest();
            }
        });
    }

    // -----------------
    // HTTP (RxJAVA)
    // -----------------

    // Execute our Stream
    private void executeHttpResultRequest(){
        QUERY = getArguments().getString("query");
        FROM_DATE = getArguments().getString("from_date");
        TO_DATE = getArguments().getString("to_date");
        CHECKBOXES = getArguments().getString("checkboxes");
        Log.e("httpResultRequest",QUERY+" "+FROM_DATE+" "+TO_DATE+" "+CHECKBOXES);
        // Execute the stream subscribing to Observable defined inside NYTStream
        this.mDisposable = NYTStreams.streamFetchArticleSearch(QUERY, FROM_DATE, TO_DATE, CHECKBOXES, NYTService.API_KEY).subscribeWith(new DisposableObserver<ArticleSearch>(){
            @Override
            public void onNext(ArticleSearch articleSearch) {
                Log.e("ArtSearch - onNext", "On Next TopStoriesFragment");
                // Update RecyclerView after getting results from NYT API
                updateResultUI(articleSearch.getResponse().getDocs());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ArtSearch - onError", Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("ArtSearch - onComplete", "On Complete TopStoriesFragment");

            }
        });
    }

    private void disposeWhenDestroy(){
        if(this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // -----------------
    // UPDATE UI
    // -----------------

    // Stop refreshing and clear actual list of users to add all the new ones
    public void updateResultUI(List<Doc> docs){
        mSwipeRefreshLayout.setRefreshing(false);
        mDocs.clear();
        if (docs.isEmpty()){
            showAlertDialog();
        } else {
            mDocs.addAll(docs);
            mResultAdapter.notifyDataSetChanged();
        }
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("There's no result for this search \n You will be redirect automatically in 5 seconds");
        AlertDialog dialog = builder.create();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }

}