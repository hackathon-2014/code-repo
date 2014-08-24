package com.example.cougartales;

import java.util.Date;
import java.util.List;

import twitter4j.Status;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

public class StatusArrayAdapter extends ArrayAdapter<Status> {
	
	private List<Status> items;

	public StatusArrayAdapter(Context context, int resource,
			List<Status> objects) {
		super(context, resource, objects);
		items = objects;
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
			
			view.setTag(viewHolder);
		} 

		else{
			viewHolder = (ViewHolder) view.getTag();
		}
		
		Status status = getItem(position);
		
		
		if (status.getRetweetedStatus() != null) {
			status = status.getRetweetedStatus();
		}
		
		String imageURL = status.getUser().getBiggerProfileImageURL();
		viewHolder.tweet.setText(status.getText());
		viewHolder.sport.setText(status.getUser().getName());
		viewHolder.twitterHandle.setText("@" + status.getUser().getScreenName());
		viewHolder.timeAgo.setText(DateUtils.getRelativeTimeSpanString(status.getCreatedAt().getTime(), new Date().getTime(), DateUtils.MINUTE_IN_MILLIS));
		
		// but for brevity, use the ImageView specific builder...
		Ion.with(viewHolder.userPic)
		.placeholder(R.drawable.ic_launcher)
		.error(R.drawable.ic_launcher)
		.load(imageURL);

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
