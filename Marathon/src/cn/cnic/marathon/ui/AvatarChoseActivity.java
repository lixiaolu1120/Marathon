package cn.cnic.marathon.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.util.Utils;

public class AvatarChoseActivity extends BaseActivity implements
		OnClickListener {
	private TextView useCamera, fromPhotos, submit, cancle;
	private ImageView avatar;
	private Uri fileUri;
	private static final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/DCIM/Camera");
	private String fileName;// 图片名称
	private File mCurrentPhotoFile;
	private static final int PIC_REQUEST_CODE_SELECT_CAMERA = 1; // 标识请求照相功能的activity
	private static final int PIC_Select_CODE_ImageFromLoacal = 2;// 标识请求相册取图功能的activity

	ProgressDialog delLoadingDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.translucent);
		setContentView(R.layout.activity_avatar_chose);
	}

	@Override
	protected void findViewbyId() {
		avatar = (ImageView) findViewById(R.id.tmp_avatar);
		useCamera = (TextView) findViewById(R.id.use_camera);
		fromPhotos = (TextView) findViewById(R.id.from_photos);
		submit = (TextView) findViewById(R.id.avatar_submit);
		cancle = (TextView) findViewById(R.id.avatar_calcle);
	}

	@Override
	protected void setListeners() {
		useCamera.setOnClickListener(this);
		fromPhotos.setOnClickListener(this);
		cancle.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.use_camera:
			callCamera();
			break;
		case R.id.from_photos:
			String status = Environment.getExternalStorageState();
			if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, PIC_Select_CODE_ImageFromLoacal);
			} else {
				Toast.makeText(AvatarChoseActivity.this, "没有SD卡",
						Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.avatar_submit:
			Intent intent = new Intent(AvatarChoseActivity.this,
					UserInfoActivity.class);
			intent.setData(fileUri);
			setResult(1, intent);
			finish();
			break;
		case R.id.avatar_calcle:
			setResult(0);
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			switch (requestCode) {

			// 照相机程序返回的
			case PIC_REQUEST_CODE_SELECT_CAMERA: {
				try {
					fileUri = Uri.fromFile(mCurrentPhotoFile);
					sendBroadcast(new Intent(
							Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, fileUri));
					Bitmap bm = BitmapFactory.decodeFile(fileUri.getPath());
					avatar.setImageBitmap(bm);
				} catch (Exception e) {
				}
				break;
			}
			case PIC_Select_CODE_ImageFromLoacal:
				Uri uri = data.getData();
				boolean isSDCard = true;
				ContentResolver cr = getContentResolver();
				Cursor cursor = cr.query(uri, null, null, null, null);
				if (cursor != null) {
					cursor.moveToFirst();
					isSDCard = false;
					fileName = cursor.getString(3);
					mCurrentPhotoFile = new File(cursor.getString(1)); // 图片文件路径
				} else {
				}
				if (isSDCard) {
					mCurrentPhotoFile = new File(uri.getEncodedPath());
					fileName = uri.getEncodedPath().substring(
							uri.getEncodedPath().lastIndexOf("/"));
				} else {
				}
				if (mCurrentPhotoFile.exists()) {
					fileUri = Uri.fromFile(mCurrentPhotoFile);
					sendBroadcast(new Intent(
							Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, fileUri));
				} else {
				}
				Bitmap bm = BitmapFactory.decodeFile(fileUri.getPath());
				avatar.setImageBitmap(bm);
				break;
			}
		}
	}

	/**
	 * 调用照相机
	 */
	private void callCamera() {
		try {
			String status = Environment.getExternalStorageState();
			if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
				PHOTO_DIR.mkdirs();// 创建照片的存储目录
				fileName = getPhotoFileName();
				mCurrentPhotoFile = new File(PHOTO_DIR, fileName);// 给新照的照片文件命名
				Intent cIntent = getTakePickIntent(mCurrentPhotoFile);
				startActivityForResult(cIntent, PIC_REQUEST_CODE_SELECT_CAMERA);
			} else {
			}

		} catch (ActivityNotFoundException e) {
		}
	}

	// 用当前时间给取得的图片命名
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		return Utils.getStringFromDate(date, "'IMG'_yyyyMMdd_HHmmss") + ".jpg";
	}

	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		return intent;
	}
}
