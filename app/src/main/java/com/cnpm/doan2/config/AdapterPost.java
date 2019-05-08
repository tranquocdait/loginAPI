package com.cnpm.doan2.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.models.Image;
import com.cnpm.doan2.models.Place;
import com.cnpm.doan2.models.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPost extends ArrayAdapter<Post> {
    public AdapterPost(Context context, ArrayList<Post> postArrayList) {
        super(context, 0,postArrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.adapter_post,parent,false);
        }
        final TextView tvNumberLike=(TextView) convertView.findViewById(R.id.id_number_like);
        final TextView tvContent=(TextView) convertView.findViewById(R.id.id_content_post);
        ImageView imAvatar=(ImageView) convertView.findViewById(R.id.id_avatar_comemt_port);

        final Post post=getItem(position);
        if (post!=null){
            tvContent.setText(post.getContent());
            tvNumberLike.setText(post.getLikes().length+" likes");
            List<Image> imageList=post.getImages();
            Picasso.with(getContext()).load(imageList.get(0).getUrl()).into(imAvatar);
        }
        return convertView;
    }
}
