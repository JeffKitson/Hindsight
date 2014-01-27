package com.shadydev.hindsight;


//import com.shadydev.battlehud.model.Landmarks;
import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.TimelineManager;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import android.hardware.Camera;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.widget.TextView;
import android.widget.VideoView;

public class HindsightService extends Service {

    private static final String LIVE_CARD_ID = "compass";
    private TextView latituteField;
    private TextView longitudeField;
    //private Camera mCamera;
    
   

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
    public class HudBinder extends Binder {
        /**
         * Read the current heading aloud using the text-to-speech engine.
         */
        public void readHeadingAloud() {
           
			//float heading = mOrientationManager.getHeading();

            Resources res = getResources();
            //String[] spokenDirections = res.getStringArray(R.array.spoken_directions);
            //String directionName = spokenDirections[MathUtils.getHalfWindIndex(heading)];

            //int roundedHeading = Math.round(heading);
           // int headingFormat;
           /* if (roundedHeading == 1) {
                headingFormat = R.string.spoken_heading_format_one;
            } else {
                headingFormat = R.string.spoken_heading_format;
            }
*/
            //String headingText = res.getString(headingFormat, roundedHeading, directionName);
            //mSpeech.speak(headingText, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
	  private final HudBinder mBinder = new HudBinder();

	    //private OrientationManager mOrientationManager;
	    //private Landmarks mLandmarks;
	    private TextToSpeech mSpeech;

	    private TimelineManager mTimelineManager;
	    private LiveCard mLiveCard;
	   // private HudRenderer mRenderer;

	    @Override
	    public void onCreate() {
	        super.onCreate();
	    

	        mTimelineManager = TimelineManager.from(this);

	        // Even though the text-to-speech engine is only used in response to a menu action, we
	        // initialize it when the application starts so that we avoid delays that could occur
	        // if we waited until it was needed to start it up.
	        mSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
	            @Override
	            public void onInit(int status) {
	                // Do nothing.
	            }
	        });

	        SensorManager sensorManager =
	                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	        LocationManager locationManager =
	                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        Criteria criteria = new Criteria();
	        String provider = locationManager.getBestProvider(criteria, false);
	        Location location = locationManager.getLastKnownLocation(provider);
	     // Initialize the location fields
	        
	        if (location != null) {
	          System.out.println("Provider " + provider + " has been selected.");
	          //onLocationChanged(location);
	        } else {
	          //latituteField.setText("Location not available");
	          //longitudeField.setText("Location not available");
	        }
	  

	      //  mOrientationManager = new OrientationManager(sensorManager, locationManager);
	       //// mLandmarks = new Landmarks(this);
	        
	    }
	  @Override
	    public int onStartCommand(Intent intent, int flags, int startId) {
	        if (mLiveCard == null) {
	            mLiveCard = mTimelineManager.createLiveCard(LIVE_CARD_ID);
	         
				//mRenderer = new HudRenderer(this, mOrientationManager);
				
	            mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder();//.addCallback(mRenderer);

	            // Display the options menu when the live card is tapped.
	            //Intent menuIntent = new Intent(this, HudMenu.class);
	           // menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	           // mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));

	           // mLiveCard.publish(PublishMode.REVEAL);
	        }

	        return START_STICKY;
	    }

	    @Override
	    public void onDestroy() {
	        if (mLiveCard != null && mLiveCard.isPublished()) {
	            mLiveCard.unpublish();
	            mLiveCard.getSurfaceHolder();//.removeCallback(mRenderer);
	            mLiveCard = null;
	        }

	        mSpeech.shutdown();

	        mSpeech = null;
	       // mOrientationManager = null;
	        //mLandmarks = null;

	        super.onDestroy();
	    }
}
