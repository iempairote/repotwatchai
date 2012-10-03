package com.coreservlets.customcomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomizableCircleTextView extends View {
    private int mDefaultTextSize = 20;
    private int mTextColor = Color.BLUE;
    private Paint mPaint;
    private Paint mTestPaint = makePaint(mTextColor);
    private static final String DEFAULT_MESSAGE = "Android";
    private String mMessage = DEFAULT_MESSAGE;
    
    // If made programmatically and added with setContentView
    
    public CustomizableCircleTextView(Context context) {
        super(context);
        mPaint = makePaint(mTextColor);
    }
    
    // If made from XML file
    
    public CustomizableCircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeCustomAttributes(attrs);
        mPaint = makePaint(mTextColor);
    }
    
    private void initializeCustomAttributes(AttributeSet attrs) {
        TypedArray attributeArray = 
            getContext().obtainStyledAttributes(attrs, 
                                                R.styleable.CustomizableCircleTextView);
        mDefaultTextSize = 
            attributeArray.getInt(R.styleable.CustomizableCircleTextView_default_text_size, 
                                  mDefaultTextSize);
        mTextColor = 
            attributeArray.getInt(R.styleable.CustomizableCircleTextView_text_color, 
                                  mTextColor);
        mMessage = 
            attributeArray.getString(R.styleable.CustomizableCircleTextView_text_message);
        if (mMessage == null) {
            mMessage = DEFAULT_MESSAGE;
        }
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        int textWidth= width - getPaddingLeft() - getPaddingRight();
        int textHeight = height - getPaddingTop() - getPaddingBottom();
        adjustTextSizeToFit(Math.min(textWidth, textHeight));
        setMeasuredDimension(width, height);
    }
    
    private int measureWidth(int measureSpec) {
        return(measureText(measureSpec, getPaddingLeft(), getPaddingRight()));
    }
    
    private int measureHeight(int measureSpec) {
        return(measureText(measureSpec, getPaddingTop(), getPaddingBottom()));
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
    
    private Paint makePaint(int color) {
        // Angled lines look much better with antialiasing
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(color);
        p.setTextSize(mDefaultTextSize); // Default size (e.g., for wrap_content)
        return(p);
    }
    
    private void adjustTextSizeToFit(int availableLength) {
        int textSize = 0;
        for(int i=1; i<=10; i++) {
            textSize = i * 10;
            mTestPaint.setTextSize(textSize);
            int requiredLength = 
                    2 * (int)mTestPaint.measureText(mMessage);
            if (requiredLength > availableLength) {
                break;
            }
        }
        mPaint.setTextSize(Math.max(10, textSize - 10));
    }
}