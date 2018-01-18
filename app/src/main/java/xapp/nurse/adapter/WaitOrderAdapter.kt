package xapp.nurse.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.widget.TextView
import com.bumptech.glide.Glide
import org.byteam.superadapter.SuperAdapter
import org.byteam.superadapter.SuperViewHolder
import xapp.nurse.R
import xapp.nurse.ui.model.OrderManage
import xapp.nurse.ui.model.WaitOrder
import xapp.nurse.uitls.getAge
import xapp.nurse.uitls.invisible
import xapp.nurse.uitls.toDate
import xapp.nurse.widget.CircleImageView

/**
 * Created by Administrator on 2017-11-27.
 */
class WaitOrderAdapter (context: Context, items:List<WaitOrder.DataBean.WaitListBean>, @LayoutRes layoutResId:Int): SuperAdapter<WaitOrder.DataBean.WaitListBean>(context, items, layoutResId) {
    override fun onBind(holder: SuperViewHolder, viewType: Int, layoutPosition: Int, item: WaitOrder.DataBean.WaitListBean) {

        val img_head=holder.findViewById<CircleImageView>(R.id.img_head)
        Glide.with(context).load(item.photo).into(img_head)
        val tv_Line=holder.findViewById<TextView>(R.id.tv_Line)
        tv_Line.invisible()
        holder.setText(R.id.mshpName,"申请人："+item.mshpName)
        holder.setText(R.id.arrangeTime, toDate(item.arrangeTime,6))
        holder.setText(R.id.productName,item.productName)
        holder.setText(R.id.gender,item.gender)
        holder.setText(R.id.birthday, getAge(item.birthday).toString()+"岁")
        holder.setText(R.id.tv_order,"接单")

    }
}