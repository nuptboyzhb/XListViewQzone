package edu.njupt.zhb.xlistviewtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;

public class GridAdapter extends ArrayAdapter<String>{

	Context context;
	int resLayoutId;
	List<String> imageUrls;
	AQuery aq;
	public GridAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.resLayoutId = resource;
		this.imageUrls = objects;
		aq = new AQuery(context);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(resLayoutId, null);
//		aq.id(R.id.ivPhoto).image(imageUrls.get(position), true, true, 200,
//				R.drawable.image_missing, null, 0, 0);
		ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
		ivPhoto.setImageResource(R.drawable.pic_screen);
		return convertView;
	}

}
