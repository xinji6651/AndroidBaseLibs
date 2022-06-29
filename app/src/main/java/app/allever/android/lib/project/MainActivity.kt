package app.allever.android.lib.project

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import app.allever.android.lib.core.base.AbstractActivity
import app.allever.android.lib.core.ext.log
import app.allever.android.lib.core.ext.toast
import app.allever.android.lib.core.function.businessinterceptor.demo.BusinessInterceptorActivity
import app.allever.android.lib.core.function.work.PollingTask
import app.allever.android.lib.core.function.work.PollingTask2
import app.allever.android.lib.core.function.work.TimerTask
import app.allever.android.lib.core.function.work.TimerTask2
import app.allever.android.lib.core.helper.ActivityHelper
import app.allever.android.lib.mvp.demo.MvpActivity
import app.allever.android.lib.mvvm.base.BaseViewModel
import app.allever.android.lib.mvvm.demo.MvvmActivity
import app.allever.android.lib.network.demo.NetworkActivity
import app.allever.android.lib.permission.permissiox.demo.PermissionXActivity
import app.allever.android.lib.project.databinding.ActivityMainBinding
import app.allever.android.lib.widget.demo.RefreshRVActivity
import app.allever.android.lib.widget.ripple.RippleHelper

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        RippleHelper.addRippleView(findViewById(R.id.btnMvvm))
        RippleHelper.addRippleView(findViewById(R.id.btnMvp))
        RippleHelper.addRippleView(findViewById(R.id.btnNetwork))
        RippleHelper.addRippleView(findViewById(R.id.btnDialog))
        RippleHelper.addRippleView(findViewById(R.id.btnPermission))
        RippleHelper.addRippleView(findViewById(R.id.btnRefreshRV))
        RippleHelper.addRippleView(findViewById(R.id.btnImageLoader))
        RippleHelper.addRippleView(findViewById(R.id.btnBaseActivity))

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

        object : TimerTask() {
            override fun delay() = 3 * 1000L
            override fun execute() = toast("执行定时任务 TimerTask")
        }.start()

        TimerTask2(6000) {
            toast("执行定时任务 TimerTask2")
        }.start()

        object : PollingTask() {
            override fun interval() = 1000L
            override fun condition() = true
            override fun execute() {
                log("执行轮训任务 PollingTask")
                toast("执行轮训任务 PollingTask")
            }
        }.start()

        PollingTask2(1000, condition = {
            true
        }) {
            log("执行轮训任务 PollingTask2")
            toast("执行轮训任务 PollingTask2")
        }.start()

    }

    override fun isSupportSwipeBack(): Boolean {
        return false
    }
}

class MainViewModel : BaseViewModel() {
    override fun init() {

    }
}