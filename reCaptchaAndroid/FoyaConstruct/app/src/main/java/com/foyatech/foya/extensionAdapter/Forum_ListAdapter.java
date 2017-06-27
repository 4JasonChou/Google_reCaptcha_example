package com.foyatech.foya.extensionAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foyatech.foya.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 2017/2/21.
 */

public class Forum_ListAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String[] keyString;
    private int[] valueViewID;

    private ItemView itemView;

    private class ItemView {
        // 宣告這個ListView可以被調整的物件
        public ImageView iconImage;
        public TextView forumTitle;
        public TextView forumDes;
        public String forumName;
    }

    public Forum_ListAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource, String[] from, int[] to) {

        mAppList = appList;
        mContext = c;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        keyString = new String[from.length];
        valueViewID = new int[to.length];
        System.arraycopy(from, 0, keyString, 0, from.length);
        System.arraycopy(to, 0, valueViewID, 0, to.length);
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getItemForumName(int position){return itemView.forumName; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            itemView = (ItemView) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.listview_forum_list_item, null);
            itemView = new ItemView();
            itemView.iconImage = (ImageView)convertView.findViewById(R.id.fragment_forum_ForumImage); // fragment_forum_ForumImage
            itemView.forumTitle = (TextView)convertView.findViewById(valueViewID[0]); // fragment_forum_ForumTitle
            itemView.forumDes = (TextView)convertView.findViewById(valueViewID[1]); //fragment_forum_ForumDes
            convertView.setTag(itemView);
        }
        HashMap<String, Object> appInfo = mAppList.get(position);
        if (appInfo != null) {
            String forumTitle = appInfo.get(keyString[0]).toString(); //fragment_forum_ForumTitle Text
            String forumDes = appInfo.get(keyString[1]).toString(); //fragment_forum_ForumDes Text
            itemView.forumTitle.setText(forumTitle);
            itemView.forumDes.setText(forumDes);
            itemView.iconImage.setImageResource(R.mipmap.ic_launcher);
            itemView.forumName = appInfo.get(keyString[2]).toString();
        }
        return convertView;
    }
}
