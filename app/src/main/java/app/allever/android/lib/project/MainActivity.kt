package app.allever.android.lib.project

import android.view.View
import androidx.lifecycle.lifecycleScope
import app.allever.android.lib.camerax.demo.CameraActivity
import app.allever.android.lib.common.BaseActivity
import app.allever.android.lib.core.ext.log
import app.allever.android.lib.core.ext.toast
import app.allever.android.lib.core.ext.toastLong
import app.allever.android.lib.core.function.businessinterceptor.demo.BusinessInterceptorActivity
import app.allever.android.lib.core.function.media.MediaBean
import app.allever.android.lib.core.function.media.MediaHelper
import app.allever.android.lib.core.function.media.MusicPlayer
import app.allever.android.lib.core.function.mediapicker.MediaPickerHelper
import app.allever.android.lib.core.function.mediapicker.MediaPickerResult
import app.allever.android.lib.core.function.work.*
import app.allever.android.lib.core.helper.ActivityHelper
import app.allever.android.lib.core.helper.TimeHelper
import app.allever.android.lib.mvp.demo.MvpActivity
import app.allever.android.lib.mvvm.base.BaseViewModel
import app.allever.android.lib.mvvm.base.MvvmConfig
import app.allever.android.lib.mvvm.demo.MvvmActivity
import app.allever.android.lib.network.demo.NetworkActivity
import app.allever.android.lib.permission.permissiox.demo.PermissionXActivity
import app.allever.android.lib.project.databinding.ActivityMainBinding
import app.allever.android.lib.widget.demo.RefreshRVActivity
import app.allever.android.lib.widget.mediapicker.MediaPickerListener
import app.allever.android.lib.widget.ripple.RippleHelper
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mMusicPlayer = MusicPlayer()


    private fun handleResult(result: MediaPickerResult) {
        val builder = StringBuilder()
        result.list.map {
            builder.append(it.path).append("\n")
            log("选中：${it.path}")
        }
        toastLong(builder.toString())
    }


    override fun isSupportSwipeBack(): Boolean {
        return false
    }

    override fun getContentMvvmConfig(): MvvmConfig  = MvvmConfig(R.layout.activity_main, BR.mainVM)

    override fun init() {
        initTopBar(getString(R.string.app_name), false)
        RippleHelper.addRippleView(findViewById(R.id.btnMvvm))
        RippleHelper.addRippleView(findViewById(R.id.btnMvp))
        RippleHelper.addRippleView(findViewById(R.id.btnNetwork))
        RippleHelper.addRippleView(findViewById(R.id.btnDialog))
        RippleHelper.addRippleView(findViewById(R.id.btnPermission))
        RippleHelper.addRippleView(findViewById(R.id.btnCamera))
        RippleHelper.addRippleView(findViewById(R.id.btnRefreshRV))
        RippleHelper.addRippleView(findViewById(R.id.btnImageLoader))
        RippleHelper.addRippleView(findViewById(R.id.btnBaseActivity))
        RippleHelper.addRippleView(findViewById(R.id.btnMediaSelector))
        RippleHelper.addRippleView(findViewById(R.id.btnMediaSelectorImageVideo))
        RippleHelper.addRippleView(findViewById(R.id.btnMediaSelectorImage))
        RippleHelper.addRippleView(findViewById(R.id.btnImageCompress))
        RippleHelper.addRippleView(findViewById(R.id.btnLoadMusic))
        RippleHelper.addRippleView(findViewById(R.id.btnPlayMusic))

        findViewById<View>(R.id.btnMvvm).setOnClickListener {
            ActivityHelper.startActivity(MvvmActivity::class.java)
//            ActivityHelper.startActivity(CropMainActivity::class.java)
//            ActivityHelper.startActivity(AndPermissionActivity::class.java)
        }

        findViewById<View>(R.id.btnMvp).setOnClickListener {
            ActivityHelper.startActivity(MvpActivity::class.java)
        }

        findViewById<View>(R.id.btnNetwork).setOnClickListener {
            ActivityHelper.startActivity(NetworkActivity::class.java)
        }

        findViewById<View>(R.id.btnDialog).setOnClickListener {
            ActivityHelper.startActivity(DialogActivity::class.java)
        }

        findViewById<View>(R.id.btnPermission).setOnClickListener {
//            ActivityHelper.startActivity(AndPermissionActivity::class.java)
            ActivityHelper.startActivity(PermissionXActivity::class.java)
        }

        findViewById<View>(R.id.btnCamera).setOnClickListener {
            ActivityHelper.startActivity(CameraActivity::class.java)
        }

        findViewById<View>(R.id.btnRefreshRV).setOnClickListener {
            ActivityHelper.startActivity(RefreshRVActivity::class.java)
        }

        findViewById<View>(R.id.btnImageLoader).setOnClickListener {
            ActivityHelper.startActivity(ImageLoaderActivity::class.java)
        }

        findViewById<View>(R.id.btnBusinessInterceptor).setOnClickListener {
            ActivityHelper.startActivity(BusinessInterceptorActivity::class.java)
        }

        findViewById<View>(R.id.btnBaseActivity).setOnClickListener {
            ActivityHelper.startActivity(UserActivity::class.java)
        }

        log("1天有${TimeHelper.getSecondByDay(1)}秒")
        log("2天有${TimeHelper.getSecondByDay(2)}秒")

        log("1小时${TimeHelper.getSecondByHour(1)}秒")
        log("2小时${TimeHelper.getSecondByHour(2)}秒")

        log("1分钟${TimeHelper.getSecondByMinute(1)}秒")
        log("2分钟${TimeHelper.getSecondByMinute(2)}秒")

        log("01:30有${TimeHelper.getSecondByMinute(1) + 30}秒")

        log("01:01:30有${TimeHelper.getSecondByTime(0, 1, 1, 30)}秒")


        CountDownTimer(this, 10 * 1000L) {
            log("倒计时定时器到了666")
        }.start()

        val mediaPickerListener = object : MediaPickerListener {
            override fun onPicked(
                all: MutableList<MediaBean>,
                imageList: MutableList<MediaBean>,
                videoList: MutableList<MediaBean>,
                audioList: MutableList<MediaBean>
            ) {
                val builder = StringBuilder()
                all.map {
                    builder.append(it.path).append("\n")
                    log("选中：${it.path}")
                }
                toastLong(builder.toString())
            }
        }

        findViewById<View>(R.id.btnMediaSelector).setOnClickListener {
            lifecycleScope.launch {
                val result = MediaPickerHelper.launchPicker(this@MainActivity,
                    MediaPickerHelper.TYPE_IMAGE,
                    MediaPickerHelper.TYPE_VIDEO,
                    MediaPickerHelper.TYPE_AUDIO
                )
                handleResult(result)
            }

//            MediaPicker.launchPickerDialog(
//                supportFragmentManager,
//                MediaHelper.TYPE_IMAGE,
//                MediaHelper.TYPE_VIDEO,
//                MediaHelper.TYPE_AUDIO,
//                mediaPickerListener = mediaPickerListener
//            )

            return@setOnClickListener
            lifecycleScope.launch {
                val mediaFolderList = MediaHelper.getAllFolder(this@MainActivity)
                log("媒体资源文件夹")
                mediaFolderList.map {
                    log("媒体资源文件夹: ${it.dir}")
                }
                val videoFolderList =
                    MediaHelper.getAllFolder(this@MainActivity, MediaHelper.TYPE_VIDEO, true)
                log("视频文件夹")
                videoFolderList.map {
                    log("视频文件夹: ${it.dir}")
                }
                val imageFolderList =
                    MediaHelper.getAllFolder(this@MainActivity, MediaHelper.TYPE_IMAGE, true)
                log("图片文件夹")
                imageFolderList.map {
                    log("图片文件夹: ${it.dir}")
                }

                val audioFolderList =
                    MediaHelper.getAllFolder(this@MainActivity, MediaHelper.TYPE_AUDIO, false)
                log("音频文件夹")
                audioFolderList.map { it ->
                    log("音频文件夹: ${it.dir}")
//                    val musicList = MediaHelper.getAudioMedia(this@MainActivity, it.dir, 0)
//                    musicList.map {
//                        log("music: ${it.musicTitle} - ${it.musicArtist} - ${it.musicAlbum}")
//                    }
                }
            }
        }

        findViewById<View>(R.id.btnMediaSelectorImageVideo).setOnClickListener {
            lifecycleScope.launch {
                val result = MediaPickerHelper.launchPicker(this@MainActivity,
                    MediaPickerHelper.TYPE_IMAGE,
                    MediaPickerHelper.TYPE_VIDEO
                )

                handleResult(result)
            }

        }

        findViewById<View>(R.id.btnMediaSelectorImage).setOnClickListener {
            lifecycleScope.launch {
                val result = MediaPickerHelper.launchPicker(this@MainActivity, MediaHelper.TYPE_IMAGE)
                handleResult(result)
            }

        }

        findViewById<View>(R.id.btnImageCompress).setOnClickListener {
            ActivityHelper.startActivity<ImageCompressActivity>()
        }

        val url = "https://win-web-ri01-sycdn.kuwo.cn/707652b674686c7f7f5540d05f90a886/63747cc0/resource/n1/40/9/760095559.mp3"
        findViewById<View>(R.id.btnLoadMusic).setOnClickListener {
            mMusicPlayer.load(url)
            mMusicPlayer.onPrepareListener = {
                mMusicPlayer.play()
            }
        }

        findViewById<View>(R.id.btnPlayMusic).setOnClickListener {
            mMusicPlayer.load(url, true)
        }


        object : TimerTask() {
            override fun delay() = 3 * 1000L
            override fun execute() = toast("执行定时任务 TimerTask")
        }.start()

        TimerTask2(this, 6000) {
//            toast("执行定时任务 TimerTask2")
        }.start()

        object : PollingTask(this) {
            override fun interval() = 1000L
            override fun condition() = true
            override fun execute() {
                log("执行轮训任务 PollingTask")
//                toast("执行轮训任务 PollingTask")
            }
        }.start()

        PollingTask2(lifecycleOwner = this, 1000, condition = {
            true
        }) {
            log("执行轮训任务 PollingTask2")
//            toast("执行轮训任务 PollingTask2")
        }.start()


    }

    override fun onDestroy() {
        super.onDestroy()
        mMusicPlayer.release()
    }
}

class MainViewModel : BaseViewModel() {
    override fun init() {
    }
}