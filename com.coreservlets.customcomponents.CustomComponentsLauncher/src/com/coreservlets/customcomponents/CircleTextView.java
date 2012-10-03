package com.coreservlets.customcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleTextView extends View {
    private int mDefaultTextSize = 20;
    private int mTextColor = Color.BLUE;
    private Paint mPaint = makePaint(mTextColor);
    private Paint mTestPaint = makePaint(mTextColor);
    private String mMessage = "Android";
   
    
    // If made programmatically and added with setContentView
    
    public CircleTextView(Context context) {
        super(context);
    }
    
    // If made from XML file
    
    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        canvas.translate(viewWidth/2, viewHeight/2);
        for(int i=0; i<10; i++) {
            canvas.drawText(mMessage, 0, 0, mPaint);
            canvas.rotate(36);
        }
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, 
                             int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        int textWidth = 
                width - getPaddingLeft() - getPaddingRight();
        int textHeight = 
                height - getPaddingTop() - getPaddingBottom();
        adjustTextSizeToFit(Math.min(textWidth, textHeight));
        setMeasuredDimension(width, height);
    }
    
    private int measureWidth(int measureSpec) {
        return(measureText(measureSpec, getPaddingLeft(), 
                           getPaddingRight()));
    }
    
    private int measureHeight(int measureSpec) {
        return(measureText(measureSpec, getPaddingTop(), 
                           getPaddingBottom()));
    }
    
    private int measureText(int measureSpec, int padding1, int padding2) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be, so set text size to make it fit
            result = specSize;
        } else {
            // Measure the text: twice text size plus padding
            result = 2*(int)mPaint.measureText(mMessage) + 
                     padding1 + padding2;
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return(result);
    }
    
    private void adjustTextSizeToFit(int availableLength) {
        int textSize = 0;
        for(int i=1; i<=10; i++) {
            textSize = i * 10;
            mTestPaint.setTextSize(textSize);
            int requiredLength = 2 * (int)mTestPaint.measureText(mMessage);
            if (requiredLength > availableLength) {
                break;
            }
        }
        mPaint.setTextSize(Math.max(10, textSize - 10));
    }
    
    private Paint makePaint(int color) {
        // Angled lines look much better with antialiasing
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(color);
        p.setTextSize(mDefaultTextSize); // Default size (e.g., for wrap_content)
        return(p);
    }
}