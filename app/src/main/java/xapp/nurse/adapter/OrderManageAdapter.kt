package xapp.nurse.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import com.bumptech.glide.Glide

import org.byteam.superadapter.SuperAdapter
import org.byteam.superadapter.SuperViewHolder
import xapp.nurse.R
import xapp.nurse.ui.model.OrderManage
import xapp.nurse.uitls.getAge
import xapp.nurse.uitls.toDate
import xapp.nurse.widget.CircleImageView


/**
 * Created by Administrator on 2017-11-27.
 */
class OrderManageAdapter(context: Context,items:List<OrderManage.DataBean>,@LayoutRes layoutResId:Int):SuperAdapter<OrderManage.DataBean>(context,items,layoutResId) {
    override fun onBind(holder: SuperViewHolder, viewType: Int, layoutPosition: Int, item: OrderManage.DataBean) {

        val img_head=holder.findViewById<CircleImageView>(R.id.img_head)
        Glide.with(context).load(item.photo).into(img_head)
        holder.setText(R.id.mshpName,item.mshpName)
        holder.setText(R.id.arrangeTime, toDate(item.arrangeTime,6))
        holder.setText(R.id.productName,item.productName)
        holder.setText(R.id.gender,item.gender)
        holder.setText(R.id.birthday, getAge(item.birthday).toString()+"岁")
    }

}