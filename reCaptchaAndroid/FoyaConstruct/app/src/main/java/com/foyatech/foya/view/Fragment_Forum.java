package com.foyatech.foya.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.foyatech.foya.R;
import com.foyatech.foya.extensionAdapter.Forum_ListAdapter;
import com.foyatech.foya.presenter.FragmentForumPresenterCompl;
import com.foyatech.foya.presenter.presenterInterface.IFragmentForumPresenter;
import com.foyatech.foya.view.viewInterface.IFragment_ForumView;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment_Forum extends Fragment implements IFragment_ForumView {

    private String TAG = "Fragment_Forum";
    private OnFragmentInteractionListener mListener;
    private Activity mActivity;
    private View mView;

    private IFragmentForumPresenter mForumPresenter;
    private ListView mForumList;

    public Fragment_Forum() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_Forum newInstance(String param1, String param2) {
        Fragment_Forum fragment = new Fragment_Forum();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { /* do somthing */ }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_forum, container, false);
        mActivity = getActivity();
        ((Activity_MainPage)getActivity()).setMyToolbarTitle("布告欄");

        //InitLogic
        mForumPresenter = new FragmentForumPresenterCompl(this,mView);

        //Find View
        mForumList = (ListView)mView.findViewById(R.id.fragment_forum_listView);

        //Set OnClickEvent
        mForumList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> item =  ( HashMap<String, Object>) parent.getItemAtPosition(position);
                String forumId = item.get("forumName").toString();
                mForumPresenter.OnForumItemOnClick(forumId);
            }
        });

        //Setting First Start Data and View.
        mForumPresenter.InitForumList();

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void ShowForumList( ArrayList<HashMap<String, Object>> showList)
    {
        String[] keys = { "fragment_forum_ForumTitle","fragment_forum_ForumDes","forumName"};
        int[] viewIds = { R.id.fragment_forum_ForumTitle,R.id.fragment_forum_ForumDes };
        Forum_ListAdapter sa = new Forum_ListAdapter(mActivity.getBaseContext(), showList ,R.layout.listview_forum_list_item , keys, viewIds);
        mForumList.setAdapter(sa);
    }

    @Override
    public void ShowSnackbar(String text)
    {
        Snackbar.make( mView  , text , Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
