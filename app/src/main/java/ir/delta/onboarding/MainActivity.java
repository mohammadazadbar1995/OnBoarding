package ir.delta.onboarding;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.rlNext)
    RelativeLayout rlNext;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.btnStart)
    TextView btnStart;
    @BindView(R.id.imgNext)
    ImageView imgNext;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.llButton)
    LinearLayout llButton;
    private LinearLayout mDotLayout;
    private int mCurrentPage;
    private ArrayList<OnBoarding> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_on_boarding);//TODO change images in OnBoarding
        ButterKnife.bind(this);
        final ViewPager mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
//        if (Constants.language.getDirection() == DirectionEnum.LTR) {
//            imgBack.setBackgroundResource(R.drawable.ic_keyboard_english);
//            imgNext.setBackgroundResource(R.drawable.ic_keyboard_arrow);
//        } else {
//            imgBack.setBackgroundResource(R.drawable.ic_keyboard_arrow);
//            imgNext.setBackgroundResource(R.drawable.ic_keyboard_english);
//        }
        createList();
        addDotsIndicator();
        setDotIndicator(0);
        SliderAdapter sliderAdapter = new SliderAdapter(list);
        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                setDotIndicator(position);
                mCurrentPage = position;
                if (position == 0) {
                    rlNext.setEnabled(true);
                    rlBack.setEnabled(false);
                    rlBack.setVisibility(View.INVISIBLE);
                    rlNext.setVisibility(View.VISIBLE);
                    next.setText(getString(R.string.next_page));
                    back.setText("");
                    btnStart.setVisibility(View.GONE);
                } else if (position == list.size() - 1) {
                    rlNext.setEnabled(false);
                    rlBack.setEnabled(true);
                    rlBack.setVisibility(View.VISIBLE);
                    rlNext.setVisibility(View.INVISIBLE);
                    next.setText("");
                    back.setText(getString(R.string.back_page));
                    btnStart.setVisibility(View.VISIBLE);
                } else {
                    rlNext.setEnabled(true);
                    rlBack.setEnabled(true);
                    rlBack.setVisibility(View.VISIBLE);
                    rlNext.setVisibility(View.VISIBLE);
                    next.setText(getString(R.string.next_page));
                    back.setText(getString(R.string.back_page));
                    btnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rlNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPage == list.size() - 1) {
//                    PreferencesData.saveIsOnBoarding(OnBoardingActivity.this, true);
//                    Intent splashIntent = new Intent(OnBoardingActivity.this, SplashActivity.class);
//                    startActivity(splashIntent);
//                    finish();
                } else {
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                }
            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PreferencesData.saveIsOnBoarding(OnBoardingActivity.this, true);
//                Intent splashIntent = new Intent(OnBoardingActivity.this, SplashActivity.class);
//                startActivity(splashIntent);
//                finish();
            }
        });

    }

    private void createList() {
//            list = new ArrayList<>();
//            list.add(new OnBoarding(R.drawable.onboarding_delta, getString(R.string.delta) + getString(R.string.ir), getString(R.string.onboarding_desc_0)));
//            list.add(new OnBoarding(R.drawable.onboarding_one, getString(R.string.select_city), getString(R.string.onboarding_desc_1)));
//            list.add(new OnBoarding(R.drawable.img_two, getString(R.string.search_home), getString(R.string.onboarding_desc_2)));
//            list.add(new OnBoarding(R.drawable.img_three, getString(R.string.register_home), getString(R.string.onboarding_desc_3)));
    }

    private void addDotsIndicator() {
        mDotLayout.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            TextView dotTextView = new TextView(this);
            dotTextView.setText(Html.fromHtml("&#8226"));
//            dotTextView.setLayoutDirection(Constants.language.getLayoutDirection());
            mDotLayout.addView(dotTextView);
        }
    }

    private void setDotIndicator(int position) {
        for (int i = 0; i < list.size(); i++) {
            TextView textView = (TextView) mDotLayout.getChildAt(i);
            if (i == position) {
                textView.setTextSize(37);
                textView.setTextColor(getResources().getColor(R.color.primaryBack));
            } else {
                textView.setTextSize(35);
                textView.setTextColor(getResources().getColor(R.color.divider));
            }
        }
    }
}

