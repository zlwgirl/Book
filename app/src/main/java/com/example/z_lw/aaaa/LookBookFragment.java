package com.example.z_lw.aaaa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z-LW on 2016/9/26.
 */
public class LookBookFragment extends Fragment implements View.OnClickListener {
    private ImageView bookBtn;
    private GridView gridView;
    private LookBookGvAdapter gvAdapter;
    private List<Photo> photo;
    private DrawerLayout drawerLayout;
//    private CircleImageView circleImageView;
    private Button btn_picture, btn_cancle;
    private SharedPreferences.Editor editor;
    private TextView setTv, myMessage;
    private TextView loginTv;
    private TextView userName ;

    /* 头像文件 */
//    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
//    /* 请求识别码 */
//    private static final int CODE_GALLERY_REQUEST = 0xa0;//本地
//    private static final int CODE_RESULT_REQUEST = 0xa2;//最终裁剪后的结果


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lookbook, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookBtn = (ImageView) view.findViewById(R.id.lookbook_im_title);
        gridView = (GridView) view.findViewById(R.id.lookbook_gv);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawlayout);
//        circleImageView = (CircleImageView) view.findViewById(R.id.drawerlayout_cr_photo);
        setTv = (TextView) view.findViewById(R.id.tv_set);
        myMessage = (TextView) view.findViewById(R.id.my_message);
        loginTv = (TextView) view.findViewById(R.id.login_tv);
        userName = (TextView) view.findViewById(R.id.username_tv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photo = new ArrayList<>();
        gvAdapter = new LookBookGvAdapter(getContext());
        gvAdapter.setPictures(photo);
        gridView.setAdapter(gvAdapter);

//        SharedPreferences spPicture = getActivity().getSharedPreferences("Picture", getActivity().MODE_PRIVATE);
//        editor = spPicture.edit();
//        circleImageView.setImageBitmap(BitmapFactory.decodeFile(spPicture.getString("tu", " ")));
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

// grideview的监听事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), GrideViewDetailActivity.class);
                startActivity(intent);

            }
        });
        // 头像的点击
//        circleImageView.setOnClickListener(this);
        // 设置点击
        setTv.setOnClickListener(this);
        myMessage.setOnClickListener(this);
        loginTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.drawerlayout_cr_photo:
//                showDiaLog();
//                break;
            case R.id.tv_set:
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.my_message:
                Intent intentMessage = new Intent(getContext(), MyMessageActicity.class);
                startActivity(intentMessage);
                break;
            case R.id.login_tv:
                Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                startActivity(intentLogin);
                break;
        }
    }

//    private void showDiaLog() {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.photo_choose_dialog, null);
//        final Dialog dialog = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
//        dialog.setContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        Window window = dialog.getWindow();
//        // 设置显示动画
//        window.setWindowAnimations(R.style.main_menu_animstyle);
//        WindowManager.LayoutParams wl = window.getAttributes();
//        wl.x = 0;
//        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
//        // 以下这两句是为了保证按钮可以水平满屏
//        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//
//        // 设置显示位置
//        dialog.onWindowAttributesChanged(wl);
//        // 设置点击外围解散
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//        btn_picture = (Button) window.findViewById(R.id.btn_picture);
//        btn_cancle = (Button) window.findViewById(R.id.btn_cancle);
//        btn_picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
//                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent1, 1);
//                dialog.dismiss();
//            }
//        });
//        btn_cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // 用户没有进行有效的设置操作，返回
//        if (resultCode == getActivity().RESULT_CANCELED) {//取消
//            Toast.makeText(getActivity().getApplication(), "取消", Toast.LENGTH_LONG).show();
//            return;
//        }
//        switch (requestCode) {
//            case 1:
//                if (resultCode == getActivity().RESULT_OK) {
//                    cropPhoto(data.getData());// 裁剪图片
//                }
//                Uri selectdeImage = data.getData();
//                Cursor cursor = getActivity().getContentResolver().query(selectdeImage, null, null, null, null);
//                while (cursor.moveToNext()) {
//                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                    circleImageView.setImageBitmap(BitmapFactory.decodeFile(path));
//                    editor.putString("tu", path);
//                    editor.commit();
//                }
//                cursor.close();
//                break;
//        }
//    }


    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
//    public void cropPhoto(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", 150);
//        intent.putExtra("outputY", 150);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, 3);
//    }


}

