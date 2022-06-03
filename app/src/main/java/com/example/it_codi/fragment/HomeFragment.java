package com.example.it_codi.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.it_codi.R;
import com.example.it_codi.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tv_page;
    private ViewPager2 homeViewPager;
    private FragmentStateAdapter pagerAdapter;
    private ViewPager2.OnPageChangeCallback pageChangeCallback;
    Timer timer;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        makeViewPager(v);

        return v;
    }

    private void makeViewPager(View v) {
        homeViewPager = v.findViewById(R.id.home_view_pager);
        tv_page = v.findViewById(R.id.tv_current_page);

        // Home View Pager에 들어갈 그림 목록
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.codi1);
        list.add(R.drawable.codi2);
        list.add(R.drawable.codi3);
        list.add(R.drawable.codi4);
        list.add(R.drawable.codi5);
        list.add(R.drawable.codi6);
        list.add(R.drawable.codi7);
        list.add(R.drawable.codi8);


        homeViewPager.setAdapter(new ViewPagerAdapter(list, tv_page));

        // startPosition 을  (int형 최대범위)/2 로 설정
        int startPosition = Integer.MAX_VALUE / 2;
        startPosition -= startPosition % list.size();
        homeViewPager.setCurrentItem(startPosition, false);



        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                homeViewPager.setCurrentItem(homeViewPager.getCurrentItem()+1,true);
            }
        };

        pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (tv_page != null)
                    tv_page.setText((position % list.size() + 1) + " / " + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    // timer start
                    if (timer != null)
                        timer.cancel();
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, 3000);
                }
                else if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    // timer cancel
                    timer.cancel();
                }
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000);

        homeViewPager.registerOnPageChangeCallback(pageChangeCallback);

        tv_page.setText("1 / " + list.size());

    }
}


