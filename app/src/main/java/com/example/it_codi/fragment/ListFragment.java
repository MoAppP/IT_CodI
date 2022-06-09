package com.example.it_codi.fragment;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.it_codi.R;
import com.example.it_codi.activity.ListLoadingActivity;
import com.example.it_codi.activity.MainActivity;
import com.example.it_codi.adapter.ListRecyclerAdapter;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    ListRecyclerAdapter adapter;
    GridLayoutManager layoutManager;

    ArrayList<Integer> allList = new ArrayList<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>();
    ArrayList<Integer> temp = new ArrayList<Integer>();
    ArrayList<String> list_ty = new ArrayList<String>();
    ArrayList<String> list_ss1 = new ArrayList<String>();
    ArrayList<String> list_ss2 = new ArrayList<String>();
    ArrayList<String> list_ss3 = new ArrayList<String>();
    ArrayList<String> list_ss4 = new ArrayList<String>();

    ClothesDatabase DB;

    boolean isLoading = false;
    boolean isCheckAddLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = ClothesDatabase.getInstance(getContext().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        adapter = new ListRecyclerAdapter(list);
        adapter.setHasStableIds(true);
        layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("test", "?");
                    Log.d("test", Boolean.toString(isLoading));
                    if (!isLoading){
//                        isLoading = true;
                        dataMore();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        CheckBox cb1 = layout.findViewById(R.id.case1);
        CheckBox cb2 = layout.findViewById(R.id.case2);
        CheckBox cb3 = layout.findViewById(R.id.case3);
        CheckBox cb4 = layout.findViewById(R.id.case4);
        CheckBox cb5 = layout.findViewById(R.id.case5);
        CheckBox ss1 = layout.findViewById(R.id.season1);
        CheckBox ss2 = layout.findViewById(R.id.season2);
        CheckBox ss3 = layout.findViewById(R.id.season3);
        CheckBox ss4 = layout.findViewById(R.id.season4);

        cb1.setOnClickListener(view -> {
            updateListTy((CheckBox) view);
            check_connect();
        });
        cb2.setOnClickListener(view ->{
            updateListTy((CheckBox) view);
            check_connect();
        });
        cb3.setOnClickListener(view -> {
            updateListTy((CheckBox) view);
            check_connect();
        });
        cb4.setOnClickListener(view -> {
            updateListTy((CheckBox) view);
            check_connect();
        });
        cb5.setOnClickListener(view -> {
            updateListTy((CheckBox) view);
            check_connect();
        });
        ss1.setOnClickListener(view -> {
            if(ss1.isChecked()) {
                list_ss1.add(ss1.getText().toString());
            }
            else {
                list_ss1.remove(ss1.getText().toString());
            }
            check_connect();
        });
        ss2.setOnClickListener(view -> {
            if(ss2.isChecked()) {
                list_ss2.add(ss2.getText().toString());
            }
            else {
                list_ss2.remove(ss2.getText().toString());
            }
            check_connect();
        });
        ss3.setOnClickListener(view -> {
            if(ss3.isChecked()) {
                list_ss3.add(ss3.getText().toString());
            }
            else {
                list_ss3.remove(ss3.getText().toString());
            }
            check_connect();
        });
        ss4.setOnClickListener(view -> {
            if(ss4.isChecked()) {
                list_ss4.add(ss4.getText().toString());
            }
            else {
                list_ss4.remove(ss4.getText().toString());
            }
            check_connect();
        });

        return layout;
    }

    @Override
    public void onResume(){
        super.onResume();
        check_add();
        firstData();
        printAllList();
        printList();
        printListTy();
        printAllList();
        printListSs1();
        printListSs2();
        printListSs3();
        printListSs4();
        adapter.notifyDataSetChanged();
    }

    private void check_connect() {
        boolean typ = list_ty.isEmpty();
        boolean spr = list_ss1.isEmpty();
        boolean sum = list_ss2.isEmpty();
        boolean aut = list_ss3.isEmpty();
        boolean win = list_ss4.isEmpty();
        if(typ && spr && sum && aut && win){
            list.clear();
            adapter.notifyDataSetChanged();
        }
        else
            new CheckAddBackGround().execute(0);
    }

    private void check_add() {
        allList.clear();

        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean st1 = pref.getBoolean("american", false);
        boolean st2 = pref.getBoolean("city", false);
        boolean st3 = pref.getBoolean("dandy", false);
        boolean st4 = pref.getBoolean("casual", false);
        boolean typ = list_ty.isEmpty();
        boolean spr = list_ss1.isEmpty();
        boolean sum = list_ss2.isEmpty();
        boolean aut = list_ss3.isEmpty();
        boolean win = list_ss4.isEmpty();
        boolean sea = !spr || !sum || !aut || !win;
        list_ty = new ArrayList<>(new LinkedHashSet<>(list_ty));
        list_ss1 = new ArrayList<>(new LinkedHashSet<>(list_ss1));
        list_ss2 = new ArrayList<>(new LinkedHashSet<>(list_ss2));
        list_ss3 = new ArrayList<>(new LinkedHashSet<>(list_ss3));
        list_ss4 = new ArrayList<>(new LinkedHashSet<>(list_ss4));
        if(!typ && sea){
            temp.clear();
            for(String it : list_ty) { allList.addAll(DB.clothesDao().findUidByType(it)); }
            for(Integer it : allList) {
                boolean s1 = DB.clothesDao().findById(it).getSpring().equals("");
                boolean s2 = DB.clothesDao().findById(it).getSummer().equals("");
                boolean s3 = DB.clothesDao().findById(it).getAutumn().equals("");
                boolean s4 = DB.clothesDao().findById(it).getWinter().equals("");
                if((!spr && !s1) || (!sum && !s2) || (!aut && !s3) || (!win && !s4))
                    temp.add(it);
            }
            allList.clear();
            allList.addAll(temp);
        }
        else if(!typ) {
            for(String it : list_ty){ allList.addAll(DB.clothesDao().findUidByType(it)); }
        }
        else if(sea) {
            if(!spr)
                for(String it : list_ss1){ allList.addAll(DB.clothesDao().findUidBySpring(it)); }
            if(!sum)
                for(String it : list_ss2){ allList.addAll(DB.clothesDao().findUidBySummer(it)); }
            if(!aut)
                for(String it : list_ss3){ allList.addAll(DB.clothesDao().findUidByAutumn(it)); }
            if(!win)
                for(String it : list_ss4){ allList.addAll(DB.clothesDao().findUidByWinter(it)); }
        }
        if(st1 || st2 || st3 || st4) {
            temp.clear();
            for (Integer it : allList) {
                String style = DB.clothesDao().findById(it).getStyle();
                boolean ame = style.equals(getString(R.string.american));
                boolean cit = style.equals(getString(R.string.city));
                boolean dan = style.equals(getString(R.string.dandy));
                boolean cas = style.equals(getString(R.string.casual));
                if ((ame && st1) || (cit && st2) || (dan && st3) || (cas && st4))
                    temp.add(it);
            }
            allList.clear();
            allList.addAll(temp);
        }

        overlap_clear();
    }

    private void overlap_clear() {
        HashSet<Integer> set = new HashSet<>();
        for(Integer item : allList){ set.add(item); }
        allList.clear();
        allList.addAll(set);
    }

    private void updateListTy(CheckBox cb){
        if(cb.isChecked())
            list_ty.add(cb.getText().toString());
        else
            list_ty.remove(cb.getText().toString());
    }


    private void firstData() {
        // 총 아이템에서 6개를 받아옴
        list.clear();
        for (int i=0; i < 4 && i < allList.size(); i++) {
            list.add(allList.get(i));
        }
    }

    private void dataMore() {
        list.add(null);
        adapter.notifyItemInserted(list.size() -1 );

        isLoading = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.remove(list.size() -1 );
                int scrollPosition = list.size();
                adapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 4;

                for (int i=currentSize; i<nextLimit; i++) {
                    if (i >= allList.size()) {
                        break;
                    }
                    list.add(allList.get(i));
                    Log.d("test", Integer.valueOf(i).toString()+"dataMore");
                }

                adapter.notifyDataSetChanged();
                isLoading = false;
                Log.d("test", Integer.valueOf(list.size()).toString()+"afterDataMore");
            }
        }, 2000);

    }

    //새로운 TASK정의 (AsyncTask)
