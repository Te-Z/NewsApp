package com.zafindratafa.terence.mynews.Utils;

import com.zafindratafa.terence.mynews.Models.ArticleSearch.ArticleSearch;
import com.zafindratafa.terence.mynews.Models.MostPopular.MostPopular;
import com.zafindratafa.terence.mynews.Models.TopStories.TopStories;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by maverick on 12/03/18.
 */

public interface NYTService {

    public final static String API_KEY = "45f44030dcab4a4cacdefd4dd7418bfa";

    @GET("topstories/v2/{section}.json?api-key="+API_KEY)
    Observable<TopStories> getTopStories(@Path("section") String section);

    @GET("mostpopular/v2/mostviewed/{section}/1.json?api-key="+API_KEY)
    Observable<MostPopular> getMostPopular(@Path("section") String section);

    @GET("search/v2/articlesearch.json")
    Observable<ArticleSearch> getArticleSearch(@Query(value = "q", encoded = true) String query,
                                               @Query("begin_date") String beginDate,
                                               @Query("end_date") String endDate,
                                               @Query("fq") String newsDesk,
                                               @Query("api-key") String apiKey);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}