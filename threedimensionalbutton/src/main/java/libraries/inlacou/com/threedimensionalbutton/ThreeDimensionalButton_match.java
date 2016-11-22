package libraries.inlacou.com.threedimensionalbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by inlacoubyv on (6/11/15) 13/09/2016.
 */
public class ThreeDimensionalButton_match extends FrameLayout {

	private static final String DEBUG_TAG = ThreeDimensionalButton_match.class.getName();
	private int position;
	private Callbacks mCallback;
	private Context context;

	private View surfaceLayout, holder;
	private TextView button;
	private Rect rect;

	public ThreeDimensionalButton_match(Context context) {
		super(context);
		init();
	}

	public ThreeDimensionalButton_match(Context context, Callbacks callbacks) {
		super(context);
		this.context = context;
		mCallback = callbacks;
		init();
	}

	public ThreeDimensionalButton_match(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
		readAttrs(attrs);
	}

	public ThreeDimensionalButton_match(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
		readAttrs(attrs);
	}

	public void setCallback(Callbacks mCallback) {
		this.mCallback = mCallback;
	}

	private void init(){
		getData();
		initialize();
		setListeners();
	}

	public void getData(){
		if(mCallback!=null) {
		}
	}

	public void initialize(View view) {
		surfaceLayout = view.findViewById(R.id.view_base_layout_surface);
		button = (TextView) view.findViewById(R.id.button);
		holder = view.findViewById(R.id.holder);
	}

	private void initialize() {
		View rootView = inflate(context, R.layout.view_button_3d_match, this);
		initialize(rootView);
	}

	private void readAttrs(AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ThreeDimensionalButton_match, 0, 0);
		try {
			//(ta.getInt(R.styleable.ThreeDimensionalButton_color, limit));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_match_text)) setText(ta.getString(R.styleable.ThreeDimensionalButton_match_text));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_match_textColor)) setTextColor(ta.getColorStateList(R.styleable.ThreeDimensionalButton_match_textColor));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_match_textSize)) setTextSize(ta.getDimensionPixelSize(R.styleable.ThreeDimensionalButton_match_textSize, -1));
		} finally {
			ta.recycle();
		}
	}

	private void setTextSize(float size) {
		if(size>0) button.setTextSize(ViewUtils.pixelsToSp(context, size));
	}

	private void setTextColor(ColorStateList color) {
		button.setTextColor(color);
	}

	private void setText(String string) {
		if(string!=null && !string.isEmpty()) button.setText(string);
	}

	public void populate() {
	}

	private void setListeners() {
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mCallback!=null) mCallback.onSurfaceClick();
			}
		});
		button.setOnTouchListener(new OnTouchListener() {
			boolean inside, outside;

			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				//Log.d(DEBUG_TAG, view.getX() + ", " + view.getY() + " | " + motionEvent.getRawX() + ", " + motionEvent.getRawY());
				if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
					onEnter(view, motionEvent);
				} else if(motionEvent.getAction()==MotionEvent.ACTION_UP) {
					onExit(view, motionEvent);
				} else if(motionEvent.getAction()==MotionEvent.ACTION_MOVE) {
					if(!rect.contains(view.getLeft() + (int) motionEvent.getX(), view.getTop() + (int) motionEvent.getY())){
						// User moved outside bounds
						onExit(view, motionEvent);
					}else{
						// User moved inside bounds
						onEnter(view, motionEvent);
					}
				}
				return false;
			}

			private void onEnter(View view, MotionEvent motionEvent) {
				if(!inside){
					inside = true;
					outside = false;
				}else{
					return;
				}
				final float scale = getContext().getResources().getDisplayMetrics().density;
				RelativeLayout.LayoutParams paramsSpacer = (RelativeLayout.LayoutParams) holder.getLayoutParams();
				paramsSpacer.height = (int) (8 * scale + 0.5f);
				holder.setLayoutParams(paramsSpacer);
				rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
				button.setBackgroundResource(R.drawable.threedimensionalbutton_rectangle_bordered_grey_dark);
			}

			private void onExit(View view, MotionEvent motionEvent) {
				if(!outside){
					outside = true;
					inside = false;
				}else{
					return;
				}
				RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) holder.getLayoutParams();
				params2.height = (0);
				holder.setLayoutParams(params2);
				button.setBackgroundResource(R.drawable.threedimensionalbutton_rectangle_bordered_grey_dark_shadowed);
			}
		});
		button.requestLayout();
	}

	public void inAnimation(){
		Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		surfaceLayout.startAnimation(animation);
	}

	public void outAnimation(){
		Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		surfaceLayout.startAnimation(animation);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mCallback.onDelete();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
	}

	public interface Callbacks {
		void onSurfaceClick();
		void onDelete();
	}

}
