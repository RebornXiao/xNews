package com.xnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xnews.R;
import com.xnews.bean.PicuterDetailModle;
import com.xnews.view.ProgressButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

;

public class PicuterDetailAdapter extends BaseAdapter {
    public List<PicuterDetailModle> lists = new ArrayList<PicuterDetailModle>();

    private Context context;

    public PicuterDetailAdapter(Context context) {
        this.context = context;
    }

    public void appendList(List<PicuterDetailModle> list) {
        if (!lists.containsAll(list) && list != null && list.size() > 0) {
            lists.addAll(list);
        }
        notifyDataSetChanged();
    }

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
                    R.layout.item_detail_photo, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PicuterDetailModle picuterDetailModle = lists.get(position);
        holder.photoCount.setText((position + 1) + "/" + lists.size());
        holder.photoContent.setText(picuterDetailModle.getAlt());
        holder.photoTitle.setText(picuterDetailModle.getTitle());
        String imgurl = picuterDetailModle.getPic().replace("auto", "854x480x75x0x0x3");
        Glide.with(context).load(imgurl).into(holder.currentImage);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_detail_photo.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.current_image)
        ImageView currentImage;
        @Bind(R.id.progressButton)
        ProgressButton progressButton;
        @Bind(R.id.photo_content)
        TextView photoContent;
        @Bind(R.id.photo_title)
        TextView photoTitle;
        @Bind(R.id.photo_count)
        TextView photoCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
