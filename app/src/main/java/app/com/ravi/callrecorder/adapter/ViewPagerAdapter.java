package app.com.ravi.callrecorder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.com.ravi.callrecorder.fragment.FragInbox;
import app.com.ravi.callrecorder.fragment.FragSaved;

/**
 * Created by NetSupport on 14-08-2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FragInbox();
        } else if (position == 1) {
            fragment = new FragSaved();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "INBOX";
        } else if (position == 1) {
            title = "SAVED";
        }
        return title;
    }
}
