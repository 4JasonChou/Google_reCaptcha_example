package com.foyatech.foya.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.foyatech.foya.R;
import com.foyatech.foya.presenter.presenterInterface.IFragmentGoogleCalendarPresenter;
import com.foyatech.foya.tabsFragment.Fragment_Tabs_GoogleCalendar_Setting;
import com.foyatech.foya.tabsFragment.Fragment_Tabs_GoogleCalendar_View;
import com.foyatech.foya.view.viewInterface.*;
import com.foyatech.foyalibrary.adapter.TabFragmentAdapter;

/**
 * Created by Admin on 2017/3/3.
 */

public class FragmentGoogleCalendarPresenterCompl implements IFragmentGoogleCalendarPresenter {

    private View mFragmentView;
    private Context mContext;
    public IFragment_GoogleCalendarView mForumView;

    public FragmentGoogleCalendarPresenterCompl(View view , Context context ,IFragment_GoogleCalendarView forumView ) {
        mFragmentView = view;
        mContext = context;
        mForumView = forumView;
    }

    public void SetTabs(FragmentManager manager)
    {
        mForumView.SetTabs(InitTabsFragment(manager));
    }

    private TabFragmentAdapter InitTabsFragment(FragmentManager manager)
    {
        TabFragmentAdapter adapter = new TabFragmentAdapter(manager,mContext);
        adapter.addFragment(new Fragment_Tabs_GoogleCalendar_View(), "查看" , R.drawable.ic_menu_camera);
        adapter.addFragment(new Fragment_Tabs_GoogleCalendar_Setting(), "設定" ,  R.drawable.ic_menu_gallery );
        return adapter;
    }

}
