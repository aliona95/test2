package com.example.info3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class DrawSurfaceView extends View implements View.OnClickListener {
	String TAG = "AAA";
	Point me = new Point(HomeActivity.lastLatitude, HomeActivity.lastLongitude, "Me", "-", "-", "-","-");
	public double mMyLatitude = me.latitude;
	public double mMyLongitude = me.longitude;
	Paint mPaint = new Paint();
	private double OFFSET = 0d;
	private double screenWidth, screenHeight = 0d;
	private Bitmap[] mSpots, mBlips;
	private Bitmap mRadar;
	Context context;
	private int PROXIMITY_RADIUS = 10000/*10000*//*1000*//*800*/;
	public static List<Float> coordImgx = new ArrayList<Float>();
	public static List<Float> coordImgy = new ArrayList<Float>();
	public static int indexOfclick = 0;

	private View t;

	public DrawSurfaceView(Context context, AttributeSet set) {
		super(context, set);
		Log.d("SIZE IN DRAW ", String.valueOf(HomeActivity.props.size()));
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(50);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		mPaint.setStrokeWidth(DpiUtils.getPxFromDpi(getContext(), 2));
		mPaint.setAntiAlias(true);
		
		mRadar = BitmapFactory.decodeResource(context.getResources(), R.drawable.radar);

		int temp = 0;
		if(HomeActivity.props.size() > 4){
			temp = HomeActivity.props.size() - 4;
		}

		mSpots = new Bitmap[HomeActivity.props.size() - temp];
		for (int i = 0; i < mSpots.length; i++) {
			mSpots[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable./*button*/rectangle/*balloon3*/);
		}
		mBlips = new Bitmap[HomeActivity.props.size() - temp];
		for (int i = 0; i < mBlips.length; i++) {
			mBlips[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.blip);
		}
	}

	public /*double*/ void calculateDistance(Point point) {
		double dX = point.latitude - mMyLatitude;
		double dY = point.longitude - mMyLongitude;

		double distance = (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)) * 100/*000*/);   // pow laipsnis
		point.distance  = Math.round(distance * 10);
		point.distance = point.distance / 10;

		//return distance;
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.d("onSizeChanged", "in here w=" + w + " h=" + h);
		screenWidth = (double) w;
		screenHeight = (double) h;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawBitmap(mRadar, 0, 0, mPaint);
		
		int radarCentreX = mRadar.getWidth() / 2;
		int radarCentreY = mRadar.getHeight() / 2;
		coordImgx.clear();
		coordImgy.clear();

		for (int i = 0; i < mBlips.length; i++) {
			Bitmap blip = mBlips[i];
			Bitmap spot = mSpots[i];
			Point u = HomeActivity.props.get(i);
			double dist = distInMetres(me, u);
			
			if (blip == null || spot == null)
				continue;
			
			if(dist > 70)
				dist = 70;

			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			double angle = bearing(me.latitude, me.longitude, u.latitude, u.longitude) - OFFSET;
			double xPos, yPos;
			
			if(angle < 0)
				angle = (angle+360)%360;
			
			xPos = Math.sin(Math.toRadians(angle)) * dist;
			yPos = Math.sqrt(Math.pow(dist, 2) - Math.pow(xPos, 2));

			if (angle > 90 && angle < 270)
				yPos *= -1;
			
			double posInPx = angle * (screenWidth / 90d);
			
			int blipCentreX = blip.getWidth() / 2;
			int blipCentreY = blip.getHeight() / 2;
			
			xPos = xPos - blipCentreX;
			yPos = yPos + blipCentreY;
			canvas.drawBitmap(blip, (radarCentreX + (int) xPos), (radarCentreY - (int) yPos), mPaint); //radar blip
			
			//reuse xPos
			int spotCentreX = spot.getWidth() / 2;
			int spotCentreY = spot.getHeight() / 2;
			xPos = posInPx - spotCentreX;
			
			if (angle <= 45) 
				u.x = (float) ((screenWidth / 2) + xPos);
			
			else if (angle >= 315) 
				u.x = (float) ((screenWidth / 2) - ((screenWidth*4) - xPos));
			
			else
				u.x = (float) (float)(screenWidth*9); //somewhere off the screen
			
			u.y = (float)screenHeight/2 + spotCentreY;

			coordImgx.add(u.x);
			coordImgy.add(Float.valueOf(180 + 40 + i * 350));
			canvas.drawBitmap(spot, u.x, /*u.y*/ 180 + 40 + i * 350, mPaint); //laukas camera spot - kur tekstas rasomas

			calculateDistance(HomeActivity.props.get(i));
			String text;
			text = HomeActivity.props.get(i).distance + "km";
			Log.d(TAG,u.newDesc);

			int counter = 0;
			int lettersCounter = 0;
			String textToScreen1 = " ";
			String textToScreen2 = " ";

			String tempText;
			boolean temp = false;
			for (String retval: u.description.split(" ")) {
				int sum = lettersCounter + retval.length();
				if(retval.length() > 11) {
					retval = retval.substring(0, 8) + "...";
					temp = true;
				}
				if(sum > 12 || temp){
					if(counter ==1){
						if(textToScreen2.length() < 8){
							textToScreen2 = textToScreen2 + " " + retval.substring(0, 11 - textToScreen2.length()) + "...";
						}else if(temp == true){
							textToScreen2 = textToScreen2 + "...";
						}
					}
					counter ++;
					lettersCounter = retval.length();
				}
				if(counter == 0){
					if(textToScreen1.length() > 0){
						textToScreen1 = textToScreen1 + " " + retval;
					}else{
						textToScreen1 = textToScreen1 + retval;
					}

				}else if(counter == 1){
					if(textToScreen2.length() > 0){
						textToScreen2 = textToScreen2 + " " + retval;
					}else {
						textToScreen2 = textToScreen2 + retval;
					}
					//textToScreen2 =  textToScreen2 + retval;
				}else{
					break;
				}
				temp = false;
				lettersCounter = lettersCounter  + retval.length();
				//counter++;
			}
			canvas.drawText(/*u.description*/textToScreen1, u.x + 10,/* u.y + 200*/ /*u.y - 400 + i * 150*//* 180 + i * 400*//*180 + (i+1) * 250*/coordImgy.get(i) + 110 + 0 * 50+40, mPaint); //text
			canvas.drawText(/*u.description*/textToScreen2, u.x + 10,/* u.y + 200*/ /*u.y - 400 + i * 150*//* 180 + i * 400*//*180 + (i+1) * 250*/coordImgy.get(i) + 110 + 1 * 50+40, mPaint); //text
			canvas.drawText(/*u.description*/text, u.x + 200,/* u.y + 200*/ /*u.y - 400 + i * 150*//* 180 + i * 400*//*180 + (i+1) * 250*/coordImgy.get(i) + 110 + 2 * 50+40, mPaint); // km text


			//canvas.drawText(/*u.description*/u.newDesc, u.x + 50,/* u.y + 200*/ /*u.y - 400 + i * 150*//* 180 + i * 400*//*180 + (i+1) * 250*/coordImgy.get(i) + 200, mPaint); //text
			//canvas.drawText(text, u.x + 300, /*u.y + 250*/ /*330 + i * 150*//*330 + i * 200*//*180*/220 + (i+1) * 300, mPaint); // km text
			t = (View) findViewById(R.id.drawSurfaceView);
			t.setOnClickListener(new OnClickListener(){
				@SuppressLint("LongLogTag")
				@Override
				public void onClick(View viewIn) {
					try {
						/*Intent intent = new Intent(this , MapsActivity. class);
						startActivity(intent);*/
						Intent i = new Intent(context.getApplicationContext(), MapsActivity.class);
						context.getApplicationContext().startActivity(i);

						Log.d(TAG,"GMAIL account selected");
					} catch (Exception except) {
						Log.e(TAG,"Ooops GMAIL account selection problem "+except.getMessage());
					}
				}
			});
		}
	}

	// uz judejima atsakingas!
	public void setOffset(float offset) {
		this.OFFSET = offset;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//int action = event.getAction();
		float x = event.getX(); // or getRawX();
		float y = event.getY();

		float xOfBitmap;
		float yOfBitmap;

			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					for (int i = 0; i < mSpots.length; i++) {
						Bitmap spot = mSpots[i];
						spot.getWidth();
						spot.getHeight();
						xOfBitmap = coordImgx.get(i);
						yOfBitmap = coordImgy.get(i);

						if (x >= xOfBitmap && x < (xOfBitmap + spot.getWidth())
								&& y >= yOfBitmap && y < (yOfBitmap + spot.getHeight())) {
							//HomeFragment.text = HomeActivity.props.get(i).description;
							HomeFragment.text = HomeActivity.props.get(i).getInfo(); //// Uzsetinamas tekstas HOME FRAGMENTE
							HomeFragment.placeName = HomeActivity.props.get(i).description;
							HomeFragment.address = "Adresas: " + HomeActivity.props.get(i).address;
							HomeFragment.type = HomeActivity.props.get(i).type;
							HomeFragment.distance = HomeActivity.props.get(i).distance;
							HomeFragment.icon =  HomeActivity.props.get(i).icon;
							///HomeFragment.image = HomeActivity.props.get(i).




							indexOfclick = i;
							/*if (x >= 0 && x < 200 && y >= 0 && y < 200) {*/
								//tada, if this is true, you've started your click inside your bitmap
								Log.d("PASPAUSTA", String.valueOf(HomeActivity.props.get(i).distance));
								Intent intent = new Intent(this.getContext(), TabActivity.class)  //?????
										.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								this.getContext().startActivity(intent);

							break;
							}
					}
				return true;
			}
		return false;
	}

	public void setMyLocation(double latitude, double longitude) {
		me.latitude = latitude;
		me.longitude = longitude;
	}

	@SuppressLint("LongLogTag")
	protected double distInMetres(Point me, Point u) {

		double lat1 = me.latitude;
		double lng1 = me.longitude;

		double lat2 = u.latitude;
		double lng2 = u.longitude;

		double earthRadius = 6371;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		//Log.d(TAG, String.valueOf(dist));
		return dist * 1000;
	}

	// AZIMUTAS
	protected static double bearing(double lat1, double lon1, double lat2, double lon2) {
		double longDiff = Math.toRadians(lon2 - lon1);
		double la1 = Math.toRadians(lat1);
		double la2 = Math.toRadians(lat2);
		double y = Math.sin(longDiff) * Math.cos(la2);
		double x = Math.cos(la1) * Math.sin(la2) - Math.sin(la1) * Math.cos(la2) * Math.cos(longDiff);

		double result = Math.toDegrees(Math.atan2(y, x));
		return (result+360.0d)%360.0d;
	}


	@Override
	public void onClick(View view) {

	}
}
