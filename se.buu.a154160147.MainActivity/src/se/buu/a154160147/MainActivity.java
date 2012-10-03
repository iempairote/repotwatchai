package se.buu.a154160147;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;

public class MainActivity extends Activity {
	
	Button button;
	ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addListenerOnButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void addListenerOnButton() {
    	 
		image = (ImageView) findViewById(R.id.imageView1);
 
		button = (Button) findViewById(R.id.btnChangeImage);
		button.setOnClickListener(new OnClickListener() {
 
			
			public void onClick(View arg0) {
				image.setImageResource(R.drawable.thip);
			}
 
		});
	}
    
}
