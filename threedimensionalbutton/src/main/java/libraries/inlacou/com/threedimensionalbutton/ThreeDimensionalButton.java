package libraries.inlacou.com.threedimensionalbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
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
public abstract class ThreeDimensionalButton extends FrameLayout {

	private static final String DEBUG_TAG = ThreeDimensionalButton.class.getName();
	private Callbacks mCallback;
	private Context context;

	private View surfaceLayout, holder;
	private TextView textView;
	private View background;
	private Rect rect;

	private Drawable drawableLeft, drawableTop, drawableRight, drawableBottom;
	private OnClickListener listener;

	public ThreeDimensionalButton(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public ThreeDimensionalButton(Context context, Callbacks callbacks) {
		super(context);
		this.mCallback = callbacks;
		this.context = context;
	}

	public ThreeDimensionalButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public ThreeDimensionalButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
	}

	public void setCallback(Callbacks mCallback) {
		this.mCallback = mCallback;
	}

	protected void init(){
		getData();
		setListeners(true);
	}

	public void getData(){
		if(mCallback!=null) {
		}
	}

	public void initialize(View view) {
		surfaceLayout = view.findViewById(R.id.view_base_layout_surface);
		textView = (TextView) view.findViewById(R.id.textview);
		background = view.findViewById(R.id.button_background);
		holder = view.findViewById(R.id.holder);
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			button.setCompoundDrawables(
					drawableLeft!=-1 ? getResources().getDrawable(drawableLeft, context.getTheme()) : null,
					drawableTop!=-1 ? getResources().getDrawable(drawableTop, context.getTheme()) : null,
					drawableRight!=-1 ? getResources().getDrawable(drawableRight, context.getTheme()) : null,
					drawableBottom!=-1 ? getResources().getDrawable(drawableBottom, context.getTheme()) : null);
		}else{
			button.setCompoundDrawables(
					drawableLeft!=-1 ? getResources().getDrawable(drawableLeft) : null,
					drawableTop!=-1 ? getResources().getDrawable(drawableTop) : null,
					drawableRight!=-1 ? getResources().getDrawable(drawableRight) : null,
					drawableBottom!=-1 ? getResources().getDrawable(drawableBottom) : null);
		}*/
	}

	public void setBackground(){

	}

	public void setDrawableLeft(Drawable drawable){
		Log.d(DEBUG_TAG, "setDrawableLeft");
		drawableLeft = drawable;
		textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
	}

	public void setDrawableRight(Drawable drawable){
		Log.d(DEBUG_TAG, "setDrawableRight");
		drawableRight = drawable;
		textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
	}

	public void setDrawableBottom(Drawable drawable){
		Log.d(DEBUG_TAG, "setDrawableBottom");
		drawableBottom = drawable;
		textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
	}

	public void setDrawableTop(Drawable drawable){
		Log.d(DEBUG_TAG, "setDrawableTop");
		drawableTop = drawable;
		textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
	}

	public void setDrawablePadding(float drawablePadding){
		textView.setCompoundDrawablePadding((int) drawablePadding);
	}

	protected void readAttrs(AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ThreeDimensionalButton, 0, 0);
		try {
			//(ta.getInt(R.styleable.ThreeDimensionalButton_color, limit));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_text)) setText(ta.getString(R.styleable.ThreeDimensionalButton_text));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_textColor)) setTextColor(ta.getColorStateList(R.styleable.ThreeDimensionalButton_textColor));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_textSize)) setTextSize(ta.getDimensionPixelSize(R.styleable.ThreeDimensionalButton_textSize, -1));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_drawableLeft)) setDrawableLeft(ta.getDrawable(R.styleable.ThreeDimensionalButton_drawableLeft));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_drawableRight)) setDrawableRight(ta.getDrawable(R.styleable.ThreeDimensionalButton_drawableRight));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_drawableBottom)) setDrawableBottom(ta.getDrawable(R.styleable.ThreeDimensionalButton_drawableBottom));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_drawableTop)) setDrawableTop(ta.getDrawable(R.styleable.ThreeDimensionalButton_drawableTop));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_drawablePadding)) setDrawablePadding(ta.getDimension(R.styleable.ThreeDimensionalButton_drawablePadding, 0f));
			if(ta.hasValue(R.styleable.ThreeDimensionalButton_enabled)) setEnabled(ta.getBoolean(R.styleable.ThreeDimensionalButton_enabled, true));
		} finally {
			ta.recycle();
		}
	}

	public void setTextSize(float size) {
		if(size>0) textView.setTextSize(ViewUtils.pixelsToSp(context, size));
	}

	public void setTextColor(ColorStateList color) {
		textView.setTextColor(color);
	}

	public void setText(String string) {
		if(string!=null && !string.isEmpty()) textView.setText(string);
	}

	public void populate() {
	}

	public void setOnClickListener(OnClickListener onClickListener){
		Log.d(DEBUG_TAG+".setOnClickListener", "setOnClickListener");
		background.setOnClickListener(onClickListener);
		listener = onClickListener;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		setListeners(enabled);
	}

	@Override
	public boolean callOnClick() {
		if(listener!=null) {
			listener.onClick(background);
			return true;
		}
		return false;
	}

	private void setListeners(boolean enabled) {
		background.setOnClickListener(enabled?(listener!=null?listener:new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		}):null);
		background.setOnTouchListener(enabled?new OnTouchListener() {
			boolean inside, outside;

			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				Log.d(DEBUG_TAG+".onTouch", view.getX() + ", " + view.getY() + " | " + motionEvent.getRawX() + ", " + motionEvent.getRawY());
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
				Log.d(DEBUG_TAG+".onEnter", view.getX() + ", " + view.getY() + " | " + motionEvent.getRawX() + ", " + motionEvent.getRawY());
				if(!inside){
					Log.d(DEBUG_TAG+".onEnter", "inside: " + inside);
					inside = true;
					if(mCallback!=null) mCallback.onKeyDown(ThreeDimensionalButton.this);
					outside = false;
				}else{
					Log.d(DEBUG_TAG+".onEnter", "inside: " + inside);
					return;
				}
				final float scale = getContext().getResources().getDisplayMetrics().density;
				RelativeLayout.LayoutParams paramsSpacer = (RelativeLayout.LayoutParams) holder.getLayoutParams();
				paramsSpacer.height = (int) (8 * scale + 0.5f);
				holder.setLayoutParams(paramsSpacer);
				rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
				background.setBackgroundResource(R.drawable.threedimensionalbutton_rectangle_bordered_grey_dark);
			}

			private void onExit(View view, MotionEvent motionEvent) {
				Log.d(DEBUG_TAG+".onExit", view.getX() + ", " + view.getY() + " | " + motionEvent.getRawX() + ", " + motionEvent.getRawY());
				if(!outside){
					outside = true;
					if(mCallback!=null) mCallback.onKeyUp(ThreeDimensionalButton.this);
					inside = false;
				}else{
					return;
				}
				RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) holder.getLayoutParams();
				params2.height = (0);
				holder.setLayoutParams(params2);
				background.setBackgroundResource(R.drawable.threedimensionalbutton_rectangle_bordered_grey_dark_shadowed);
			}
		}:null);
		background.requestLayout();
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
		void onKeyUp(ThreeDimensionalButton button);
		void onKeyDown(ThreeDimensionalButton button);
		void onDelete();
	}

}
