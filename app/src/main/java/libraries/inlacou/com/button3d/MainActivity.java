package libraries.inlacou.com.button3d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import libraries.inlacou.com.threedimensionalbutton.ThreeDimensionalButton;

public class MainActivity extends AppCompatActivity {

	ThreeDimensionalButton match, wrap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		match = (ThreeDimensionalButton) findViewById(R.id.match);
		wrap = (ThreeDimensionalButton) findViewById(R.id.wrap);

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
	}
}