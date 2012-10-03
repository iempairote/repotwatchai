package com.coreservlets.customcomponents;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class CustomComponentsLauncher extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /** Switches to the MeasurementsActivity when the associated button is clicked. 
     *  You must also register the new Activity in AndroidManifest.xml. 
     */
    public void launchMeasurementsActivity(View clickedButton) {
        Intent activityIntent = 
                new Intent(this, MeasurementsActivity.class);
        startActivity(activityIntent);
    }
    
    /** Switches to the CustomAttributesActivity when the associated button is clicked.  */
    
    public void launchCustomAttributesActivity(View clickedButton) {
        Intent activityIntent = 
                new Intent(this, CustomAttributesActivity.class);
        startActivity(activityIntent);
    }
    
}
