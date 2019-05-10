package com.cnpm.doan2.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.Html;
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
    private String fullname;
    private String urlAvatar;
    public AdapterPost(Context context, ArrayList<Post> postArrayList,String fullname,String urlAvatar) {
        super(context, 0,postArrayList);
        this.fullname=fullname;
        this.urlAvatar=urlAvatar;
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

        final TextView tvFullname=(TextView) convertView.findViewById(R.id.id_fullname);
        ImageView imTourist=(ImageView) convertView.findViewById(R.id.id_image_avatar);

//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back);
//        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);


        final Post post=getItem(position);
        if (post!=null){
            tvFullname.setText(fullname);
            Picasso.with(getContext()).load(urlAvatar).into(imTourist);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvContent.setText(Html.fromHtml(post.getContent(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvContent.setText(Html.fromHtml(post.getContent()));
            }
           //tvContent.setText(post.getContent());
            tvNumberLike.setText(post.getLikes().size()+" likes");
            List<Image> imageList=post.getImages();
            Picasso.with(getContext()).load(imageList.get(0).getUrl()).into(imAvatar);
        }
        return convertView;
    }
}
