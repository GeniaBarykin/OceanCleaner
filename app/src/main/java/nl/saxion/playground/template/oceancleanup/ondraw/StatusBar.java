package nl.saxion.playground.template.oceancleanup.ondraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author genia
 */
public class StatusBar extends View {

    private int parameter;

    private int maxValue;

    private boolean fuel;

    private boolean time;

    public StatusBar(Context context) {
        super(context);
    }

    public StatusBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StatusBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                     int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAsTime() {
        this.time = true;
        fuel = false;
    }

    public boolean isTime() {
        return time;
    }

    public void setHp(int parameter) {
        this.parameter = parameter;
        invalidate();
    }

    public void setFuel() {
        this.fuel = true;
        time = false;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        Paint paint = getPaint();

        float total = (width / maxValue) * parameter;
        canvas.drawRect(0, height, total, 0, paint);
    }

    private Paint getPaint() {
        Paint paint = new Paint();
        if (fuel) {
            paint.setColor(Color.BLACK);
        } else if (time) {
            System.out.println("Time initialized");
            paint.setColor(Color.CYAN);
        } else {
            paint.setColor(Color.GREEN);
        }
        return paint;
    }
}