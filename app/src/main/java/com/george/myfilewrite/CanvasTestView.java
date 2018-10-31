package com.george.myfilewrite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by George.ren on 2018/10/29.
 * Email:1063658094@qq.com
 * Describe:钟表
 */
public class CanvasTestView extends View {
    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private void initCirclePaint() {
        circlePaint.setColor(Color.parseColor("#000000"));
        circlePaint.setStrokeWidth(5);
        circlePaint.setStyle(Paint.Style.STROKE);
    }

    private Paint keduPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private void initKeduPaint() {
        keduPaint.setStrokeWidth(3);
        keduPaint.setColor(Color.parseColor(("#000000")));
        keduPaint.setStyle(Paint.Style.STROKE);
    }

    public CanvasTestView(Context context) {
        this(context, null);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCirclePaint();
        initKeduPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 圆盘
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 4, circlePaint);

        // 刻度
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                keduPaint.setStrokeWidth(3);
                keduPaint.setTextSize(30);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 4,
                        getWidth() / 2, getHeight() / 2 - getWidth() / 4 + 50, keduPaint);
                String degree = String.valueOf(i);
                canvas.drawText(degree, getWidth() / 2 - keduPaint.measureText(degree) / 2,
                        getHeight() / 2 - getWidth() / 4 + 80, keduPaint);
            } else {
                keduPaint.setStrokeWidth(1);
                keduPaint.setTextSize(15);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 4, getWidth() / 2,
                        getHeight() / 2 - getWidth() / 4 + 25, keduPaint);
                String degree = String.valueOf(i);
                canvas.drawText(degree, getWidth() / 2 - keduPaint.measureText(degree) / 2,
                        getHeight() / 2 - getWidth() / 4 + 60, keduPaint);
            }
            canvas.rotate(15,getWidth()/2,getHeight()/2);
        }

        // 指针
        Paint hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setColor(Color.parseColor("#000000"));
        hourPaint.setStrokeWidth(20);
        hourPaint.setStyle(Paint.Style.STROKE);

        Paint minutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minutePaint.setColor(Color.parseColor("#000000"));
        minutePaint.setStrokeWidth(10);
        minutePaint.setStyle(Paint.Style.STROKE);

        canvas.save();
        //将坐标系平移到圆点
        canvas.translate(getWidth()/2, getHeight()/2);
        canvas.drawLine(0, 0, 100, 100, hourPaint);
        canvas.drawLine(0, 0, 100, 150, minutePaint);
        canvas.restore();
    }
}
