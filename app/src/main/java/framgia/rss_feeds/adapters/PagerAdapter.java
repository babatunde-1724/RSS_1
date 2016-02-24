package framgia.rss_feeds.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import framgia.rss_feeds.fragments.FragmentHome;
import framgia.rss_feeds.fragments.FragmentMagazine;
import framgia.rss_feeds.fragments.FragmentSports;
import framgia.rss_feeds.fragments.FragmentTechnology;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentHome HOMETAB = new FragmentHome();
                return HOMETAB;
            case 1:
                FragmentTechnology TECHTAB = new FragmentTechnology();
                return TECHTAB;
            case 2:
                FragmentSports SPORTSTAB = new FragmentSports();
                return SPORTSTAB;
            case 3:
                FragmentMagazine MAGAZINETAB = new FragmentMagazine();
                return MAGAZINETAB;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}