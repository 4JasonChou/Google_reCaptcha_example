package com.foyatech.foya.view.viewInterface;

import java.util.ArrayList;
import java.util.HashMap;

public interface IFragment_ForumView {
    public void ShowForumList( ArrayList<HashMap<String, Object>> showList);
    public void ShowSnackbar(String text);
}
