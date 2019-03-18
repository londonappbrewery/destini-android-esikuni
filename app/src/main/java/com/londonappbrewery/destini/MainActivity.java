package com.londonappbrewery.destini;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private final String STORY_INDEX_KEY = "StoryIndexKey";
    private final String STORY_TEXT_KEY = "StoryTextKey";
    private final String TOP_BUTTON_TEXT_KEY = "TopButtonTextKey";
    private final String BOTTOM_BUTTON_TEXT_KEY = "BottomButtonTextKey";
    private final String STORY_BUNDLE_KEY = "StoryBundleKey";

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView mStoryTextView;
    private Button mTopButton;
    private Button mBottomButton;
    private Button mResetButton;
    private int mStoryIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mStoryTextView = findViewById(R.id.storyTextView);
        mTopButton = findViewById(R.id.buttonTop);
        mBottomButton = findViewById(R.id.buttonBottom);
        mResetButton = findViewById(R.id.resetButton);

        mResetButton.setVisibility(View.INVISIBLE);

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:

        mTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStoryIndex == 1 || mStoryIndex == 2) {
                    mStoryTextView.setText(R.string.T3_Story);
                    mTopButton.setText(R.string.T3_Ans1);
                    mBottomButton.setText(R.string.T3_Ans2);
                    mStoryIndex = 3;
                } else if (mStoryIndex == 3) {
                    mStoryTextView.setText(R.string.T6_End);
                    mTopButton.setVisibility(View.GONE);
                    mBottomButton.setVisibility(View.GONE);
                    mStoryIndex = 6;
                    mResetButton.setVisibility(View.VISIBLE);
                }
            }
        });


        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:

        mBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStoryIndex == 1) {
                    mStoryTextView.setText(R.string.T2_Story);
                    mTopButton.setText(R.string.T2_Ans1);
                    mBottomButton.setText(R.string.T2_Ans2);
                    mStoryIndex = 2;
                } else if (mStoryIndex == 2) {
                    mStoryTextView.setText(R.string.T4_End);
                    mTopButton.setVisibility(View.GONE);
                    mBottomButton.setVisibility(View.GONE);
                    mStoryIndex = 4;
                    mResetButton.setVisibility(View.VISIBLE);
                } else if (mStoryIndex == 3) {
                    mStoryTextView.setText(R.string.T5_End);
                    mTopButton.setVisibility(View.GONE);
                    mBottomButton.setVisibility(View.GONE);
                    mStoryIndex = 5;
                    mResetButton.setVisibility(View.VISIBLE);
                }
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStoryIndex = 1;
                mResetButton.setVisibility(View.INVISIBLE);
                mTopButton.setVisibility(View.VISIBLE);
                mBottomButton.setVisibility(View.VISIBLE);
                mStoryTextView.setText(R.string.T1_Story);
                mTopButton.setText(R.string.T1_Ans1);
                mBottomButton.setText(R.string.T1_Ans2);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle storyBundle = new Bundle();

        storyBundle.putInt(STORY_INDEX_KEY, mStoryIndex);
        storyBundle.putString(STORY_TEXT_KEY, mStoryTextView.getText().toString());
        storyBundle.putString(TOP_BUTTON_TEXT_KEY, mTopButton.getText().toString());
        storyBundle.putString(BOTTOM_BUTTON_TEXT_KEY, mBottomButton.getText().toString());

        outState.putBundle(STORY_BUNDLE_KEY, storyBundle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Bundle storyBundle = savedInstanceState.getBundle(STORY_BUNDLE_KEY);
        mStoryIndex = storyBundle.getInt(STORY_INDEX_KEY);
        mStoryTextView.setText(storyBundle.getString(STORY_TEXT_KEY));

        if (mStoryIndex == 1 || mStoryIndex == 2 || mStoryIndex == 3) {
            mTopButton.setText(storyBundle.getString(TOP_BUTTON_TEXT_KEY));
            mBottomButton.setText(storyBundle.getString(BOTTOM_BUTTON_TEXT_KEY));
        } else {
            mTopButton.setVisibility(View.GONE);
            mBottomButton.setVisibility(View.GONE);
            mResetButton.setVisibility(View.VISIBLE);
        }
    }
}
