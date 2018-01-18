package xapp.nurse.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.na_fragment_personal.*

import xapp.nurse.R
import xapp.nurse.R.id.staffName
import xapp.nurse.api.ApiManager
import xapp.nurse.base.BaseFragment
import xapp.nurse.base.net.MTCallBack
import xapp.nurse.ui.activity.*
import xapp.nurse.ui.model.Login
import xapp.nurse.uitls.SaveObjectUtils
import xapp.nurse.uitls.SharedUtils

/**
 * Created by Dell on 2017/10/24.
 */

class PersonalFragment : BaseFragment() {

    override val layoutResource: Int
        get() = R.layout.na_fragment_personal
    override fun afterInitView() {

        val utils= SaveObjectUtils(activity,"info")
        val info=utils.getObject("login", Login.DataBean::class.java)
        ib_right.setOnClickListener { warpActivity(SetActivity::class.java) }
        staffName.text=info?.staffName
        mobile.text=info?.mobile
        integral.text=info?.integral.toString()
        if (info!=null&&info?.imgPath!=null) {
            Glide.with(this).load(info?.imgPath).into(picture)
        }
        rlWallet.setOnClickListener {
            warpActivity(AccountWallet::class.java)
        }
        bind.setOnClickListener {
            warpActivity(BindWalletActivity::class.java)
        }
        r_count.setOnClickListener {
            warpActivity(CountActivity::class.java)
        }
    }
    override fun onStart() {
        super.onStart()
        val utils= SaveObjectUtils(activity,"info")
        val info=utils.getObject("login", Login.DataBean::class.java)
        if(info!=null&&info.imgPath!=null){
            Glide.with(this).load(info?.imgPath).into(picture)
        }
    }
    companion object {
        fun get(): PersonalFragment {
            return Inner.anotherSingle
        }

        private object Inner {
            val anotherSingle = PersonalFragment()
        }
    }



}
