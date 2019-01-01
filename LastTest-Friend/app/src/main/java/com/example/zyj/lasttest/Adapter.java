package com.example.zyj.lasttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.network.zhouwei.http_network.Comment;
import com.network.zhouwei.http_network.Server;

import java.util.List;

/**
 * Created by zyj on 2018/12/12.
 */
public class Adapter extends ArrayAdapter {
    private int resourceId;

    public Adapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Server server = new Server();
        server.setServer("119.29.60.170");
        Comment comment = (Comment) getItem(position);
        ListLayout listLayout = new ListLayout();
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            listLayout.nameView = (TextView) view.findViewById(R.id.name_text);
            listLayout.dataView = (TextView) view.findViewById(R.id.data_text);
            listLayout.titleView = (TextView) view.findViewById(R.id.title_text);
            listLayout.txImg = (ImageView) view.findViewById(R.id.tx_img);
            listLayout.pictImg = (ImageView) view.findViewById(R.id.pict_img);
            view.setTag(listLayout);
        } else {
            view = convertView;
            listLayout = (ListLayout) view.getTag();
        }
        listLayout.nameView.setText(comment.getUsername());
        listLayout.txImg.setImageBitmap(server.imageFileDownload(comment.getUsername()));
        listLayout.titleView.setText(comment.getContext());
        listLayout.dataView.setText("2∑÷÷”«∞");
        listLayout.pictImg.setImageResource(R.drawable.beijing);
        return view;
    }

    class ListLayout {
        private ImageView txImg;
        private TextView nameView;
        private TextView titleView;
        private ImageView pictImg;
        private TextView dataView;
    }
}
