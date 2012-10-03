package burapha.se.changeimage;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;


public class MainActivity extends Activity implements ViewFactory {

    private Integer[] mImageIds = {
    		
            R.drawable.kak,
            R.drawable.softeng,
            R.drawable.tawatchai,
            /*
            R.drawable.pic_d,
          
            R.drawable.pic_e,
            R.drawable.pic_f,
            R.drawable.pic_g,
            R.drawable.pic_h,
            R.drawable.pic_i
            */
    };
    
	ImageSwitcher iSwitcher;
	Button btnNext;
	Button btnPrev;
	int position = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
       // imageSwitcher1
		iSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
		iSwitcher.setFactory(this);
		iSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		iSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));    
		
		iSwitcher.setImageResource(mImageIds[0]);

		
    	btnNext = (Button) findViewById(R.id.Button01);
    	btnNext.setOnClickListener(new View.OnClickListener() {
    		   public void onClick(View v) {  
    			   setPositionNext();
    			   iSwitcher.setImageResource(mImageIds[position]); 
    			   Toast.makeText(MainActivity.this, "Your selected position = " + getResources().getResourceName(mImageIds[position]), Toast.LENGTH_SHORT).show();
    			}    
    	});  
        
    	btnPrev = (Button) findViewById(R.id.Button02);
    	btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	setPositionPrev();
            	iSwitcher.setImageResource(mImageIds[position]);
            	Toast.makeText(MainActivity.this, "Your selected position = " + getResources().getResourceName(mImageIds[position]), Toast.LENGTH_SHORT).show();
            }
        }
    	);    	

    }
    
    public void setPositionNext()
    {
    	position++;
    	if(position > mImageIds.length -1)
    	{
    		position = 0;
    	}
    }
    
    public void setPositionPrev()
    {
    	position--;
    	if(position < 0)
    	{
    		position = mImageIds.length - 1;
    	} 	
    }
    
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iView.setLayoutParams(new 
				ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		iView.setBackgroundColor(0xFF000000);
		return iView;
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
