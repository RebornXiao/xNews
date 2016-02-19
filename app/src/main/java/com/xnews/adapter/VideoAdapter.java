package com.xnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xnews.R;
import com.xnews.bean.VideoModle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoAdapter extends BaseAdapter {
    private Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public List<VideoModle> lists = new ArrayList<VideoModle>();

    public void appendList(List<VideoModle> list) {
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
                    R.layout.item_video, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideoModle videoModle = lists.get(position);
        holder.videoTime.setText(videoModle.getLength());
        holder.videoTitle.setText(videoModle.getTitle());
        Picasso.with(context).load(videoModle.getCover()).into(holder.videoImg);

        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_video.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.video_img)
        ImageView videoImg;
        @Bind(R.id.video_title)
        TextView videoTitle;
        @Bind(R.id.video_time)
        TextView videoTime;
        @Bind(R.id.layout)
        LinearLayout layout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
