package cn.cnic.marathon.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.base.UserInfo;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.request.UserUpdate;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.util.Utils;

public class UserInfoActivity extends BaseActivity implements OnClickListener,
        OnItemSelectedListener {
    LinearLayout back;
    ImageView avatar;
    Spinner uType;
    EditText uDesc;
    TextView unLogin;
    Button submit;
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private Bitmap bitmap;
    private String avatarPath;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private EditText mNameEditText, mSignature, mDescEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
    }

    @Override
    protected void findViewbyId() {
        back = (LinearLayout) findViewById(R.id.back_userinfo);
        avatar = (ImageView) findViewById(R.id.avatar);
        mNameEditText = (EditText) findViewById(R.id.nick_name_edittext);
        mSignature = (EditText) findViewById(R.id.signature_edittext);
        mDescEdit = (EditText) findViewById(R.id.u_desc);
        uType = (Spinner) findViewById(R.id.u_type);
        uDesc = (EditText) findViewById(R.id.u_desc);
        unLogin = (TextView) findViewById(R.id.unlogin_btn);
        submit = (Button) findViewById(R.id.userinfo_submit_btn);

    }

    @Override
    protected void initData() {
        UserInfo user = UserStatus.getUser();
        mNameEditText.setText(user.getTel());
        mSignature.setText(user.getTel());
        mDescEdit.setText(user.getDescription());
        ArrayAdapter<String> adapter;
        String m[] = {"运动员", "观赛者", "其他"};
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, m);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uType.setAdapter(adapter);
        uType.setSelection(user.getUtype());
        uDesc.setText(user.getDescription());
    }

    @Override
    protected void setListeners() {
        back.setOnClickListener(this);
        avatar.setOnClickListener(this);
        uType.setOnItemSelectedListener(this);
        unLogin.setOnClickListener(this);
        submit.setOnClickListener(this);
        mNameEditText.setOnClickListener(this);
        mSignature.setOnClickListener(this);
        mDescEdit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_userinfo:
                finish();
                break;
            case R.id.unlogin_btn:
                UtilTool.unLoginConfirm(UserInfoActivity.this);
                break;
            case R.id.userinfo_submit_btn:
                // 提交用户信息更新
                upload();
                break;
            case R.id.avatar:
                mPop(avatar);
                break;

            case R.id.nick_name_edittext:
                mNameEditText.setCursorVisible(true);
                break;
            case R.id.signature_edittext:
                mSignature.setCursorVisible(true);
                break;
            case R.id.u_desc:
                mDescEdit.setCursorVisible(true);
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    /*
     * 上传图片到服务器
     */
    public void upload() {
        // 上传头像
        uploadAvatar();
        long u_type = uType.getSelectedItemId();
        String description = uDesc.getText().toString();
        Request request = new UserUpdate(UserStatus.getUser().getUid(), null,
                u_type + "", description);
        NetWork.initNetWork(request, UserInfoActivity.this,
                new uploadCallback());
    }

    class uploadCallback implements CallBack {
        @Override
        public void onRequestComplete(Response result) {
            Boolean isSuccess = result.isSuccess();
            if (isSuccess) {
                Utils.ToastMSG(getApplicationContext(), "用户资料上传成功");
                UserInfo user = UserStatus.getUser();
                user.setUtype((int) uType.getSelectedItemId());
                user.setDescription(uDesc.getText().toString());
                finish();
            }
        }
    }

    /**
     * 上传头像
     */
    void uploadAvatar() {
        if (avatarPath == null || "".equals(avatarPath))
            return;
        HttpURLConnection http = null;
        String server = "http://app.dviz.cn/mobile";
        try {
            URL url = new URL(server);
            http = (HttpURLConnection) url.openConnection();
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setConnectTimeout(50000);
            http.setReadTimeout(50000);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "multipart/form-data");
            http.connect();
            OutputStream out = http.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");

            out.write("{\"type\":\"upload_avatar\",\"content\":{\"avatar\":"
                    .getBytes());
            System.out
                    .print("{\"type\":\"upload_avatar\",\"content\":{\"avatar\":");
            FileInputStream is = new FileInputStream(new File(avatarPath));
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                out.write(buffer);
            }
            out.write("}".getBytes());
            osw.flush();
            osw.close();
            out.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (http.getResponseCode() == 200) {
                String result = "";
                InputStream is = http.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        is, "utf-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                System.out.println("响应结果：" + result);
                in.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("响应出错！");
        }
    }

    /**
     * pop
     */
    PopupWindow popupWindow = null;
    TextView useCamera, fromPhotos, avatarCalcle;

    public void mPop(View parent) {
        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.pop_userinfo, null);
            popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            useCamera = (TextView) view.findViewById(R.id.use_camera);
            fromPhotos = (TextView) view.findViewById(R.id.from_photos);
            avatarCalcle = (TextView) view.findViewById(R.id.avatar_calcle);

        }
        useCamera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popupWindow.dismiss();
                camera();
            }
        });
        fromPhotos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popupWindow.dismiss();
                gallery();
            }
        });
        avatarCalcle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popupWindow.dismiss();
            }
        });
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(false);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    /*
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    /**
     * 剪切图片
     *
     * @param uri
     * @function:
     * @author:Jerry
     * @date:2013-12-30
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressLint("ShowToast")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            Uri uri = data.getData();
            avatarPath = uri.getPath();
            Bitmap bm = BitmapFactory.decodeFile(avatarPath);
            avatar.setImageBitmap(bm);
        }
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(UserInfoActivity.this, "未找到存储卡，无法存储照片！", 0)
                        .show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                this.avatar.setImageBitmap(bitmap);
                boolean delete = tempFile.delete();
                System.out.println("delete = " + delete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
