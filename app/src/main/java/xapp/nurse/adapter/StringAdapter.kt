package xapp.nurse.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.widget.ImageView

import org.byteam.superadapter.SuperAdapter
import org.byteam.superadapter.SuperViewHolder
import xapp.nurse.R

/**
 * Created by Dell on 2017/10/25.
 */

class StringAdapter(context: Context, items: List<String>, @LayoutRes layoutResId: Int) : SuperAdapter<String>(context, items, layoutResId) {

    override fun onBind(holder: SuperViewHolder, viewType: Int, layoutPosition: Int, item: String) {
//        val iv = holder.findViewById<ImageView>(R.id.image)
//        holder.setText(R.id.textView,"1111")
    }
}
