package com.example.it_codi.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.it_codi.R;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDao;
import com.example.it_codi.database.ClothesDatabase;
import com.example.it_codi.database.Converters;
import com.example.it_codi.fragment.HomeFragment;
import com.example.it_codi.fragment.LikeFragment;
import com.example.it_codi.fragment.ListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft;
    Fragment homeFragment = new HomeFragment();
    Fragment listFragment = new ListFragment();
    Fragment likeFragment = new LikeFragment();

    ClothesDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        ft = fm.beginTransaction();
        ft.add(R.id.frame, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item ->  {
            ft = fm.beginTransaction();
            switch (item.getItemId()){
                case R.id.home:
                    ft.replace(R.id.frame, homeFragment);
                    break;
                case R.id.list:
                    ft.replace(R.id.frame, listFragment);
                    break;
                case R.id.like:
                    ft.replace(R.id.frame, likeFragment);
                    break;
            }
            ft.commit();
            return true;
        });


        //싱글톤 패턴 적용
        DB = ClothesDatabase.getInstance(getApplicationContext());
//        DB.clothesDao().deleteAll();
        if (DB.clothesDao().findAll().isEmpty())
            insertClothes();

    }

    @Override
    protected void onDestroy() {
        DB.clothesDao().deleteAll();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "setup");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;

        switch (item.getItemId()) {
            case 1:
                it = new Intent(this, SetupPopupActivity.class);
                startActivity(it);
                return true;
        }
        return false;
    }

    private void insertClothes(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.codi1_accessory1, options);
        Clothes clothes = new Clothes(bitmap, "악세서리", "봄", "여름", "","",getString(R.string.american),"나일론 볼캡",false);
        DB.clothesDao().insert(clothes);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.codi1_accessory2, options);
        Clothes clothes1 = new Clothes(bitmap1, "악세서리", "봄", "여름", "","",getString(R.string.american),"포스트맨 숄더백",false);
        DB.clothesDao().insert(clothes1);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.codi1_shoes, options);
        Clothes clothes2 = new Clothes(bitmap2, "신발", "봄", "여름", "","",getString(R.string.american),"베이지 코튼 팬츠",false);
        DB.clothesDao().insert(clothes2);

        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.codi1_pants, options);
        Clothes clothes3 = new Clothes(bitmap3, "하의", "봄", "여름", "","",getString(R.string.american),"포인트 독일군",false);
        DB.clothesDao().insert(clothes3);

        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.codi1_top, options);
        Clothes clothes4 = new Clothes(bitmap4, "상의", "봄", "여름", "","",getString(R.string.american),"오버핏 반팔 데님 셔츠",false);
        DB.clothesDao().insert(clothes4);

        //2

        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.codi2_accessory1, options);
        Clothes clothes5 = new Clothes(bitmap5, "악세서리", "봄", "여름", "","",getString(R.string.city),"머스타드 스틱캡",false);
        DB.clothesDao().insert(clothes5);

        Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.codi2_accessory2, options);
        Clothes clothes6 = new Clothes(bitmap6, "악세서리", "봄", "여름", "","",getString(R.string.city),"다크 브라운 가죽 팔찌",false);
        DB.clothesDao().insert(clothes6);

        Bitmap bitmap7 = BitmapFactory.decodeResource(getResources(), R.drawable.codi2_accessory3, options);
        Clothes clothes7 = new Clothes(bitmap7, "악세서리", "봄", "여름", "","",getString(R.string.city),"칼하트 메신저백",false);
        DB.clothesDao().insert(clothes7);

        Bitmap bitmap8 = BitmapFactory.decodeResource(getResources(), R.drawable.codi2_pants, options);
        Clothes clothes8 = new Clothes(bitmap8, "하의", "봄", "여름", "","",getString(R.string.city),"블랙 오버롤",false);
        DB.clothesDao().insert(clothes8);

        Bitmap bitmap9 = BitmapFactory.decodeResource(getResources(), R.drawable.codi2_shoes, options);
        Clothes clothes9 = new Clothes(bitmap9, "신발", "봄", "여름", "","",getString(R.string.city),"쉘 스니커즈",false);
        DB.clothesDao().insert(clothes9);

        Bitmap bitmap10 = BitmapFactory.decodeResource(getResources(), R.drawable.codi2_top, options);
        Clothes clothes10 = new Clothes(bitmap10, "상의", "봄", "여름", "","",getString(R.string.city),"쿨코튼 기본 반팔 티셔츠",false);
        DB.clothesDao().insert(clothes10);

        //3

        Bitmap bitmap11 = BitmapFactory.decodeResource(getResources(), R.drawable.codi3_accessory1, options);
        Clothes clothes11 = new Clothes(bitmap11, "악세서리", "봄", "여름", "","",getString(R.string.casual),"그레이 메시지 캡",false);
        DB.clothesDao().insert(clothes11);

        Bitmap bitmap12 = BitmapFactory.decodeResource(getResources(), R.drawable.codi3_accessory2, options);
        Clothes clothes12 = new Clothes(bitmap12, "악세서리", "봄", "여름", "","",getString(R.string.casual),"블랙 스트링 백팩",false);
        DB.clothesDao().insert(clothes12);

        Bitmap bitmap13 = BitmapFactory.decodeResource(getResources(), R.drawable.codi3_pants, options);
        Clothes clothes13 = new Clothes(bitmap13, "하의", "봄", "여름", "","",getString(R.string.casual),"연청 데님 팬츠",false);
        DB.clothesDao().insert(clothes13);

        Bitmap bitmap14 = BitmapFactory.decodeResource(getResources(), R.drawable.codi3_shoes, options);
        Clothes clothes14 = new Clothes(bitmap14, "신발", "봄", "여름", "","",getString(R.string.casual),"나이키 데이브레이크",false);
        DB.clothesDao().insert(clothes14);

        Bitmap bitmap15 = BitmapFactory.decodeResource(getResources(), R.drawable.codi3_top, options);
        Clothes clothes15 = new Clothes(bitmap15, "상의", "봄", "여름", "","",getString(R.string.casual),"1987 블랙 반팔티",false);
        DB.clothesDao().insert(clothes15);

        //4

        Bitmap bitmap16 = BitmapFactory.decodeResource(getResources(), R.drawable.codi4_accessory1, options);
        Clothes clothes16 = new Clothes(bitmap16, "악세서리", "봄", "여름", "","",getString(R.string.city),"애쉬컴팩트 글래스",false);
        DB.clothesDao().insert(clothes16);

        Bitmap bitmap17 = BitmapFactory.decodeResource(getResources(), R.drawable.codi4_accessory2, options);
        Clothes clothes17 = new Clothes(bitmap17, "악세서리", "봄", "여름", "","",getString(R.string.city),"아웃도어 헬멧 백",false);
        DB.clothesDao().insert(clothes17);

        Bitmap bitmap18 = BitmapFactory.decodeResource(getResources(), R.drawable.codi4_pants, options);
        Clothes clothes18 = new Clothes(bitmap18, "하의", "봄", "여름", "","",getString(R.string.city),"벌룬핏 스냅 팬츠",false);
        DB.clothesDao().insert(clothes18);

        Bitmap bitmap19 = BitmapFactory.decodeResource(getResources(), R.drawable.codi4_shoes, options);
        Clothes clothes19 = new Clothes(bitmap19, "신발", "봄", "여름", "","",getString(R.string.city),"앵커 슈즈",false);
        DB.clothesDao().insert(clothes19);

        Bitmap bitmap20 = BitmapFactory.decodeResource(getResources(), R.drawable.codi4_top1, options);
        Clothes clothes20 = new Clothes(bitmap20, "상의", "봄", "여름", "","",getString(R.string.city),"코튼 프리미엄 티셔츠",false);
        DB.clothesDao().insert(clothes20);

        Bitmap bitmap21 = BitmapFactory.decodeResource(getResources(), R.drawable.codi4_top2, options);
        Clothes clothes21 = new Clothes(bitmap21, "상의", "봄", "여름", "","",getString(R.string.city),"오버핏 브이넥 베스트",false);
        DB.clothesDao().insert(clothes21);

        //5

        Bitmap bitmap22 = BitmapFactory.decodeResource(getResources(), R.drawable.codi5_accessory1, options);
        Clothes clothes22 = new Clothes(bitmap22, "악세서리", "", "", "가을","겨울",getString(R.string.casual),"뉴욕 양키즈 볼캡",false);
        DB.clothesDao().insert(clothes22);

        Bitmap bitmap23 = BitmapFactory.decodeResource(getResources(), R.drawable.codi5_accessory2, options);
        Clothes clothes23 = new Clothes(bitmap23, "악세서리", "", "", "가을","겨울",getString(R.string.casual),"더블링 목걸이",false);
        DB.clothesDao().insert(clothes23);

        Bitmap bitmap24 = BitmapFactory.decodeResource(getResources(), R.drawable.codi5_outer, options);
        Clothes clothes24 = new Clothes(bitmap24, "아우터", "", "", "가을","겨울",getString(R.string.casual),"미니멀 레더 블루종",false);
        DB.clothesDao().insert(clothes24);

        Bitmap bitmap25 = BitmapFactory.decodeResource(getResources(), R.drawable.codi5_pants, options);
        Clothes clothes25 = new Clothes(bitmap25, "하의", "", "", "가을","겨울",getString(R.string.casual),"블랙 스웨트 팬츠",false);
        DB.clothesDao().insert(clothes25);

        Bitmap bitmap26 = BitmapFactory.decodeResource(getResources(), R.drawable.codi5_shoes, options);
        Clothes clothes26 = new Clothes(bitmap26, "신발", "", "", "가을","겨울",getString(R.string.casual),"컨버스 하이",false);
        DB.clothesDao().insert(clothes26);

        Bitmap bitmap27 = BitmapFactory.decodeResource(getResources(), R.drawable.codi5_top, options);
        Clothes clothes27 = new Clothes(bitmap27, "상의", "", "", "가을","겨울",getString(R.string.casual),"아이보리 니트 스웨터",false);
        DB.clothesDao().insert(clothes27);

        //6

        Bitmap bitmap28 = BitmapFactory.decodeResource(getResources(), R.drawable.codi6_accessory, options);
        Clothes clothes28 = new Clothes(bitmap28, "악세서리", "", "", "가을","겨울",getString(R.string.dandy),"미니 블랙 크로스백",false);
        DB.clothesDao().insert(clothes28);

        Bitmap bitmap29 = BitmapFactory.decodeResource(getResources(), R.drawable.codi6_outer, options);
        Clothes clothes29 = new Clothes(bitmap29, "아우터", "", "", "가을","겨울",getString(R.string.dandy),"싱글 블레이져",false);
        DB.clothesDao().insert(clothes29);

        Bitmap bitmap30 = BitmapFactory.decodeResource(getResources(), R.drawable.codi6_pants, options);
        Clothes clothes30 = new Clothes(bitmap30, "하의", "", "", "가을","겨울",getString(R.string.dandy),"워시드 슬림 데님",false);
        DB.clothesDao().insert(clothes30);

        Bitmap bitmap31 = BitmapFactory.decodeResource(getResources(), R.drawable.codi6_shoes, options);
        Clothes clothes31 = new Clothes(bitmap31, "신발", "", "", "가을","겨울",getString(R.string.dandy),"테일러 토 더비 슈즈",false);
        DB.clothesDao().insert(clothes31);

        Bitmap bitmap32 = BitmapFactory.decodeResource(getResources(), R.drawable.codi6_top, options);
        Clothes clothes32 = new Clothes(bitmap32, "상의", "", "", "가을","겨울",getString(R.string.dandy),"베이지 롱 셔츠",false);
        DB.clothesDao().insert(clothes32);

        //7

        Bitmap bitmap33 = BitmapFactory.decodeResource(getResources(), R.drawable.codi7_accessory, options);
        Clothes clothes33 = new Clothes(bitmap33, "악세서리", "", "", "가을","겨울",getString(R.string.dandy),"블랙 브리프케이스",false);
        DB.clothesDao().insert(clothes33);

        Bitmap bitmap34 = BitmapFactory.decodeResource(getResources(), R.drawable.codi7_pants, options);
        Clothes clothes34 = new Clothes(bitmap34, "하의", "", "", "가을","겨울",getString(R.string.dandy),"아이보리 슬림 슬랙스",false);
        DB.clothesDao().insert(clothes34);

        Bitmap bitmap35 = BitmapFactory.decodeResource(getResources(), R.drawable.codi7_shoes, options);
        Clothes clothes35 = new Clothes(bitmap35, "신발", "", "", "가을","겨울",getString(R.string.dandy),"포멀 더비슈즈",false);
        DB.clothesDao().insert(clothes35);

        Bitmap bitmap36 = BitmapFactory.decodeResource(getResources(), R.drawable.codi7_top, options);
        Clothes clothes36 = new Clothes(bitmap36, "상의", "", "", "가을","겨울",getString(R.string.dandy),"연청 스웻 맨투맨",false);
        DB.clothesDao().insert(clothes36);

        //8

        Bitmap bitmap37 = BitmapFactory.decodeResource(getResources(), R.drawable.codi8_accessory, options);
        Clothes clothes37 = new Clothes(bitmap37, "악세서리", "", "", "가을","겨울",getString(R.string.casual),"스트릿 블랙 캡",false);
        DB.clothesDao().insert(clothes37);

        Bitmap bitmap38 = BitmapFactory.decodeResource(getResources(), R.drawable.codi8_outer, options);
        Clothes clothes38 = new Clothes(bitmap38, "아우터", "", "", "가을","겨울",getString(R.string.casual),"그레이 숏 패딩",false);
        DB.clothesDao().insert(clothes38);

        Bitmap bitmap39 = BitmapFactory.decodeResource(getResources(), R.drawable.codi8_pants, options);
        Clothes clothes39 = new Clothes(bitmap39, "하의", "", "", "가을","겨울",getString(R.string.casual),"위시드 블랙진",false);
        DB.clothesDao().insert(clothes39);

        Bitmap bitmap40 = BitmapFactory.decodeResource(getResources(), R.drawable.codi8_shoes, options);
        Clothes clothes40 = new Clothes(bitmap40, "신발", "", "", "가을","겨울",getString(R.string.casual),"스케이트 컨버스 하이",false);
        DB.clothesDao().insert(clothes40);

        Bitmap bitmap41 = BitmapFactory.decodeResource(getResources(), R.drawable.codi8_top, options);
        Clothes clothes41 = new Clothes(bitmap41, "상의", "", "", "가을","겨울",getString(R.string.casual),"네이비 로고 후드티",false);
        DB.clothesDao().insert(clothes41);
    }

    class MakeRoom extends AsyncTask<Context, Integer, ClothesDatabase> {
        ClothesDatabase db;

        public void MakeRoom(ClothesDatabase db) {
            this.db = db;
        }

        // 백그라운드 작업 실행
        //  UI 작업 불가
        @Override
        protected ClothesDatabase doInBackground(Context... contexts) {
            db = ClothesDatabase.getInstance(getApplicationContext());
            return db;
        }

        // 초기화 코드
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // Background 작업 중 UI 작업 진행
        // publishProgress() 통해 호출
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//
//        }

        // 결과를 반환.
        @Override
        protected void onPostExecute(ClothesDatabase db) {
            super.onPostExecute(db);
        }

        // 취소 시 발생할 이벤트
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}