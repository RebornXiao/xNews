package com.xnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HorizontalScrollViewPager extends LazyViewPager  {

    private int downX;
    private int downY;

    public HorizontalScrollViewPager(Context context) {
        super(context);
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();

                int diffX = downX - moveX;
                int diffY = downY - moveY;

                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (getCurrentItem() == 0 && diffX < 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (getCurrentItem() == (getAdapter().getCount() - 1)
                            && diffX > 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
