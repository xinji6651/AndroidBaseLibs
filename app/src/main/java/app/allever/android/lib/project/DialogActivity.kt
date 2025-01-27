package app.allever.android.lib.project

import app.allever.android.lib.common.BaseActivity
import app.allever.android.lib.mvvm.base.BaseViewModel
import app.allever.android.lib.mvvm.base.MvvmConfig
import app.allever.android.lib.project.databinding.ActivityDialogBinding
import app.allever.android.lib.widget.ripple.RippleHelper

class DialogActivity: BaseActivity<ActivityDialogBinding, DialogViewModel>() {
    override fun getContentMvvmConfig() = MvvmConfig(R.layout.activity_dialog, BR.dialogVM)
    override fun init() {
        initTopBar("弹窗")
        RippleHelper.addRippleView(binding.btnBottomDialog)
        RippleHelper.addRippleView(binding.btnCenterDialog)
        RippleHelper.addRippleView(binding.btnNotificationPopWindow)
        RippleHelper.addRippleView(binding.btnAbstractPopupWindow)
        binding.btnBottomDialog.setOnClickListener {
            BottomDialog().show(supportFragmentManager)
        }
        binding.btnCenterDialog.setOnClickListener {
            CenterDialog(this).show()
        }
        val firstPopWindow = FirstPopWindow(this)
        val firstPopWindow2 = FirstPopWindow(this)
        binding.btnNotificationPopWindow.setOnClickListener {
            firstPopWindow.show(offsetYDp = 42)
        }
        binding.btnAbstractPopupWindow.setOnClickListener {
            NotificationWindow(this).show(window.decorView)
        }
    }
}

class DialogViewModel : BaseViewModel() {
    override fun init() {
    }
}