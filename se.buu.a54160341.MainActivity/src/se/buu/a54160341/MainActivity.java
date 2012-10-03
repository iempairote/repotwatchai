package se.buu.a54160341;

import se.buu.a54160341.R.menu;
import android.os.Bundle;
import android.app.Activity;

import android.widget.Button;
import android.widget.ImageView;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	Button button,button2;
	ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
                
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void addListenerOnButton() {
    	 
		image = (ImageView) findViewById(R.id.imageView1);
		
			button = (Button) findViewById(R.id.btnChangeImage);
			button.setOnClickListener(new OnClickListener() {
 
				public void onClick(View arg0) {
					image.setImageResource(R.drawable.pic02);
				} 
				
			});
		
    }
}
