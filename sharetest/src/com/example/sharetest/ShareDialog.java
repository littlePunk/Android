package com.example.sharetest;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShareDialog extends Dialog {
	private Context context;
	public ShareDialog(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	public static class Builder {
		private Context context;
		private String title;
		private ListView list;
		private List<ResolveInfo> shareList;
		public Builder(Context context) {
			this.context = context;
		}
		public Builder setTitle(String title){
			this.title = title;
			return this;
		}
		public Builder setShareList(List<ResolveInfo> listName){
			this.shareList = listName;
			return this;
		}
		public ShareDialog create() {
			LayoutInflater inflater = (LayoutInflater) context  
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
			final ShareDialog dialog = new ShareDialog(context);
			View layout = inflater.inflate(R.layout.resolver_grid, null);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.addContentView(layout, new LayoutParams(  
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			((TextView)layout.findViewById(R.id.alertTitle)).setText(title);
			ListView list = (ListView) layout.findViewById(R.id.resolver_grid);
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, shareListName);
			ShareAdapter adapter = new ShareAdapter(context, shareList);
			list.setAdapter(adapter);
			return dialog;
		}
		public class ShareAdapter extends BaseAdapter{
			private List<ResolveInfo> mBaseResolveList;
			private LayoutInflater mInflater;
			public ShareAdapter(Context context,List<ResolveInfo> rList) {
				mBaseResolveList = rList;
				mInflater = LayoutInflater.from(context);
			}
			@Override
			public int getCount() {
				int result = mBaseResolveList.size();
				return result;
			}

			@Override
			public ResolveInfo getItem(int position) {
				// TODO Auto-generated method stub
				return mBaseResolveList.get(position);
			}

			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = convertView;
				if (view == null){
					view = mInflater.inflate(R.layout.resolve_list_item,parent, false);
					final ViewHolder holder = new ViewHolder(view);
					view.setTag(holder);
				}
				bindView(view, getItem(position));
				return view;
			}
			
			private final void bindView(View view,ResolveInfo info){
				final ViewHolder holder = (ViewHolder)view.getTag();
				holder.text.setText(info.loadLabel(context.getPackageManager()).toString());
				holder.icon.setImageDrawable(info.loadIcon(context.getPackageManager()));
			}
		}
		private class ViewHolder {
			public TextView text;
	        public TextView text2;
	        public ImageView icon;
	        public ViewHolder(View view) {
	            text = (TextView) view.findViewById(R.id.text1);
	            text2 = (TextView) view.findViewById(R.id.text2);
	            icon = (ImageView) view.findViewById(R.id.icon);
	        }
		}
	}
}
