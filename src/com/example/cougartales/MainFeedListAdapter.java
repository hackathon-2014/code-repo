package com.example.cougartales;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.parse.ParseObject;

public class MainFeedListAdapter extends ArrayAdapter<Game> {

	private int res = 0;
	private Context mContext;
	private final LruCache<Integer, Bitmap> mMemoryCache;
	private List<Game> items;

	public MainFeedListAdapter(Context context, int resource,
			List<Game> objects) {
		super(context, resource, objects);

		items = objects;
		mContext = context;
		final int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024);
		mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(final Integer key, final Bitmap bitmap) {
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
			}
		};

	}

	@Override
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {
		ViewHolder viewHolder;
		View view = convertView;

		if (view == null) {
			viewHolder = new ViewHolder();

			if (items.get(position).isInProgress()) {
				Toast.makeText(mContext, "in progress", Toast.LENGTH_SHORT).show();
				view = LayoutInflater.from(mContext).inflate(
						R.layout.score_known_list_item, parent, false);

				view.setTag(viewHolder);

				viewHolder.nameTextView = (TextView) view
						.findViewById(R.id.textView1);
				viewHolder.pb = (ProgressBar) view
						.findViewById(R.id.progressBar1);
				viewHolder.playing = true;
				
				viewHolder.homeTeam = (TextView) view.findViewById(R.id.textView1);
				viewHolder.awayTeam = (TextView) view.findViewById(R.id.textView3);
				viewHolder.sport = (TextView) view.findViewById(R.id.textView2);
				viewHolder.score = (TextView) view.findViewById(R.id.textView4);

			} else {
				view = LayoutInflater.from(mContext).inflate(
						R.layout.main_feed__item, parent, false);

				view.setTag(viewHolder);

				viewHolder.nameTextView = (TextView) view
						.findViewById(R.id.textView1);
				viewHolder.pb = (ProgressBar) view
						.findViewById(R.id.progressBar1);
				viewHolder.twitterPic = (ImageView) view
						.findViewById(R.id.imageView1);
				viewHolder.playing = false;

			}

		} else {
			viewHolder = (ViewHolder) view.getTag();

		}

		initItemUI(viewHolder, position);

		return view;
	}

	private void addBitmapToMemoryCache(final int key, final Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	private Bitmap getBitmapFromMemCache(final int key) {
		return mMemoryCache.get(key);
	}

	private void initItemUI(final ViewHolder viewHolder, int position) {

		
		
		//viewHolder.homeTeam.setText(items.get(position).getHomeTeam().getName());
		//viewHolder.awayTeam.setText(items.get(position).getAwayTeam().getName());
		viewHolder.sport.setText(items.get(position).getSport());
		viewHolder.score.setText(items.get(position).getHomeScore()+" - " + items.get(position).getAwayScore());
		if (!viewHolder.playing) {
			Ion.with(mContext).load(items.get(position).getString("imgUrl"))
					.progressBar(viewHolder.pb).withBitmap()
					.error(R.drawable.ic_launcher)
					.intoImageView(viewHolder.twitterPic)
					.setCallback(new FutureCallback<ImageView>() {
						@Override
						public void onCompleted(Exception e, ImageView file) {

							viewHolder.pb.setVisibility(View.GONE);
						}
					});

		}

	}

	private static class ViewHolder {
		TextView nameTextView;
		ImageView twitterPic;
		ProgressBar pb;
		TextView homeTeam;
		TextView score;
		TextView sport;
		
		TextView awayTeam;
		boolean playing;

	}

	public void remove(int position) {

		items.remove(position);

	}

}
