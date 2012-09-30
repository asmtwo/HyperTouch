package fr.hyperfiction.hypertouch;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import org.haxe.nme.GameActivity;

/**
 * ...
 * @author shoe[box]
 */

class HyperTouch implements View.OnTouchListener{

	public static GLSurfaceView mSurface;

	private ArrayList<View.OnTouchListener> gestures;

	private static String TAG = "HyperTouch";

	//JNI
		
		static public native void onTwix( float fx , float fy );
		static public native void onDoubleTap( float fx , float fy );
		static public native void onPan( int phase , float fx , float fy , float vx , float vy , float cx , float cy , float pressure );
		static public native void onPinch( float scale );
		static public native void onSwipe( int dir );
		
		static {
			System.loadLibrary( "hypertouch" ); 
		}

	// -------o constructor
		
		/**
		* constructor
		*
		* @param	
		* @return	void
		*/
		public HyperTouch() {
			trace("constructor");
			gestures = new ArrayList<View.OnTouchListener>( );
			mSurface = ( GLSurfaceView ) GameActivity.getInstance( ).getCurrentFocus( );	
			GameActivity.getInstance( ).getCurrentFocus( ).setOnTouchListener( this );
		}
	
	// -------o public
				
		/**
		* <code>SimpleOnGestureListener</code> onTouch Generic Method
		* 
		* @public
		* @return	void
		*/
		public boolean onTouch( View v , final MotionEvent ev) {
			
			boolean res = false;
			for( View.OnTouchListener g : gestures ){
				res = g.onTouch( v , ev );
				if( res )
					break;
			}

			return false;
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		public void add_gesture( View.OnTouchListener g ) {
			trace("add_gesture ::: "+g);
			gestures.add( g );
		}

	// -------o protected
	
		

	// -------o misc
		
		/**
		* 
		* 
		* @public
		* @return	void
		*/
		static public void trace( String s ){
			Log.i( TAG , s );
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		public static HyperTouch getInstance( ){
			trace("getInstance");
			if( __instance == null )
				__instance = new HyperTouch( );

			return __instance;
		}

		private static HyperTouch __instance = null;
}