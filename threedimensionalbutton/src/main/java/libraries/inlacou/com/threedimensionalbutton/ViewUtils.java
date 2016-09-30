package libraries.inlacou.com.threedimensionalbutton;

import android.content.Context;

/**
 * Created by inlacou on 30/09/16.
 */

public class ViewUtils {

	public static float pixelsToSp(Context context, float px) {
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return px/scaledDensity;
	}

}
