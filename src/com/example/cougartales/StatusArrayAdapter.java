package com.example.cougartales;

import java.util.List;

import twitter4j.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.team_feed_item, parent, false);
		} 

		TextView statusText = (TextView) view.findViewById(R.id.status_text);
		statusText.setText(getItem(position).getText());

		return view;
	}

}
