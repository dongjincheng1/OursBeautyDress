package com.example.beautydress.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ViewHolder {
    private View mConvertView; //用于返回给listview 的adapter getView 方法的view
    private SparseArray<View>  viewSparseArray=new SparseArray<>(); //用来替代HashMap<integer,E>
    public View getmConvertView() {
        return mConvertView;
    }

    public ViewHolder(Context context, int resId) {
        mConvertView= LayoutInflater.from(context).inflate(resId,null);
        mConvertView.setTag(this); //给view设置tag
    }

    public  static  ViewHolder getHolder(View convertView,Context context, int resId){
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder(context,resId);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return  viewHolder;
    }

    public  View findViewById(int id){
        View view  = viewSparseArray.get(id);
        if (view != null) {

        }else {
            view=mConvertView.findViewById(id);
            viewSparseArray.append(id,view);
        }
        return  view;
    }
}
