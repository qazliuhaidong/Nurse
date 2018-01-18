package xapp.nurse.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.bumptech.glide.Glide
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.*
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import kotlinx.android.synthetic.main.na_activity_set.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import xapp.nurse.R
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseActivity
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.model.GetOrder
import xapp.nurse.ui.model.Login
import xapp.nurse.uitls.AppManager
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.SharedUtils
import java.io.File

/**
 * Created by Dell on 2017/10/31.
 */

class SetActivity : BaseActivity(), TakePhoto.TakeResultListener, InvokeListener {

    private var info: Login.DataBean? = null
    override val layoutId: Int
        get() = R.layout.na_activity_set
    override fun afterInitView() {
        setLightStatusBar()
        val utils= SaveObjectUtils(this,"info")
         info=utils.getObject("login", Login.DataBean::class.java)
        if (info?.imgPath!=null) {
            Glide.with(this).load(info?.imgPath).into(img_head)
        }
        ib_back.setOnClickListener {
            finish()
        }
        tv_tel.text=info?.mobile!!.toString()
        rl_img.setOnClickListener {
            AlertView("上传头像", null, "取消", null,
                    arrayOf("拍照", "从相册中选择"),
                    this, AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
                val file = File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg")
                if (!file.parentFile.exists()) file.parentFile.mkdirs()
                val imageUri = Uri.fromFile(file)
                configCompress(takePhoto!!)
                if (position == 0) {
                    takePhoto?.onPickFromCaptureWithCrop(imageUri, getCropOptions())//拍照剪切
                } else if (position == 1) {
                    takePhoto?.onPickMultipleWithCrop(1, getCropOptions()) //相册选取1张照片并剪切
                }
            }).show()
        }

        tv_updatePWD.setOnClickListener {
            warpActivity(UpdatePwdActivity::class.java)
        }
        tv_about.setOnClickListener { warpActivity(AboutActivity::class.java) }
        tv_logoff.setOnClickListener {
            AppManager.finishAllActivity()
            SharedUtils.setPrefString(baseContext, "account", "")
            SharedUtils.setPrefString(baseContext, "password", "")
            warpActivity(LoginActivity::class.java, true)


        }
    }



    private var takePhoto: TakePhoto? = null
    private var invokeParam: InvokeParam? = null

    override fun takeSuccess(result: TResult?) {
        Glide.with(this).load(File(result?.image?.compressPath)).into(img_head)
        val file = File(result?.image?.compressPath)
        var staffId:Int=-1
        staffId=info?.staffId!!
        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("staffId", info?.staffId.toString() + "")//其他参数
        val imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        builder.addFormDataPart("avatarFile", file.name, imageBody)//后台需要的图片key
        val parts = builder.build().parts()
        getApiResult(ApiManager.getImg(staffId,parts), MTCallBack<GetOrder>().apply {
            success {
                if (it.code==10000){
                    Toast.makeText(this@SetActivity, "上传成功", Toast.LENGTH_SHORT).show()
                    val account = SharedUtils.getPrefString(mContext!!, "account", "")
                    val password = SharedUtils.getPrefString(mContext!!, "password", "")
                    getApiResult(ApiManager.Login(account,password,2),MTCallBack<Login>().apply {
                             success {
                                 if (it.code==10000){
                                     SaveObjectUtils(baseContext, "info").setObject("login", it.data!!)
                                 }
                             }

                    })
                }
            }
        })

    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {

    }

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam!!.method)
        if (PermissionManager.TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam

        }
        return type
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getTakePhoto().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        getTakePhoto().onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun configCompress(takePhoto: TakePhoto) {

        //图片压缩
        val maxSize = 102400//B
        val width = 400
        val height = 400//px
        val showProgressBar = true//显示压缩进度
        val enableRawFile = true//保存原文件
        val config: CompressConfig
        config = CompressConfig.Builder()
                .setMaxSize(maxSize)
                .setMaxPixel(if (width >= height) width else height)
                .enableReserveRaw(enableRawFile)
                .create()
        takePhoto.onEnableCompress(config, showProgressBar)

        //不压缩
        //takePhoto.onEnableCompress(null,false);


        val builder = TakePhotoOptions.Builder()
        builder.setWithOwnGallery(true)//使用takephoto相册
        builder.setCorrectImage(true)//纠正拍照旋转角度
        takePhoto.setTakePhotoOptions(builder.create())
    }

    private fun getCropOptions(): CropOptions {
        //剪切
        val height = 400
        val width = 400
        val withWonCrop = true
        val builder = CropOptions.Builder()
        builder.setOutputX(width).setOutputY(height)
        builder.setWithOwnCrop(withWonCrop)
        return builder.create()
    }

    /**
     * 获取TakePhoto实例

     * @return
     */
    fun getTakePhoto(): TakePhoto {
        if (takePhoto == null) {
            takePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
        }

        return takePhoto!!
    }
}
