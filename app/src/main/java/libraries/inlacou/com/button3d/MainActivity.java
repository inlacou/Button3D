package libraries.inlacou.com.button3d;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import butterknife.OnClick;
import libraries.inlacou.com.threedimensionalbutton.ThreeDimensionalButton;

public class MainActivity extends AppCompatActivity {

	private static final String DEBUG_TAG = MainActivity.class.getName();
	ThreeDimensionalButton match, wrap, wrap3, wrap4, wrap6;
	private ThreeDimensionalButton currentButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ScrollView scrollview = findViewById(R.id.scroll);
		match = findViewById(R.id.match);
		wrap = findViewById(R.id.wrap);
		wrap3 = findViewById(R.id.wrap3);
		wrap4 = findViewById(R.id.wrap4);
		wrap6 = findViewById(R.id.wrap6);

		match.setCallback(new ThreeDimensionalButton.Callbacks() {
			@Override
			public void onKeyUp(ThreeDimensionalButton button) {
				Log.d(DEBUG_TAG, "onKeyUp");
				if(currentButton==button) currentButton = null;
			}

			@Override
			public void onKeyDown(ThreeDimensionalButton button) {
				Log.d(DEBUG_TAG, "onKeyDown");
				currentButton = button;
			}
		});

		scrollview.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				Log.d(DEBUG_TAG, "onTouch | currentButton: " + currentButton);
				if(currentButton!=null){
					currentButton.onTouchEvent(motionEvent);
				}
				return false;
			}
		});

		match.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "MATCH", Toast.LENGTH_SHORT).show();
				wrap.callOnClick();
			}
		});

		wrap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "WRAP", Toast.LENGTH_SHORT).show();
			}
		});
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			wrap3.setDrawableRight(getResources().getDrawable(R.drawable.documentacion_section, this.getTheme()));
		}else{
			wrap3.setDrawableRight(getResources().getDrawable(R.drawable.documentacion_section));
		}
		
		wrap4.setDrawablePadding(getResources().getDimension(R.dimen.threedimensionalbutton_general_all));

		wrap6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "WRAP6", Toast.LENGTH_SHORT).show();
				match.callOnClick();
			}
		});
	}

	@OnClick(R.id.wrap5)
	public void onWrap5ButtonClick(){
		Toast.makeText(MainActivity.this, "WRAP5", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev){
		Log.d(DEBUG_TAG, "dispatchTouchEvent");
		super.dispatchTouchEvent(ev);
		if(currentButton!=null) {
			return currentButton.onTouchEvent(ev);
		}else{
			return false;
		}
	}
}