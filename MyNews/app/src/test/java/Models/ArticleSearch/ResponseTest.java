package Models.ArticleSearch;

import com.zafindratafa.terence.mynews.Models.ArticleSearch.Doc;
import com.zafindratafa.terence.mynews.Models.ArticleSearch.Facets;
import com.zafindratafa.terence.mynews.Models.ArticleSearch.Meta;
import com.zafindratafa.terence.mynews.Models.ArticleSearch.Response;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by maverick on 17/02/18.
 */
public class ResponseTest {
    Response mResponse;
    @Before
    public void setUp() throws Exception {
        mResponse = new Response();
    }

    @Test
    public void getMeta() throws Exception {
        Meta meta = new Meta();
        mResponse.setMeta(meta);
        assertTrue("getMeta", mResponse.getMeta() == meta);
    }

    @Test
    public void setMeta() throws Exception {
        Meta meta = new Meta();
        mResponse.setMeta(meta);
        assertTrue("setMeta", mResponse.getMeta() == meta);
    }

    @Test
    public void getDocs() throws Exception {
        List<Doc> docList = new ArrayList<>();
        docList.add(new Doc());
        mResponse.setDocs(docList);
        assertTrue("getDocs", mResponse.getDocs() == docList);
    }

    @Test
    public void setDocs() throws Exception {
        List<Doc> docList = new ArrayList<>();
        docList.add(new Doc());
        mResponse.setDocs(docList);
        assertTrue("setDocs", mResponse.getDocs() == docList);
    }

    @Test
    public void getFacets() throws Exception {
        Facets facets = new Facets();
        mResponse.setFacets(facets);
        assertTrue("getFacets", mResponse.getFacets() == facets);
    }

    @Test
    public void setFacets() throws Exception {
        Facets facets = new Facets();
        mResponse.setFacets(facets);
        assertTrue("setFacets", mResponse.getFacets() == facets);
    }

}