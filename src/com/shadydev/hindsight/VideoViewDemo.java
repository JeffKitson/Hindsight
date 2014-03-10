/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shadydev.hindsight;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoViewDemo extends Activity {

	/**
	 * TODO: Set the path variable to a streaming video URL or a local media file
	 * path.
	 */
	private String path = "http://10.5.5.9:8080/live/amba.m3u8";
	private VideoView mVideoView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.videoview);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		
		

		if (path == "" || !testConn()) {
			// Tell the user to provide a media file URL/path.
			Toast.makeText(VideoViewDemo.this, "There seems to be an issue connecting.", Toast.LENGTH_LONG).show();
			return;
		} else {
			/*
			 * Alternatively,for streaming media you can use
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
			mVideoView.setVideoPath(path);
			mVideoView.setMediaController(new MediaController(this));
			mVideoView.requestFocus();

			mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mediaPlayer) {
					// optional need Vitamio 4.0
					mediaPlayer.setPlaybackSpeed(1.0f);
				}
			});
		}

	}
	
	public boolean testConn(){
		try {

            URL url = new URL("http://10.5.5.9:8080/live/amba.m3u8");

    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
    urlc.setRequestProperty("User-Agent", "Android Application:1");
    urlc.setRequestProperty("Connection", "close");
    urlc.setConnectTimeout(1000 * 30); // mTimeout is in seconds
            urlc.connect();
    if (urlc.getResponseCode() == 200) {
        //Main.Log("getResponseCode == 200");
            return new Boolean(true);
    }
    } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
		return false;
	}
}
