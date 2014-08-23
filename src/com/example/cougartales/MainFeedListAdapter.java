package com.example.cougartales;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainFeedListAdapter extends ArrayAdapter<ParseObject> {

	private int res = 0;
	private Context mContext;
	private final LruCache<Integer, Bitmap> mMemoryCache;
	private List<ParseObject> items;

	public MainFeedListAdapter(Context context, int resource,
			List<ParseObject> objects) {
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
		boolean playing = false;

		if (view == null) {

			if (items.get(position).getBoolean("isPlaying")) {
				view = LayoutInflater.from(mContext).inflate(
						R.layout.score_known_list_item, parent, false);

				viewHolder = new ViewHolder();
				view.setTag(viewHolder);

				
				viewHolder.nameTextView = (TextView) view
						.findViewById(R.id.textView1);
				viewHolder.pb = (ProgressBar) view
						.findViewById(R.id.progressBar1);
				playing = true;
			} else {
				view = LayoutInflater.from(mContext).inflate(
						R.layout.main_feed__item, parent, false);

				
				viewHolder = new ViewHolder();
				view.setTag(viewHolder);

				viewHolder.nameTextView = (TextView) view
						.findViewById(R.id.textView1);
				viewHolder.pb = (ProgressBar) view
						.findViewById(R.id.progressBar1);
				viewHolder.twitterPic = (ImageView) view
						.findViewById(R.id.imageView1);
			}

		} else {
			viewHolder = (ViewHolder) view.getTag();

		}

		initItemUI(viewHolder, position, playing);

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

	private void initItemUI(final ViewHolder viewHolder, int position,
			boolean playing) {

		viewHolder.nameTextView.setText(items.get(position).getString(
				"teamName"));
		if (!playing) {
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

	}

	public void remove(int position) {

		items.remove(position);

	}

}
