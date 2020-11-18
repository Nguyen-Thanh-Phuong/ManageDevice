package com.danang.managedevice.Controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Other {
    public static void setHeightDynamicLsView(ArrayAdapter<String> mListViewAdapter, ListView list)
    {
        int totalHeight = 0;
        for (int i = 0; i < mListViewAdapter.getCount(); i++) {
            View listItem = mListViewAdapter.getView(i, null, list);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = list.getLayoutParams();
        params.height = totalHeight + (list.getDividerHeight() * (mListViewAdapter.getCount() - 1));
        list.setLayoutParams(params);
    }

}
