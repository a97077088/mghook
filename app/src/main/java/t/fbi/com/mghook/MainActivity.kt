package t.fbi.com.mghook

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        h.text="初始化配置成功"
        var pr=getPreferences(Activity.MODE_WORLD_READABLE+Activity.MODE_WORLD_WRITEABLE);
        var predt=pr.edit()
        predt.putString("imei","1111111111")
        predt.putString("deviid","22222222")
        predt.commit()


        imei.text=String.format("imei:%s",pr.getString("imei",""))
        deviid.text=String.format("deviid:%s",pr.getString("deviid",""))

    }
}
