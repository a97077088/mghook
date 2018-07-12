package t.fbi.com.mghook

import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.telephony.TelephonyManager
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage


public class Main :IXposedHookLoadPackage{
    var topack="com.cmcc.cmvideo"
    var topack1="t.fbi.com.myapplication"

    override fun handleLoadPackage(p0: XC_LoadPackage.LoadPackageParam) {

        if(p0.packageName!=topack&&p0.packageName!=topack1){
        return;
        }
        XposedBridge.log("插件开始生效")
        XposedBridge.log(String.format("当前包名:%s",p0.packageName));
        XposedBridge.log("准备读取配置文件")
        var pv=XSharedPreferences("t.fbi.com.mghook","MainActivity")
        var simei=pv.getString("imei","")
        var sdeviid=pv.getString("deviid","")
        if(simei==""||sdeviid==""){
            XposedBridge.log("获取配置文件失败，取消hook")
            return
        }

        XposedBridge.log(String.format("获取到imei:%s",simei))
        XposedBridge.log(String.format("获取到devi:%s",simei))


        XposedHelpers.findAndHookMethod(TelephonyManager::class.java,"getDeviceId",object :XC_MethodHook(){
            override fun afterHookedMethod(param: MethodHookParam) {
                XposedBridge.log("已经修改imei")
                param.result=simei;
            }
        });
        if(p0.packageName==topack1){
            return;
        }


        XposedBridge.log("已经设置咪咕hook")
        XposedHelpers.findAndHookMethod(Application::class.java,"attach",Context::class.java, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                XposedBridge.log("准备开始hook咪咕")
                var cl=(param.args[0] as Context).classLoader;
                var DeviceUtil=cl.loadClass("com.cmcc.cmvideo.foundation.util.DeviceUtil")
                var SdkUtil=cl.loadClass("com.cmvideo.analitics.common.SdkUtil")


                XposedHelpers.findAndHookMethod(DeviceUtil, "getUUID", object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("已经修改getUUID")
                        param.result = sdeviid;
                    }
                });
                XposedHelpers.findAndHookMethod(SdkUtil,"generateUdid",Context::class.java,object: XC_MethodHook(){
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("已经修改generateUdid")
                        param.result=sdeviid;
                    }
                })

            }
        })





    }
}