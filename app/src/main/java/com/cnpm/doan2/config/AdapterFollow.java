package com.cnpm.doan2.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.models.CommentPlace;
import com.cnpm.doan2.models.Image;
import com.cnpm.doan2.models.Post;
import com.cnpm.doan2.models.Tourist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterFollow  extends ArrayAdapter<Tourist> {
    public AdapterFollow(Context context, ArrayList<Tourist> followArrayList) {
        super(context, 0,followArrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.adapter_follow,parent,false);
        }
        TextView tvName=(TextView) convertView.findViewById(R.id.id_name_follow);
        ImageView image=(ImageView) convertView.findViewById(R.id.id_image_avatar_follow);

        Tourist tourist=getItem(position);
        if (tourist!=null){
            tvName.setText(tourist.getFullname());
            Picasso.with(getContext()).load(tourist.getAvatar().getUrl()).into(image);
        }
        return convertView;
    }
}
