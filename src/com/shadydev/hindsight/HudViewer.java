package com.shadydev.hindsight;

import io.vov.vitamio.LibsChecker;
import com.shadydev.hindsight.MediaMetadataRetrieverDemo;
import com.shadydev.hindsight.MediaPlayerDemo;
import com.shadydev.hindsight.VideoBuffer;
//import com.shadydev.hindsight.VideoSubtitleList;
import com.shadydev.hindsight.VideoViewDemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import io.vov.vitamio.LibsChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HudViewer extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		//addItem(myData, "MediaPlayer", new Intent(this, MediaPlayerDemo.class));
		addItem(myData, "GoPro WiFi", new Intent(this, VideoViewExtended.class));

		return myData;
	}

	protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
	}

}
/* extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hud_viewer);
		//Remove title bar
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//
		
		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			return;
		// your code
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hud_viewer, menu);
		return true;
	}

}
*/