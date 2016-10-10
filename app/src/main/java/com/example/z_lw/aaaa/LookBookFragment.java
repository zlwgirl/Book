package com.example.z_lw.aaaa;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Z-LW on 2016/9/26.
 */
public class LookBookFragment extends Fragment implements View.OnClickListener {
    private TextView bookBtn;
    private DrawerLayout drawerLayout;
    private CircleImageView circleImageView;
    private Button btn_picture, btn_cancle;
    private SharedPreferences.Editor editor;
    private TextView setTv, myMessage, money;
    private ImageView loginIm,imageView_look;
    private TextView userName ;
    private String imageurl;

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;//本地
    private static final int CODE_RESULT_REQUEST = 0xa2;//最终裁剪后的结果


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lookbook, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookBtn = (TextView) view.findViewById(R.id.lookbook_bt_title);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawlayout);
        circleImageView = (CircleImageView) view.findViewById(R.id.drawerlayout_cr_photo);
        setTv = (TextView) view.findViewById(R.id.tv_set);
        myMessage = (TextView) view.findViewById(R.id.my_message);
        money = (TextView) view.findViewById(R.id.my_money);
        loginIm = (ImageView) view.findViewById(R.id.login_im);
        userName = (TextView) view.findViewById(R.id.username_tv);
        imageView_look = (ImageView) view.findViewById(R.id.look_imageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //接受书城页面传过来的网址SP
        SharedPreferences preferences = getActivity().getSharedPreferences("download", Context.MODE_PRIVATE);
        imageurl = preferences.getString("url","http://58pic.ooopic.com/58pic/14/70/68/34858PIC6sf.jpg");

        new MyAsync().execute(imageurl);

        SharedPreferences spPicture = getActivity().getSharedPreferences("Picture", getActivity().MODE_PRIVATE);
        editor = spPicture.edit();
        circleImageView.setImageBitmap(BitmapFactory.decodeFile(spPicture.getString("tu", " ")));
        SharedPreferences spUsername = getActivity().getSharedPreferences("Register",getActivity().MODE_PRIVATE);
        String name = spUsername.getString("userName","");
        userName.setText(name);


// 按钮点击弹出抽屉
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);// 锁定当前行
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        // 头像的点击
        circleImageView.setOnClickListener(this);
        // 设置点击
        setTv.setOnClickListener(this);
        myMessage.setOnClickListener(this);
        money.setOnClickListener(this);
        loginIm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawerlayout_cr_photo:
                showDiaLog();
                break;
            case R.id.tv_set:
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.my_message:
                Intent intentMessage = new Intent(getContext(), MyMessageActicity.class);
                startActivity(intentMessage);
                break;
            case R.id.my_money:
                Intent intentMoney = new Intent(getContext(), MyMoney.class);
                startActivity(intentMoney);
                break;
            case R.id.login_im:
                Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                startActivity(intentLogin);
                break;
        }
    }

    private void showDiaLog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.photo_choose_dialog, null);
        final Dialog dialog = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        btn_picture = (Button) window.findViewById(R.id.btn_picture);
        btn_cancle = (Button) window.findViewById(R.id.btn_cancle);
//        btn_photo = (Button) window.findViewById(R.id.btn_photo);
        // 相册
//        btn_picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentFromGallery = new Intent();
//                // 设置文件类型
//                intentFromGallery.setType("image/*");//选择图片
//                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
////如果你想在Activity中得到新打开Activity关闭后返回的数据，
////你需要使用系统提供的startActivityForResult(Intent intent,int requestCode)方法打开新的Activity
//                startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
//
//                dialog.dismiss();
//            }
//        });
        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        // 相机
//        btn_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
//                startActivityForResult(intent2, CODE_GALLERY_REQUEST);// 采用ForResult打开
//                dialog.dismiss();
//            }
//        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 用户没有进行有效的设置操作，返回
        if (resultCode == getActivity().RESULT_CANCELED) {//取消
            Toast.makeText(getActivity().getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                Uri selectdeImage = data.getData();
                Cursor cursor = getActivity().getContentResolver().query(selectdeImage, null, null, null, null);
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    circleImageView.setImageBitmap(BitmapFactory.decodeFile(path));
                    editor.putString("tu", path);
                    editor.commit();
                }
                cursor.close();
                break;
//            case 2:
//                if (resultCode == getActivity().RESULT_OK) {
//                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
//                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
//                }
//                break;
        }
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//
//// 用户没有进行有效的设置操作，返回
//        if (resultCode == getActivity().RESULT_CANCELED) {//取消
//            Toast.makeText(getActivity().getApplication(), "取消", Toast.LENGTH_LONG).show();
//            return;
//        }
//        switch (requestCode) {
//            case CODE_GALLERY_REQUEST:
//                if (resultCode == getActivity().RESULT_OK) {
//                    cropPhoto(intent.getData());// 裁剪图片
//                }//如果是来自本地的
////                cropPhoto(intent.getData());//直接裁剪图片
//                Uri selectdeImage = intent.getData();
//                Cursor cursor = getActivity().getContentResolver().query(selectdeImage, null, null, null, null);
//                while (cursor.moveToNext()) {
//                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                    circleImageView.setImageBitmap(BitmapFactory.decodeFile(path));
//                    editor.putString("tu", path);
//                    editor.commit();
//                }
//                cursor.close();
//                break;
//            case CODE_RESULT_REQUEST:
//                if (intent != null) {
//                    setImageToHeadView(intent);//设置图片框
//                }
//                break;
//        }
//
//    }
//
//    /**
//     * 提取保存裁剪之后的图片数据，并设置头像部分的View
//     */
//    private void setImageToHeadView(Intent intent) {
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            Bitmap photo = extras.getParcelable("data");
//            circleImageView.setImageBitmap(photo);
//            //新建文件夹 先选好路径 再调用mkdir函数 现在是根目录下面的Ask文件夹
//            File nf = new File(Environment.getExternalStorageDirectory() + "/Ask");
//            nf.mkdir();
//            //在根目录下面的ASk文件夹下 创建okkk.jpg文件
//            File f = new File(Environment.getExternalStorageDirectory() + "/Ask", "okkk.jpg");
//            FileOutputStream out = null;
//            try {//打开输出流 将图片数据填入文件中
//                out = new FileOutputStream(f);
//                photo.compress(Bitmap.CompressFormat.PNG, 90, out);
//                try {
//                    out.flush();
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
    //异步任务将图片显示在imageview上
    class MyAsync extends AsyncTask<String,Void,Bitmap>{
        URL url = null;
        HttpURLConnection connection = null;
        InputStream stream = null;
        Bitmap bitmap = null;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                stream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(stream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stream.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView_look.setImageBitmap(bitmap);
        }
    }


}

