package libraries.inlacou.com.threedimensionalbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by inlacoubyv on (6/11/15) 13/09/2016.
 */
public class ThreeDimensionalButton_wrap extends ThreeDimensionalButton {

	private static final String DEBUG_TAG = ThreeDimensionalButton_wrap.class.getName();
	private Context context;

	public ThreeDimensionalButton_wrap(Context context) {
		super(context);
		initialize();
		super.init();
	}

	public ThreeDimensionalButton_wrap(Context context, Callbacks callbacks) {
		super(context, callbacks);
		this.context = context;
		initialize();
		super.init();
	}

	public ThreeDimensionalButton_wrap(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initialize();
		super.init();
		super.readAttrs(attrs);
	}

	public ThreeDimensionalButton_wrap(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initialize();
		super.init();
		super.readAttrs(attrs);
	}

	private void initialize() {
		View rootView = inflate(context, R.layout.view_button_3d_wrap, this);
		initialize(rootView);
	}

	@Override
	public void setOnClickListener(OnClickListener onClickListener) {
		super.setOnClickListener(onClickListener);
	}
}