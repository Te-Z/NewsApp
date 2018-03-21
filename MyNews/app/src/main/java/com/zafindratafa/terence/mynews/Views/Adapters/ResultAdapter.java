package com.zafindratafa.terence.mynews.Views.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.zafindratafa.terence.mynews.Models.ArticleSearch.Doc;
import com.zafindratafa.terence.mynews.Models.MostPopular.MostPopularResult;
import com.zafindratafa.terence.mynews.R;
import com.zafindratafa.terence.mynews.Views.ViewHolders.MostPopularViewHolder;
import com.zafindratafa.terence.mynews.Views.ViewHolders.ResultViewHolder;

import java.util.List;

/**
 * Created by maverick on 13/03/18.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    // FOR DATA
    private List<Doc> mDocs;

    private RequestManager glide;

    public ResultAdapter(List<Doc> docs, RequestManager glide) {
        mDocs = docs;
        this.glide = glide;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view holder and inflating its xml layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_page_item, parent, false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.updateWithResult(this.mDocs.get(position), this.glide);
    }

    @Override
    public int getItemCount() {
        return this.mDocs.size();
    }
}
