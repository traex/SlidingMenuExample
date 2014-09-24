package com.andexert.slidingmenuexample;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by TraeX on 24/09/2014.
 */
public class SlidingPanel extends SlidingPaneLayout implements SlidingPaneLayout.PanelSlideListener
{
    private Boolean isOpened = false;
    private float actiondown = 0;
    private Integer menuWidthClosed;
    private Integer menuWidthExpand;

    public SlidingPanel(Context context)
    {
        super(context);
        setPanelSlideListener(this);
    }

    public SlidingPanel(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
        setPanelSlideListener(this);
    }

    public SlidingPanel(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
        setPanelSlideListener(this);
    }

    private void init()
    {
        menuWidthClosed = getResources().getDimensionPixelOffset(R.dimen.menu_width_closed);
        menuWidthExpand = getResources().getDimensionPixelOffset(R.dimen.menu_width_expand);
        setSliderFadeColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (ev.getAction() == MotionEvent.ACTION_DOWN)
        {
            actiondown = ev.getX();
            return super.onInterceptTouchEvent(ev);
        }

        return (ev.getAction() == MotionEvent.ACTION_MOVE &&
                ((isOpened && actiondown > ev.getX() && actiondown > menuWidthExpand) ||
                        (!isOpened && actiondown < ev.getX() && actiondown < menuWidthClosed)));
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset)
    {
        Log.e("Menu offset", "" + slideOffset);
        App.getBus().post(new OnDragMenu(slideOffset));
    }

    @Override
    public void onPanelOpened(View panel)
    {
        Log.e("Is menu opened ?", "Yeah !");
        App.getBus().post(new OnPanelStateChanged(true));
        isOpened = true; // Mandatory to know the slidingpanel state
    }

    @Override
    public void onPanelClosed(View panel)
    {
        Log.e("Is menu opened ?", "Nooo !");
        isOpened = false; // Mandatory to know the slidingpanel state
        App.getBus().post(new OnPanelStateChanged(false));
    }
}
