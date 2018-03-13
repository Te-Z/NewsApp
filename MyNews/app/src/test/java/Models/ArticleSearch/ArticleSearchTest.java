package Models.ArticleSearch;

import com.zafindratafa.terence.mynews.Models.ArticleSearch.ArticleSearch;
import com.zafindratafa.terence.mynews.Models.ArticleSearch.Response;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by maverick on 17/02/18.
 */
public class ArticleSearchTest {
    ArticleSearch mArticleSearch;
    Response mResponse, mResponse1;

    @Before
    public void setUp() throws Exception {
        mArticleSearch = new ArticleSearch();
        mResponse = new Response();
        mResponse1 = new Response();
    }

    @Test
    public void getResponse() throws Exception {
        mArticleSearch.setResponse(mResponse);

        assertTrue("getResponse", mArticleSearch.getResponse() == mResponse);
    }

    @Test
    public void setResponse() throws Exception {
        mArticleSearch.setResponse(mResponse1);

        assertTrue("setResponse", mArticleSearch.getResponse() == mResponse1);
    }

    @Test
    public void getCopyright() throws Exception {
        mArticleSearch.setCopyright("getCopyright");
        assertTrue("getCopyright", mArticleSearch.getCopyright() == "getCopyright");
    }

    @Test
    public void setCopyright() throws Exception {
        mArticleSearch.setCopyright("setCopyright");
        assertTrue("setCopyright", mArticleSearch.getCopyright() == "setCopyright");
    }

}