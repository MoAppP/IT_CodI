package com.example.it_codi.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;

import com.example.it_codi.R;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;

public class LoadingActivity extends Activity {
    ClothesDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DB = ClothesDatabase.getInstance(getApplicationContext());
                if (DB.clothesDao().findAllUid().isEmpty())
                    insertClothes();

                finish();
            }
        }, 2000);

    }

    private void insertClothes(){
        Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), R.drawable.codi1_accessory1, 1500, 1500);
        Clothes clothes = new Clothes(bitmap, "악세서리", "봄", "여름", "","",getString(R.string.american),"나일론 볼캡",false);
        DB.clothesDao().insert(clothes);

        Bitmap bitmap1 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi1_accessory2, 1500, 1500);
        Clothes clothes1 = new Clothes(bitmap1, "악세서리", "봄", "여름", "","",getString(R.string.american),"포스트맨 숄더백",false);
        DB.clothesDao().insert(clothes1);

        Bitmap bitmap2 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi1_shoes, 1500, 1500);
        Clothes clothes2 = new Clothes(bitmap2, "신발", "봄", "여름", "","",getString(R.string.american),"베이지 코튼 팬츠",false);
        DB.clothesDao().insert(clothes2);

        Bitmap bitmap3 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi1_pants, 1500, 1500);
        Clothes clothes3 = new Clothes(bitmap3, "하의", "봄", "여름", "","",getString(R.string.american),"포인트 독일군",false);
        DB.clothesDao().insert(clothes3);

        Bitmap bitmap4 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi1_top, 1500, 1500);
        Clothes clothes4 = new Clothes(bitmap4, "상의", "봄", "여름", "","",getString(R.string.american),"오버핏 반팔 데님 셔츠",false);
        DB.clothesDao().insert(clothes4);

        //2

        Bitmap bitmap5 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi2_accessory1, 1500, 1500);
        Clothes clothes5 = new Clothes(bitmap5, "악세서리", "봄", "여름", "","",getString(R.string.city),"머스타드 스틱캡",false);
        DB.clothesDao().insert(clothes5);

        Bitmap bitmap6 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi2_accessory2, 1500, 1500);
        Clothes clothes6 = new Clothes(bitmap6, "악세서리", "봄", "여름", "","",getString(R.string.city),"다크 브라운 가죽 팔찌",false);
        DB.clothesDao().insert(clothes6);

        Bitmap bitmap7 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi2_accessory3, 1500, 1500);
        Clothes clothes7 = new Clothes(bitmap7, "악세서리", "봄", "여름", "","",getString(R.string.city),"칼하트 메신저백",false);
        DB.clothesDao().insert(clothes7);

        Bitmap bitmap8 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi2_pants, 1500, 1500);
        Clothes clothes8 = new Clothes(bitmap8, "하의", "봄", "여름", "","",getString(R.string.city),"블랙 오버롤",false);
        DB.clothesDao().insert(clothes8);

        Bitmap bitmap9 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi2_shoes, 1500, 1500);
        Clothes clothes9 = new Clothes(bitmap9, "신발", "봄", "여름", "","",getString(R.string.city),"쉘 스니커즈",false);
        DB.clothesDao().insert(clothes9);

        Bitmap bitmap10 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi2_top, 1500, 1500);
        Clothes clothes10 = new Clothes(bitmap10, "상의", "봄", "여름", "","",getString(R.string.city),"쿨코튼 기본 반팔 티셔츠",false);
        DB.clothesDao().insert(clothes10);

        //3

        Bitmap bitmap11 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi3_accessory1, 1500, 1500);
        Clothes clothes11 = new Clothes(bitmap11, "악세서리", "봄", "여름", "","",getString(R.string.casual),"그레이 메시지 캡",false);
        DB.clothesDao().insert(clothes11);

        Bitmap bitmap12 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi3_accessory2, 1500, 1500);
        Clothes clothes12 = new Clothes(bitmap12, "악세서리", "봄", "여름", "","",getString(R.string.casual),"블랙 스트링 백팩",false);
        DB.clothesDao().insert(clothes12);

        Bitmap bitmap13 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi3_pants, 1500, 1500);
        Clothes clothes13 = new Clothes(bitmap13, "하의", "봄", "여름", "","",getString(R.string.casual),"연청 데님 팬츠",false);
        DB.clothesDao().insert(clothes13);

        Bitmap bitmap14 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi3_shoes, 1500, 1500);
        Clothes clothes14 = new Clothes(bitmap14, "신발", "봄", "여름", "","",getString(R.string.casual),"나이키 데이브레이크",false);
        DB.clothesDao().insert(clothes14);

        Bitmap bitmap15 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi3_top, 1500, 1500);
        Clothes clothes15 = new Clothes(bitmap15, "상의", "봄", "여름", "","",getString(R.string.casual),"1987 블랙 반팔티",false);
        DB.clothesDao().insert(clothes15);

        //4

        Bitmap bitmap16 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi4_accessory1, 1500, 1500);
        Clothes clothes16 = new Clothes(bitmap16, "악세서리", "봄", "여름", "","",getString(R.string.city),"애쉬컴팩트 글래스",false);
        DB.clothesDao().insert(clothes16);

        Bitmap bitmap17 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi4_accessory2, 1500, 1500);
        Clothes clothes17 = new Clothes(bitmap17, "악세서리", "봄", "여름", "","",getString(R.string.city),"아웃도어 헬멧 백",false);
        DB.clothesDao().insert(clothes17);

        Bitmap bitmap18 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi4_pants, 1500, 1500);
        Clothes clothes18 = new Clothes(bitmap18, "하의", "봄", "여름", "","",getString(R.string.city),"벌룬핏 스냅 팬츠",false);
        DB.clothesDao().insert(clothes18);

        Bitmap bitmap19 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi4_shoes, 1500, 1500);
        Clothes clothes19 = new Clothes(bitmap19, "신발", "봄", "여름", "","",getString(R.string.city),"앵커 슈즈",false);
        DB.clothesDao().insert(clothes19);

        Bitmap bitmap20 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi4_top1, 1500, 1500);
        Clothes clothes20 = new Clothes(bitmap20, "상의", "봄", "여름", "","",getString(R.string.city),"코튼 프리미엄 티셔츠",false);
        DB.clothesDao().insert(clothes20);

        Bitmap bitmap21 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi4_top2, 1500, 1500);
        Clothes clothes21 = new Clothes(bitmap21, "상의", "봄", "여름", "","",getString(R.string.city),"오버핏 브이넥 베스트",false);
        DB.clothesDao().insert(clothes21);

        //5

        Bitmap bitmap22 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi5_accessory1, 1500, 1500);
        Clothes clothes22 = new Clothes(bitmap22, "악세서리", "", "", "가을","겨울",getString(R.string.casual),"뉴욕 양키즈 볼캡",false);
        DB.clothesDao().insert(clothes22);

        Bitmap bitmap23 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi5_accessory2, 1500, 1500);
        Clothes clothes23 = new Clothes(bitmap23, "악세서리", "", "", "가을","겨울",getString(R.string.casual),"더블링 목걸이",false);
        DB.clothesDao().insert(clothes23);

        Bitmap bitmap24 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi5_outer, 1500, 1500);
        Clothes clothes24 = new Clothes(bitmap24, "아우터", "", "", "가을","겨울",getString(R.string.casual),"미니멀 레더 블루종",false);
        DB.clothesDao().insert(clothes24);

        Bitmap bitmap25 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi5_pants, 1500, 1500);
        Clothes clothes25 = new Clothes(bitmap25, "하의", "", "", "가을","겨울",getString(R.string.casual),"블랙 스웨트 팬츠",false);
        DB.clothesDao().insert(clothes25);

        Bitmap bitmap26 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi5_shoes, 1500, 1500);
        Clothes clothes26 = new Clothes(bitmap26, "신발", "", "", "가을","겨울",getString(R.string.casual),"컨버스 하이",false);
        DB.clothesDao().insert(clothes26);

        Bitmap bitmap27 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi5_top, 1500, 1500);
        Clothes clothes27 = new Clothes(bitmap27, "상의", "", "", "가을","겨울",getString(R.string.casual),"아이보리 니트 스웨터",false);
        DB.clothesDao().insert(clothes27);

        //6

        Bitmap bitmap28 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi6_accessory, 1500, 1500);
        Clothes clothes28 = new Clothes(bitmap28, "악세서리", "", "", "가을","겨울",getString(R.string.dandy),"미니 블랙 크로스백",false);
        DB.clothesDao().insert(clothes28);

        Bitmap bitmap29 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi6_outer, 1500, 1500);
        Clothes clothes29 = new Clothes(bitmap29, "아우터", "", "", "가을","겨울",getString(R.string.dandy),"싱글 블레이져",false);
        DB.clothesDao().insert(clothes29);

        Bitmap bitmap30 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi6_pants, 1500, 1500);
        Clothes clothes30 = new Clothes(bitmap30, "하의", "", "", "가을","겨울",getString(R.string.dandy),"워시드 슬림 데님",false);
        DB.clothesDao().insert(clothes30);

        Bitmap bitmap31 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi6_shoes, 1500, 1500);
        Clothes clothes31 = new Clothes(bitmap31, "신발", "", "", "가을","겨울",getString(R.string.dandy),"테일러 토 더비 슈즈",false);
        DB.clothesDao().insert(clothes31);

        Bitmap bitmap32 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi6_top, 1500, 1500);
        Clothes clothes32 = new Clothes(bitmap32, "상의", "", "", "가을","겨울",getString(R.string.dandy),"베이지 롱 셔츠",false);
        DB.clothesDao().insert(clothes32);

        //7

        Bitmap bitmap33 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi7_accessory, 1500, 1500);
        Clothes clothes33 = new Clothes(bitmap33, "악세서리", "", "", "가을","겨울",getString(R.string.dandy),"블랙 브리프케이스",false);
        DB.clothesDao().insert(clothes33);

        Bitmap bitmap34 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi7_pants, 1500, 1500);
        Clothes clothes34 = new Clothes(bitmap34, "하의", "", "", "가을","겨울",getString(R.string.dandy),"아이보리 슬림 슬랙스",false);
        DB.clothesDao().insert(clothes34);

        Bitmap bitmap35 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi7_shoes, 1500, 1500);
        Clothes clothes35 = new Clothes(bitmap35, "신발", "", "", "가을","겨울",getString(R.string.dandy),"포멀 더비슈즈",false);
        DB.clothesDao().insert(clothes35);

        Bitmap bitmap36 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi7_top, 1500, 1500);
        Clothes clothes36 = new Clothes(bitmap36, "상의", "", "", "가을","겨울",getString(R.string.dandy),"연청 스웻 맨투맨",false);
        DB.clothesDao().insert(clothes36);

        //8

        Bitmap bitmap37 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi8_accessory, 1500, 1500);
        Clothes clothes37 = new Clothes(bitmap37, "악세서리", "", "", "가을","겨울",getString(R.string.casual),"스트릿 블랙 캡",false);
        DB.clothesDao().insert(clothes37);

        Bitmap bitmap38 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi8_outer, 1500, 1500);
        Clothes clothes38 = new Clothes(bitmap38, "아우터", "", "", "가을","겨울",getString(R.string.casual),"그레이 숏 패딩",false);
        DB.clothesDao().insert(clothes38);

        Bitmap bitmap39 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi8_pants, 1500, 1500);
        Clothes clothes39 = new Clothes(bitmap39, "하의", "", "", "가을","겨울",getString(R.string.casual),"위시드 블랙진",false);
        DB.clothesDao().insert(clothes39);

        Bitmap bitmap40 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi8_shoes, 1500, 1500);
        Clothes clothes40 = new Clothes(bitmap40, "신발", "", "", "가을","겨울",getString(R.string.casual),"스케이트 컨버스 하이",false);
        DB.clothesDao().insert(clothes40);

        Bitmap bitmap41 = decodeSampledBitmapFromResource(getResources(), R.drawable.codi8_top, 1500, 1500);
        Clothes clothes41 = new Clothes(bitmap41, "상의", "", "", "가을","겨울",getString(R.string.casual),"네이비 로고 후드티",false);
        DB.clothesDao().insert(clothes41);


    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

}