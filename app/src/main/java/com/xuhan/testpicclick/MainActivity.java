package com.xuhan.testpicclick;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView imageView;

    private final static int MARGIN = 90;
    private final static int PADDING = 5;
    private boolean isClickLeft = false;
    private boolean isClickTop = false;
    private boolean isClickRight = false;
    private boolean isClickBottom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.btn_id);
        imageView.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int width = view.getWidth();
        int height = view.getHeight();
        int touchX = (int) motionEvent.getX();
        int touchY = (int) motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //获取 上下左右 四个区域的矩形区域
                Rect rectLeft = new Rect(0, height / 4, width / 2, height / 4 * 3);
                Rect rectTop = new Rect(width / 4, 0, width / 4 * 3, height / 2);
                Rect rectRight = new Rect(width / 2, height / 4, width, height / 4 * 3);
                Rect rectBottom = new Rect(width / 4, height / 2, width / 4 * 3, height);

                // Path 绘制 上下左右 的菱形区域
                // Region 设置菱形区域
                Path pathLeft = new Path();
                pathLeft.moveTo(0 + MARGIN, height / 2);
                pathLeft.lineTo(width / 4, height / 4 + PADDING);
                pathLeft.lineTo(width / 2 - PADDING, height / 2);
                pathLeft.lineTo((width / 4), height / 4 * 3 - PADDING);
                pathLeft.close();
                Region regionLeft = new Region();
                regionLeft.setPath(pathLeft, new Region(rectLeft));

                Path pathTop = new Path();
                pathTop.moveTo(width / 4 + PADDING, height / 4);
                pathTop.lineTo(width / 2, 0 + MARGIN);
                pathTop.lineTo(width / 4 * 3 - PADDING, height / 2);
                pathTop.lineTo(width / 2, height / 2 - PADDING);
                pathTop.close();
                Region regionTop = new Region();
                regionTop.setPath(pathTop, new Region(rectTop));

                Path pathRight = new Path();
                pathRight.moveTo(width / 2 + PADDING, height / 2);
                pathRight.lineTo(width / 4 * 3, height / 4 + PADDING);
                pathRight.lineTo(width - MARGIN, height / 2);
                pathRight.lineTo(width / 4 * 3, height / 4 * 3 - PADDING);
                pathRight.close();
                Region regionRight = new Region();
                regionRight.setPath(pathRight, new Region(rectRight));

                Path pathBottom = new Path();
                pathBottom.moveTo(width / 4 + PADDING, height / 4 * 3);
                pathBottom.lineTo(width / 2, height / 2 + PADDING);
                pathBottom.lineTo(width / 4 * 3 - PADDING, height / 4 * 3);
                pathBottom.lineTo(width / 2, height - MARGIN);
                pathBottom.close();
                Region regionBottom = new Region();
                regionBottom.setPath(pathBottom, new Region(rectBottom));

                if (regionLeft.contains(touchX, touchY)) {
                    clickLeft();
                } else if (regionTop.contains(touchX, touchY)) {
                    clickTop();
                } else if (regionRight.contains(touchX, touchY)) {
                    clickRight();
                } else if (regionBottom.contains(touchX, touchY)) {
                    clickBottom();
                }
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                imageView.setImageResource(R.drawable.btn);
                if (isClickLeft) {
                    // TODO 响应左侧事件
                    Log.i("tttt-click--", "left");
                }
                if (isClickTop) {
                    Log.i("tttt-click--", "top");
                }
                if (isClickRight) {
                    Log.i("tttt-click--", "right");
                }
                if (isClickBottom) {
                    Log.i("tttt-click--", "bottom");
                }
                isClickLeft = false;
                isClickTop = false;
                isClickRight = false;
                isClickBottom = false;
                break;
        }

        return true;
    }

    private void clickLeft() {
        isClickLeft = true;
        imageView.setImageResource(R.drawable.btn_jieshao_on);
    }

    private void clickTop() {
        isClickTop = true;
        imageView.setImageResource(R.drawable.btn_daozhen_on);
    }

    private void clickRight() {
        isClickRight = true;
        imageView.setImageResource(R.drawable.btn_daohang_on);
    }

    private void clickBottom() {
        isClickBottom = true;
        imageView.setImageResource(R.drawable.btn_jiankang_on);
    }
}
