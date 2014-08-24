package com.example.cougartales;

import java.util.List;

import twitter4j.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StatusArrayAdapter extends ArrayAdapter<Status> {

	public StatusArrayAdapter(Context context, int resource,
			List<Status> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder viewHolder;

		
		if (view == null) {
			viewHolder = new ViewHolder();

			view = LayoutInflater.from(getContext()).inflate(R.layout.tweet_item, parent, false);
			
			viewHolder.twitterHandle = (TextView) view.findViewById(R.id.textView3);
			viewHolder.tweet = (TextView) view.findViewById(R.id.textView4);
			viewHolder.sport = (TextView) view.findViewById(R.id.textView2);
			viewHolder.timeAgo = (TextView) view.findViewById(R.id.textView1);
			viewHolder.userPic= (ImageView) view.findViewById(R.id.imageView1);
		} 

		else{
			viewHolder = (ViewHolder) view.getTag();

		
		}
		TextView statusText = (TextView) view.findViewById(R.id.status_text);
		statusText.setText(getItem(position).getText());

		return view;
	}

	private static class ViewHolder {
		ImageView userPic;
		TextView twitterHandle;
		TextView tweet;
		TextView sport;
		TextView timeAgo;
	}
	
}
