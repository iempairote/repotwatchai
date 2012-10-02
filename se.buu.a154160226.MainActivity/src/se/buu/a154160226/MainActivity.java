package se.buu.a154160226;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;


@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements 
		OnItemSelectedListener, ViewFactory {
	private Integer[] mThumbIds = { R.drawable.max,
			R.drawable.pam_thumb, R.drawable.ploy_thumb, R.drawable.dream_thumb, R.drawable.aum_thumb, R.drawable.thip_thumb
	};
	
	private Integer[] mImageIds = { R.drawable.max,
			R.drawable.pam, R.drawable.ploy, R.drawable.dream, R.drawable.aum, R.drawable.thip
	};

	private Context mContext;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mSwitcher = (ImageSwitcher) findViewById(R.id.imgswitcher);
        mSwitcher.setFactory((ViewFactory) this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
    
        Gallery g = (Gallery) findViewById(R.id.gallery);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemSelectedListener((OnItemSelectedListener) this);
    }
    
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
    	mSwitcher.setImageResource(mImageIds[position]);
    }
    
    public void onNothingSelected(AdapterView<?> parent) {
    }
    
    public View makeView() {
    	ImageView i = new ImageView(this);
    	i.setBackgroundColor(0xFF000000);
    	i.setScaleType(ImageView.ScaleType.FIT_CENTER);
    	i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    	return i;
    }
    
    private ImageSwitcher mSwitcher;
    
    public class ImageAdapter extends BaseAdapter {
    	public ImageAdapter(Context c) {
    		mContext = c;
    	}
    	
    	public int getCount() {
    		return mThumbIds.length;
    	}
    	
    	public Object getItem(int position) {
    		return position;
    	}
    	
    	public long getItemId(int position) {
    		return position;
    	}
    	
    	public View getView (int position, View convertView, ViewGroup parent) {
    		ImageView i = new ImageView(mContext);
    		i.setImageResource(mThumbIds[position]);
    		i.setAdjustViewBounds(true);
    		i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    		i.setBackgroundColor(Color.WHITE);
    		return i;
    	}
    }
}