package com.zafindratafa.terence.mynews.Models.ArticleSearch;

/**
 * Created by maverick on 17/02/18.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleSearch {

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}