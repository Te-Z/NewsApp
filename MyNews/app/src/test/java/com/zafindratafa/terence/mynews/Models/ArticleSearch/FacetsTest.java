package com.zafindratafa.terence.mynews.Models.ArticleSearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by maverick on 17/02/18.
 */
public class FacetsTest {
    Facets mFacets;
    @Before
    public void setUp() throws Exception {
        mFacets = new Facets();
    }

    @Test
    public void getDayOfWeek() throws Exception {
        DayOfWeek dayOfWeek = new DayOfWeek();
        mFacets.setDayOfWeek(dayOfWeek);
        assertTrue("getDayOfWeek", mFacets.getDayOfWeek() == dayOfWeek);
    }

    @Test
    public void setDayOfWeek() throws Exception {
        DayOfWeek dayOfWeek1 = new DayOfWeek();
        mFacets.setDayOfWeek(dayOfWeek1);
        assertTrue("setDayOfWeek", mFacets.getDayOfWeek() == dayOfWeek1);
    }

}