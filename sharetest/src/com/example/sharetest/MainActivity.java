package com.example.sharetest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	//private Button shareButton;
	private ListView shareList;
	private ArrayList<String> shareListName = new ArrayList<String>();
	private ArrayList<Drawable> shareListDrawable = new ArrayList<Drawable>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //initData();
        ShareDialog.Builder builder = new ShareDialog.Builder(this);
        builder.setTitle("·ÖÏí");
        builder.setShareList(getShareApps(this));
        builder.create().show();
    }

    private void initData() {
		// TODO Auto-generated method stub
    	List<ResolveInfo> listResolve = getShareApps(this);
    	for(ResolveInfo resolve : listResolve){
    		shareListName.add(resolve.loadLabel(getPackageManager()).toString());
    		shareListDrawable.add(resolve.loadIcon(getPackageManager()));
    	}
	}

	public List<ResolveInfo> getShareApps(Context context) {  
        List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("image/*");
        PackageManager pManager = context.getPackageManager();
        mApps = pManager.queryIntentActivities(intent, 
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return mApps;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
