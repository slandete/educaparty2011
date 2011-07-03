/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.slandete.android.apps.educaparty.es.ui;

import java.util.TimeZone;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;
import android.widget.TextView;

import com.slandete.android.apps.educaparty.R;
import com.slandete.android.apps.educaparty.es.Setup;
import com.slandete.android.apps.educaparty.provider.ScheduleContract.Blocks;
import com.slandete.android.apps.educaparty.util.MathUtils;
import com.slandete.android.apps.educaparty.util.ParserUtils;
import com.slandete.android.apps.educaparty.util.UIUtils;

public class ScheduleActivity extends TabActivity {

    /** Flags used with {@link DateUtils#formatDateRange}. */
    private static final int TIME_FLAGS = DateUtils.FORMAT_SHOW_DATE
            | DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_ABBREV_ALL;

    private static final String TAG_MON = "monday";
    private static final String TAG_TUE = "tuesday";
    private static final String TAG_WED = "wednesday";
    private static final String TAG_THU = "thursday";
    private static final String TAG_FRI = "friday";
    private static final String TAG_SAT = "saturday";
    private static final String TAG_SUN = "sunday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ((TextView) findViewById(R.id.title_text)).setText(getTitle());

        
        final long monStart = ParserUtils.parseTime(Setup.DAY1_START);
        final long monEnd = ParserUtils.parseTime(Setup.DAY1_END);
        final long tueStart = ParserUtils.parseTime(Setup.DAY2_START);
        final long tueEnd = ParserUtils.parseTime(Setup.DAY2_END);
        final long wedStart = ParserUtils.parseTime(Setup.DAY3_START);
        final long wedEnd = ParserUtils.parseTime(Setup.DAY3_END);
        final long thuStart = ParserUtils.parseTime(Setup.DAY4_START);
        final long thuEnd = ParserUtils.parseTime(Setup.DAY4_END);
        final long friStart = ParserUtils.parseTime(Setup.DAY5_START);
        final long friEnd = ParserUtils.parseTime(Setup.DAY5_END);
        final long satStart = ParserUtils.parseTime(Setup.DAY6_START);
        final long satEnd = ParserUtils.parseTime(Setup.DAY6_END);
        final long sunStart = ParserUtils.parseTime(Setup.DAY7_START);
        final long sunEnd = ParserUtils.parseTime(Setup.DAY7_END);

        setupBlocksTab(TAG_MON, monStart,monEnd);
        setupBlocksTab(TAG_TUE, tueStart,tueEnd);
        setupBlocksTab(TAG_WED, wedStart,wedEnd);
        setupBlocksTab(TAG_THU, thuStart,thuEnd);
        setupBlocksTab(TAG_FRI, friStart,friEnd);
        setupBlocksTab(TAG_SAT, satStart,satEnd);
        //setupBlocksTab(TAG_SUN, sunStart,sunEnd);

        
        
        
        
        final long now = System.currentTimeMillis();
        if ((now >= monStart)&(now < tueStart)) {
            getTabHost().setCurrentTabByTag(TAG_MON);
        } else if ((now >= tueStart)&(now < wedStart)) {
            getTabHost().setCurrentTabByTag(TAG_TUE);
        } else if((now >= wedStart)&(now < thuStart)) {
            getTabHost().setCurrentTabByTag(TAG_WED);
        } else if((now >= thuStart)&(now < friStart)) {
            getTabHost().setCurrentTabByTag(TAG_THU);
        } else if((now >= friStart)&(now < satStart)) {
            getTabHost().setCurrentTabByTag(TAG_FRI);
        } else if((now >= satStart)&(now < sunStart)) {
            getTabHost().setCurrentTabByTag(TAG_SAT);
        } else if((now >= sunStart)&(now < sunEnd)) {
            getTabHost().setCurrentTabByTag(TAG_SUN);
        } else {
            // Otherwise start with first day
            getTabHost().setCurrentTabByTag(TAG_MON);
        }
    }

    public static class FlingableTabHost extends TabHost {
        GestureDetector mGestureDetector;

        Animation mRightInAnimation;
        Animation mRightOutAnimation;
        Animation mLeftInAnimation;
        Animation mLeftOutAnimation;

        public FlingableTabHost(Context context, AttributeSet attrs) {
            super(context, attrs);

            mRightInAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_right_in);
            mRightOutAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_right_out);
            mLeftInAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_left_in);
            mLeftOutAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_left_out);

            final int minScaledFlingVelocity = ViewConfiguration.get(context)
                    .getScaledMinimumFlingVelocity() * 10; // 10 = fudge by experimentation

            mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                        float velocityY) {
                    int tabCount = getTabWidget().getTabCount();
                    int currentTab = getCurrentTab();
                    if (Math.abs(velocityX) > minScaledFlingVelocity &&
                        Math.abs(velocityY) < minScaledFlingVelocity) {

                        final boolean right = velocityX < 0;
                        final int newTab = MathUtils.constrain(currentTab + (right ? 1 : -1),
                                0, tabCount - 1);
                        if (newTab != currentTab) {
                            // Somewhat hacky, depends on current implementation of TabHost:
                            // http://android.git.kernel.org/?p=platform/frameworks/base.git;a=blob;
                            // f=core/java/android/widget/TabHost.java
                            View currentView = getCurrentView();
                            setCurrentTab(newTab);
                            View newView = getCurrentView();

                            newView.startAnimation(right ? mRightInAnimation : mLeftInAnimation);
                            currentView.startAnimation(
                                    right ? mRightOutAnimation : mLeftOutAnimation);
                        }
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            if (mGestureDetector.onTouchEvent(ev)) {
                return true;
            }
            return super.onInterceptTouchEvent(ev);
        }
    }

    private void setupBlocksTab(String tag, long startMillis, long endMillis) {
        final TabHost host = getTabHost();

        final Uri blocksBetweenDirUri = Blocks.buildBlocksBetweenDirUri(startMillis, endMillis);

        final Intent intent = new Intent(Intent.ACTION_VIEW, blocksBetweenDirUri);
        intent.addCategory(Intent.CATEGORY_TAB);

        intent.putExtra(BlocksActivity.EXTRA_TIME_START, startMillis);
        intent.putExtra(BlocksActivity.EXTRA_TIME_END, endMillis);

        TimeZone.setDefault(UIUtils.CONFERENCE_TIME_ZONE);
        final String label = DateUtils.formatDateTime(this, startMillis, TIME_FLAGS);
        host.addTab(host.newTabSpec(tag)
                .setIndicator(buildIndicator(label))
                .setContent(intent));
    }

    /**
     * Build a {@link View} to be used as a tab indicator, setting the requested
     * string resource as its label.
     */
    private View buildIndicator(String text) {
        final TextView indicator = (TextView) getLayoutInflater().inflate(R.layout.tab_indicator,
                getTabWidget(), false);
        indicator.setText(text);
        return indicator;
    }

    public void onHomeClick(View v) {
        UIUtils.goHome(this);
    }

    public void onSearchClick(View v) {
        UIUtils.goSearch(this);
    }
}
