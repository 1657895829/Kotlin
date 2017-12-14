package app.example.com.kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * 数据适配器类,传入构造参数：上下文对象
 * 重写的方法带有的蓝色字体要注释或直接删除
 */
class MyAdapter(context1 : Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder02>() {
    var context : Context = context1
    var list : ArrayList<Bean.Book>  = ArrayList()

    //添加数据的方法
    fun addData(bean: Bean.DataBean){
        list.addAll(bean.result.bookList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder02 {

        //设置布局
        var view = LayoutInflater.from(context).inflate(R.layout.adapter,parent,false)
        return MyViewHolder02(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder02?, position: Int) {

        //加载布局（刚开始写holder会报 . 有错误，需要在 . 前加上 !!  表示数据不为空）
        holder!!.item_textview.setText(list.get(position).name)
        Glide.with(context).load(list.get(position).coverImg).into(holder.item_imageview)
    }


    override fun getItemCount(): Int {
        return list.size

    }

    /*设置Viewholder类  共有 2 种方法获取参数*/

    // 方法 1 ：使用构造方法获取参数
    class MyViewHolder01  : RecyclerView.ViewHolder {
        //使用延迟变量
        lateinit var item_imageview : ImageView
        lateinit var item_textview : TextView

        constructor(view : View) : super(view){
            item_imageview = view.findViewById(R.id.item_imageview)
            item_textview = view.findViewById(R.id.item_textview)
        }

    }


    //  方法 2 ：使用初始化代码块获取参数
    class MyViewHolder02(view : View) : RecyclerView.ViewHolder(view) {
        //使用延迟变量
        lateinit var item_imageview : ImageView
        lateinit var item_textview : TextView

        init {
            item_imageview = view.findViewById(R.id.item_imageview)
            item_textview = view.findViewById(R.id.item_textview)
        }

    }
}