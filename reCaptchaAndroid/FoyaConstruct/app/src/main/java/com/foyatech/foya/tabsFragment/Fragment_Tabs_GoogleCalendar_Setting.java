package com.foyatech.foya.tabsFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foyatech.foya.R;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Tabs_GoogleCalendar_Setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Tabs_GoogleCalendar_Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Tabs_GoogleCalendar_Setting extends Fragment {

    private OnFragmentInteractionListener mListener;
    private GoogleApiClient mGoogleApiClient;


    public Fragment_Tabs_GoogleCalendar_Setting() {
        // Required empty public constructor
    }

    public static Fragment_Tabs_GoogleCalendar_Setting newInstance(String param1, String param2) {
        Fragment_Tabs_GoogleCalendar_Setting fragment = new Fragment_Tabs_GoogleCalendar_Setting();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs_google_calendar_setting, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {  super.onAttach(context);  }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