// < >안에 들은 자료형은 순서대로 doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형을 뜻한다.(내가 사용할 매개변수타입을 설정하면된다)
    class CheckAddBackGround extends AsyncTask<Integer , Integer , Integer> {
        //초기화 단계에서 사용한다. 초기화관련 코드를 작성했다.
        protected void onPreExecute() {
            if (!isCheckAddLoading){
                ListLoadingActivity.activity = null;
                Intent it = new Intent(getActivity(), ListLoadingActivity.class);
                startActivity(it);
                isCheckAddLoading = true;
            }
        }

        //스레드의 백그라운드 작업 구현
//여기서 매개변수 Intger ... values란 values란 이름의 Integer배열이라 생각하면된다.
//배열이라 여러개를 받을 수 도 있다. ex) excute(100, 10, 20, 30); 이런식으로 전달 받으면 된다.
        protected Integer doInBackground(Integer ... values) {

            int i = 0;
            while (ListLoadingActivity.activity == null && i <= 100000) {
               try {
                   Thread.sleep(1000);
                   i += 1000;
                   Log.d("test", Integer.valueOf(i).toString());
               }
               catch (Exception e) {
                   Log.d("test", "?");
                   break;
               }
            }

            check_add();
            firstData();
            Log.d("test", Integer.valueOf(allList.size()).toString() + "allList size");

            return values[0];
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            adapter.notifyDataSetChanged();
        }

        //이 Task에서(즉 이 스레드에서) 수행되던 작업이 종료되었을 때 호출됨
        protected void onPostExecute(Integer result) {

            adapter.notifyDataSetChanged();
            printAllList();
            printList();
            printListTy();
            printAllList();
            printListSs1();
            printListSs2();
            printListSs3();
            printListSs4();
//            ListLoadingActivity.activity.finish();
            if (ListLoadingActivity.activity != null) {
                try {
                    ListLoadingActivity.activity.finish();
                } catch (Exception e) {
                    ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningTaskInfo> info = manager.getRunningTasks(1);
                    ComponentName componentName = info.get(0).topActivity;
                    String ActivityName = componentName.getShortClassName().substring(1);
                    if (ActivityName.equals("ListLoadingActivity"))
                        ListLoadingActivity.activity.finish();
                }
            }
            isCheckAddLoading = false;

        }
    }

    public void printAllList() {
        String s = "";
        for (int i = 0; i < allList.size(); i++) {
            s += Integer.valueOf(allList.get(i)).toString()+" ";
        }
        Log.d("test", "allList "+s);
    }
    public void printList(){
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += Integer.valueOf(list.get(i)).toString()+" ";
        }
        Log.d("test", "list "+s);
    }
    public void printListTy(){
        String s = "";
        for (int i = 0; i < list_ty.size(); i++) {
            s += list_ty.get(i)+" ";
        }
        Log.d("test", "list_ty "+s);
    }
    public void printListSs1(){
        String s = "";
        for (int i = 0; i < list_ss1.size(); i++) {
            s += list_ss1.get(i)+" ";
        }
        Log.d("test", "list_ss1 "+s);
    }
    public void printListSs2(){
        String s = "";
        for (int i = 0; i < list_ss2.size(); i++) {
            s += list_ss2.get(i)+" ";
        }
        Log.d("test", "list_ss2 "+s);
    }
    public void printListSs3(){
        String s = "";
        for (int i = 0; i < list_ss3.size(); i++) {
            s += list_ss3.get(i)+" ";
        }
        Log.d("test", "list_ss3 "+s);
    }
    public void printListSs4(){
        String s = "";
        for (int i = 0; i < list_ss4.size(); i++) {
            s += list_ss4.get(i)+" ";
        }
        Log.d("test", "list_ss4 "+s);
    }
}