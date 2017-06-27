package com.foyatech.foya.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foyatech.foya.R;
import com.foyatech.foya.presenter.FragmentGoogleCalendarPresenterCompl;
import com.foyatech.foya.presenter.presenterInterface.IFragmentGoogleCalendarPresenter;
import com.foyatech.foya.tabsFragment.Fragment_Tabs_GoogleCalendar_Setting;
import com.foyatech.foya.tabsFragment.Fragment_Tabs_GoogleCalendar_View;
import com.foyatech.foya.view.viewInterface.IFragment_GoogleCalendarView;
import com.foyatech.foyalibrary.adapter.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


public class FragmentGoogleCalendar extends Fragment implements IFragment_GoogleCalendarView {

    private OnFragmentInteractionListener mListener;
    private Activity mActivity;
    private Context mContext;
    private View mView;
    private TabLayout mGoogleTabLayout;
    private ViewPager mViewPage;
    private IFragmentGoogleCalendarPresenter mFragmentGoogleCalendarPresenter;


    public FragmentGoogleCalendar() {
        // Required empty public constructor
    }
    public static FragmentGoogleCalendar newInstance(String param1, String param2) {
        FragmentGoogleCalendar fragment = new FragmentGoogleCalendar();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_google_calendar, container, false);
        ((Activity_MainPage)getActivity()).setMyToolbarTitle("Google日曆");
        mActivity = getActivity();

        //InitLogic
        mFragmentGoogleCalendarPresenter = new FragmentGoogleCalendarPresenterCompl(mView,mContext,this);
        //FindView
        mGoogleTabLayout = (TabLayout)mView.findViewById(R.id.tabGoogleCalendar);
        mViewPage = (ViewPager)mView.findViewById(R.id.pagerGoogleCalendar);

        //Setting Start
        mFragmentGoogleCalendarPresenter.SetTabs(getChildFragmentManager());

        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void SetTabs(TabFragmentAdapter adapter) {
        mViewPage.setAdapter(adapter);
        mGoogleTabLayout.setupWithViewPager(mViewPage);
        adapter.SetCustomView(mGoogleTabLayout);

    }
}
