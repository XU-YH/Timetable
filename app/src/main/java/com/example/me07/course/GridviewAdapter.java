package com.example.me07.course;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.me07.R;

import java.util.List;

/**
 * Created by 胥尹辉 on 2018/1/14.
 */

public class GridviewAdapter extends BaseAdapter{
    private Context mContext;
    //保存内容的内部数组
    private List<String> content;

    public GridviewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.content = list;
    }

    public int getCount() {
        return content.size();
    }

    public Object getItem(int position) {
        return content.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.text);
        //如果有课,那么添加数据
        textView.setText((String)getItem(position));
        if( !getItem(position).equals("")) {
            textView.setTextColor(Color.WHITE);
            //变换颜色
            int rand = position % 7;
            switch( rand ) {
                case 0:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg));
                    break;
                case 1:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg1));
                    break;
                case 2:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg2));
                    break;
                case 3:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg3));
                    break;
                case 4:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg4));
                    break;
                case 5:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg5));
                    break;
                case 6:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg6));
                    break;
            }
        }
        return convertView;
    }

}
