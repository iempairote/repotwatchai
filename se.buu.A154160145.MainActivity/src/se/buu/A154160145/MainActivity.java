package se.buu.A154160145;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
 

    
    public class MainActivity extends Activity {
    	Button button;
    	ImageView image;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            addListenerOnButton();
        }
        public void addListenerOnButton() {
        	 
    		image = (ImageView) findViewById(R.id.imageView1);
     
    		button = (Button) findViewById(R.id.btnChangeImage);
    		button.setOnClickListener(new OnClickListener() {
     
    			public void onClick(View arg0) {
    				image.setImageResource(R.drawable.glfjgjpgtojetg5eg4r65yh4t6yn4tn45r5g46ww4gt6y4);
    			
    			}
     
    		});
     
    	}
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.activity_main, menu);
            return true;
     
    }
}