package app.example.com.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

//RecyclerView + Retrofit + Rxjava + lambda表达式 请求网络数据的简单使用
class MainActivity : AppCompatActivity() {
    lateinit var adapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //设置布局管理器以及适配器
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        //注意这里提取公有变量时不准确，须自己手写
        adapter = MyAdapter(this)
        recyclerView.adapter = adapter


        getData()
    }

    //定义函数，获取网络数据的方法
    fun getData(){
        //构建retrofit
        var retrofit = Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        //实例化接口
        var service : GetDataInterface = retrofit.create(GetDataInterface::class.java)

        //异步请求数据（含有lambda表达式）
        service.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    next ->     //next 等同于执行 onNext 方法的返回值
                    var result = next.string()
                    println("请求数据："+result.toString())

                    //返回DataBean数据
                    var gson = Gson()
                    var bean = gson.fromJson(result,Bean.DataBean::class.java)

                    //添加请求的数据
                    adapter.addData(bean)

                },{
                    t ->       //t相当于执行返回String类型数据的 onError 方法
                },{
                    //oncomplete //oncomplete相当于执行 onComplete 方法
                },{
                    //d ->      //d相当于执行返回String类型数据的 dispose 方法
                })
    }

}

