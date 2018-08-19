package t.fbi.com.mghook

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.google.gson.Gson
import de.robv.android.xposed.XSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.File
import t.fbi.com.mghook.Devi
import java.io.FileNotFoundException
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        h.text="201808191330"

    }
}
