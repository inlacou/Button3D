package libraries.inlacou.com.button3d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import libraries.inlacou.com.threedimensionalbutton.ThreeDimensionalButton;

public class MainActivity extends AppCompatActivity {

	ThreeDimensionalButton match, wrap, wrap3, wrap4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		match = (ThreeDimensionalButton) findViewById(R.id.match);
		wrap = (ThreeDimensionalButton) findViewById(R.id.wrap);
		wrap3 = (ThreeDimensionalButton) findViewById(R.id.wrap3);
		wrap4 = (ThreeDimensionalButton) findViewById(R.id.wrap4);

		match.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "MATCH", Toast.LENGTH_SHORT).show();
			}
		});

		wrap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "WRAP", Toast.LENGTH_SHORT).show();
			}
		});

		wrap3.setDrawableRight(getResources().getDrawable(R.drawable.documentacion_section, this.getTheme()));

		wrap4.setDrawablePadding(getResources().getDimension(R.dimen.threedimensionalbutton_general_all));
	}
}