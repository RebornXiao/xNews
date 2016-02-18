package com.xnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xnews.R;
import com.xnews.bean.NewModle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewAdapter extends BaseAdapter {
    public List<NewModle> lists;
    private Context context;
    private String currentItem;

    public NewAdapter(Context context) {
        this.context = context;
        lists = new ArrayList<NewModle>();
    }

    public void appendList(List<NewModle> list) {
        if (!lists.containsAll(list) && list != null && list.size() > 0) {
            lists.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        lists.clear();
        notifyDataSetChanged();
    }

    public void currentItem(String item) {
        this.currentItem = item;
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
                    R.layout.item_new, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewModle newModle = lists.get(position);
        if (newModle.getImagesModle() == null) {
            holder.articleLayout.setVisibility(View.VISIBLE);
            holder.itemImageLayout.setVisibility(View.GONE);
            holder.itemTitle.setText(newModle.getTitle());
            if ("北京".equals(currentItem)) {


            } else {
                holder.itemContent.setText(newModle.getDigest());
            }
            if (!"".equals(newModle.getImgsrc())) {
                holder.leftImage.setVisibility(View.VISIBLE);
                Picasso.with(context).load(newModle.getImgsrc()).into(holder.leftImage);
            } else {
                holder.leftImage.setVisibility(View.GONE);
            }
        } else {
            holder.layoutImage.setVisibility(View.VISIBLE);
            holder.articleLayout.setVisibility(View.GONE);
            holder.itemAbstract.setText(newModle.getTitle());
            List<String> imageModle = newModle.getImagesModle().getImgList();
            Picasso.with(context).load(imageModle.get(0)).into(holder.itemImage0);
            Picasso.with(context).load(imageModle.get(1)).into(holder.itemImage1);
            Picasso.with(context).load(imageModle.get(2)).into(holder.itemImage2);

//            newItemView.setImages(newModle);
        }

        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_new.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.left_image)
        ImageView leftImage;
        @Bind(R.id.item_title)
        TextView itemTitle;
        @Bind(R.id.item_content)
        TextView itemContent;
        @Bind(R.id.title_layout)
        RelativeLayout titleLayout;
        @Bind(R.id.article_top_layout)
        RelativeLayout articleTopLayout;
        @Bind(R.id.item_abstract)
        TextView itemAbstract;
        @Bind(R.id.item_image_0)
        ImageView itemImage0;
        @Bind(R.id.item_image_1)
        ImageView itemImage1;
        @Bind(R.id.item_image_2)
        ImageView itemImage2;
        @Bind(R.id.item_image_layout)
        LinearLayout itemImageLayout;
        @Bind(R.id.layout_image)
        LinearLayout layoutImage;
        @Bind(R.id.article_layout)
        LinearLayout articleLayout;
        @Bind(R.id.item_divider)
        View itemDivider;
        @Bind(R.id.item_layout)
        LinearLayout itemLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
