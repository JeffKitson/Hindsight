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
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class RecordVideo extends Activity {

	/**
	 * TODO: Set the path variable to a streaming video URL or a local media file
	 * path.
	 */
	private String path = "http://10.5.5.9:8080/live/amba.m3u8";
	private VideoView mVideoView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		isConnected();
	
	}
	public boolean isConnected()
	{
	    try{
	    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    	StrictMode.setThreadPolicy(policy);
	        ConnectivityManager cm = (ConnectivityManager) getSystemService
	                                                    (Context.CONNECTIVITY_SERVICE);
	        NetworkInfo netInfo = cm.getActiveNetworkInfo();

	        if (netInfo != null && netInfo.isConnected())
	        {
	            //Network is available but check if we can get access from the network.
	            URL url = new URL("http://10.5.5.9/bacpac/SH?t=YOURPASSWORD&p=%01");
	            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
	            urlc.setRequestProperty("Connection", "close");
	            urlc.setConnectTimeout(2000); // Timeout 2 seconds.
	            urlc.connect();

	            if (urlc.getResponseCode() == 200)  //Successful response.
	            {
	                return true;
	            } 
	            else 
	            {
	                 Log.d("NO INTERNET", "NO INTERNET");
	                return false;
	            }
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    return false;
	}
}
