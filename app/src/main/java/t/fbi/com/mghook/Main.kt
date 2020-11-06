package t.fbi.com.mghook

import android.app.AndroidAppHelper
import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.telephony.TelephonyManager
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File
import java.io.FileNotFoundException
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XC_MethodHook.MethodHookParam
import de.robv.android.xposed.XC_MethodHook
import android.R.attr.classLoader
import android.R.attr.shortcutDisabledMessage
import android.util.JsonReader
import de.robv.android.xposed.XposedHelpers.findClass
import java.lang.System
import de.robv.android.xposed.XposedHelpers
import com.charleskorn.kaml.*
import com.google.gson.annotations.SerializedName
import java.io.FileReader
import kotlinx.serialization.*


@Serializable
data class DeviceInfo(
        var imei: String,
        var devid: String,
        var agent: String,
        var channel: String,
        var phonemode: String,
        var phonebrand: String,
)

public class Main :IXposedHookLoadPackage{
    var topack="com.cmcc.cmvideo"
    override fun handleLoadPackage(p0: XC_LoadPackage.LoadPackageParam) {
        if(p0.packageName!=topack){
            return;
        }
        XposedBridge.log("准备开始hook咪咕")

        XposedHelpers.findAndHookMethod(Application::class.java,"attach",Context::class.java, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                XposedBridge.log("已经设置咪咕hook")
//                var ct=(param.args[0] as Context)
//                var cl=(param.args[0] as Context).classLoader;
//                var DeviceUtil=cl.loadClass("com.cmcc.cmvideo.foundation.util.DeviceUtil")
//                var SdkUtil=cl.loadClass("com.cmvideo.analitics.common.SdkUtil")
//                var ChannelUtil=cl.loadClass("com.cmcc.cmvideo.foundation.util.ChannelUtil")
//                var ClarityUtil=cl.loadClass("com.cmcc.cmvideo.foundation.util.ClarityUtil")
//
//                var sdeviid=""
//                var simei=""
//                var sagent=""
//                var schannel=""
//                var sphoneMode=""
//                var sphoneBrand=""
//                var cfg:DeviceInfo
//                try{
//                    var fpath="${ct.filesDir}/dd.yaml"
//                    try {
//                        var txt=File(fpath).readText()
//                        cfg=Yaml.default.decodeFromString(DeviceInfo.serializer(),txt)
//                        XposedBridge.log(txt)
//                    }catch(e:Exception){
//                        XposedBridge.log(e.localizedMessage)
//                        cfg= DeviceInfo("861729039971363",
//                                "fe68296d50743c9e747c2d346ed36f55",
//                                "Dalvik/2.1.0 (Linux; U; Android 5.1; OPPO A37m Build/LMY47I)",
//                                "25000600-99000-200300280000001",
//                                "OPPO A37m",
//                                "OPPO"
//                        )
//                        var tostr=Yaml.default.encodeToString(DeviceInfo.serializer(),cfg)
//                        File(fpath).writeText(tostr)
//                    }
//                    simei=cfg.imei
//                    sdeviid=cfg.devid
//                    sagent=cfg.agent
//                    schannel=cfg.channel
//                    sphoneMode=cfg.phonemode
//                    sphoneBrand=cfg.phonebrand
//                    XposedBridge.log("获取imei和deviid成功")
//                }catch(e:Exception){
//                    XposedBridge.log(e)
//                }
//
//                XposedHelpers.setStaticObjectField(android.os.Build::class.java, "MODEL", sphoneMode)
//                XposedHelpers.setStaticObjectField(android.os.Build::class.java, "BRAND", sphoneBrand)
//
//
//
//
//                XposedHelpers.findAndHookMethod(DeviceUtil, "getDeviceBrand", object : XC_MethodHook() {
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("已经修改getDeviceBrand")
//                        param.result = sphoneBrand;
//                    }
//                });
//
//                XposedHelpers.findAndHookMethod(DeviceUtil, "getSystemModel", object : XC_MethodHook() {
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("已经修改getSystemModel")
//                        param.result = sphoneMode;
//                    }
//                });
//
//
//
//                XposedHelpers.findAndHookMethod(DeviceUtil, "getUUID", object : XC_MethodHook() {
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("已经修改getUUID")
//                        param.result = sdeviid;
//                    }
//                });
//
//
//
//                XposedHelpers.findAndHookMethod(SdkUtil,"generateUdid",Context::class.java,object: XC_MethodHook(){
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("已经修改generateUdid")
//                        param.result=sdeviid;
//                    }
//                })
//
//                XposedHelpers.findAndHookMethod(ChannelUtil,"getFullChannel",object :XC_MethodHook(){
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        param.result=schannel
//                    }
//                })
//
//                XposedHelpers.findAndHookMethod(System::class.java,"getProperty",String::class.java,object :XC_MethodHook(){
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        var k=param.args[0] as String;
//                        if(k=="http.agent"){
//                            param.result=sagent
//                        }
//                    }
//                })
//
//
//                XposedHelpers.findAndHookMethod(TelephonyManager::class.java,"getDeviceId",object :XC_MethodHook(){
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("已经修改imei")
//                        param.result=simei;
//                    }
//                });
//
//                XposedHelpers.findAndHookMethod(ClarityUtil,"getRate",object :XC_MethodHook(){
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("已经修改getRate")
//                        param.result="2";
//                    }
//                });

            }
        })





    }
}