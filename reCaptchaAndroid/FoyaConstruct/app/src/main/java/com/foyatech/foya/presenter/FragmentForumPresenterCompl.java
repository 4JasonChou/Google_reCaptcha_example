package com.foyatech.foya.presenter;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.foyatech.foya.presenter.presenterInterface.IFragmentForumPresenter;
import com.foyatech.foya.view.viewInterface.IFragment_ForumView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 2017/2/24.
 */

public class FragmentForumPresenterCompl implements IFragmentForumPresenter {

    private View mFragment;
    public IFragment_ForumView mForumView;
    private ArrayList<ForumInfo> mForumsData;

    public FragmentForumPresenterCompl(IFragment_ForumView forumView ,  View fragment ){mForumView = forumView;  mFragment =  fragment; }
    @Override
    public void InitForumList() {
        // 在此,取得討論版清單 再呼叫 mForumView的顯示清單
        //取得討論版清單資料
        mForumsData = GetFourmInfo();
        //顯示討論版清單
        mForumView.ShowForumList(SetForumsList_HashMap(GetForumsList("Main")));
    }

    public void OnForumItemOnClick(String id) {
        ForumInfo selectForum  = GetForumFromForumsData(id);
        if(selectForum != null)
        {
            if(selectForum.Isfinal)
            {
                //切換文章區
                //mForumView.ShowSnackbar("Is Final.");
                Snackbar.make( mFragment  , "Final" , Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            else
            {
                //切換討論版
                mForumView.ShowForumList(SetForumsList_HashMap(GetForumsList(id)));
            }
        }
        else
            return;
    }

    private ForumInfo GetForumFromForumsData(String Id) {
        for(int i=0;i<mForumsData.size();i++)
        {
            if(mForumsData.get(i).Id.equals(Id)) {
                return mForumsData.get(i);
            }
        }
        return  null;
    }
    private ArrayList<ForumInfo> GetForumsList( String forumType ) {
        ArrayList<ForumInfo> res = new ArrayList<ForumInfo>();
        for(int i=0;i<mForumsData.size();i++)
        {
            if(mForumsData.get(i).Type.equals(forumType)) {
                res.add(mForumsData.get(i));
            }
        }
        return  res;
    }
    private ArrayList<HashMap<String, Object>> SetForumsList_HashMap(ArrayList<ForumInfo> readyList ) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>> ();
        HashMap<String, Object> oneFourm;
        for(int i=0;i<readyList.size();i++)
        {
            oneFourm = new HashMap<String, Object>();
            oneFourm.put("fragment_forum_ForumTitle", readyList.get(i).Title);
            oneFourm.put("fragment_forum_ForumDes", readyList.get(i).Des);
            oneFourm.put("forumName", readyList.get(i).Id);
            list.add(oneFourm);
        }
        return list;

    }
    private class ForumInfo {
        public String Type ;
        public String Id ;
        public String Title ;
        public String Des;
        public boolean Isfinal = false ;
        public ForumInfo ( String type, String id ,String title,String des,boolean isfinal)
        {
            Type = type; Id = id ; Title = title ; Des = des; Isfinal = isfinal;
        }
    }
    public ArrayList<ForumInfo> GetFourmInfo() {
        ForumInfo f1 = new ForumInfo("Main","BackMain","後勤支援處","xxxx",false);
        ForumInfo f2 = new ForumInfo("Main","PeopleMain","人力事業處","xxxx",false);
        ForumInfo s11 = new ForumInfo("BackMain","WorkSup","行政支援處","xxxx",true);
        ForumInfo s12 = new ForumInfo("BackMain","ThingSup","業務支援處","xxxx",true);
        ForumInfo s13 = new ForumInfo("BackMain","SkillSup","技術支援處","xxxx",true);
        ForumInfo s21 = new ForumInfo("PeopleMain","PeopleSup","人力支援處","xxxx",true);


        ArrayList<ForumInfo> res  = new ArrayList<ForumInfo>();
        res.add(f1);
        res.add(f2);
        res.add(s11);
        res.add(s12);
        res.add(s13);
        res.add(s21);

        return  res;
    }
}
