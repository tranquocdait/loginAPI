package com.cnpm.doan2.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.activites.MainActivity;
import com.cnpm.doan2.models.CommentPlace;
import com.cnpm.doan2.models.Image;
import com.cnpm.doan2.models.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCommentPlace extends ArrayAdapter<CommentPlace> {
    public AdapterCommentPlace(Context context, ArrayList<CommentPlace> commentPlaceArrayList) {
        super(context, 0,commentPlaceArrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.adapter_comment,parent,false);
        }
        TextView tvcomment=(TextView) convertView.findViewById(R.id.id_comment_place);
        ImageView image=(ImageView) convertView.findViewById(R.id.id_image_avatar_comment);

        CommentPlace commentPlace=getItem(position);
        if (commentPlace!=null){
            tvcomment.setText(commentPlace.getContent());
            Picasso.with(getContext()).load(commentPlace.getTourist().getAvatar().getUrl()).into(image);
        }
        return convertView;
    }
}
