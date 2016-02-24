
package com.xnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xnews.R;
import com.xnews.bean.PhotoModle;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoAdapter extends BaseAdapter {
    public List<PhotoModle> lists = new ArrayList<PhotoModle>();

    public void appendList(List<PhotoModle> list) {
        if (!lists.containsAll(list) && list != null && list.size() > 0) {
            lists.addAll(list);
        }
        notifyDataSetChanged();
    }

    private Context context;

    public void clear() {
        lists.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_photo, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PhotoModle photoModle = lists.get(position);

        String picUrl = photoModle.getSeturl();
        picUrl = picUrl.replace("auto", "854x480x75x0x0x3");

        holder.photoTitle.setText(photoModle.getSetname());
        Picasso.with(context).load(picUrl).into(holder.photoImg);

        return convertView;
    }
    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_photo.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.photo_img)
        ImageView photoImg;
        @Bind(R.id.photo_title)
        TextView photoTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
