package com.example.administrator.getrssi;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Collect collect;
    private WifiManager wm;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    double JRssi,Jlevel;
    double WRssi,Wlevel;
    double DRssi,Dlevel;
    double ARssi,Alevel;
    StringBuilder n1bulid,r1bulid,d1build,r2bulid,d2bulid,r3build,d3build,r4build,d4build;
    private SQLiteDatabase dbWrite, dbRead;
   // private SimpleCursorAdapter adapter;
    private Context context;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new mThread().start();
        collect = new Collect(this,"my.db",null,1);
        binview();
        dbRead = collect.getReadableDatabase();
        dbWrite = collect.getWritableDatabase();
        //adapter = new SimpleCursorAdapter(context,R.layout.activity_main,null,new String[]{"ID","Rssi","Distance"},new int[]{R.id.et2,R.id.et3,R.id.et4});



    }

     private void binview() {
        add = (Button)findViewById(R.id.button);
        add.setOnClickListener(btnAdd);
    }

  private View.OnClickListener btnAdd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            collect.getWritableDatabase();
            ContentValues values = new ContentValues();
            //第一条
            values.put("ID","ChinaNet");
            values.put("Rssi",JRssi);
            values.put("Distance",Jlevel);
            dbWrite.insert("person",null,values);
            values.clear();
                //第二条
                values.put("ID","2");
                values.put("RSSI",r2bulid.toString());
                values.put("Distance",d2bulid.toString());
                dbWrite.insert("person",null,values);
                values.clear();
                //第三条
                values.put("ID","3");
                values.put("RSSI",r3build.toString());
                values.put("Distance",d3build.toString());
                dbWrite.insert("person",null,values);
                values.clear();
                //第四条
                values.put("ID","4");
                values.put("RSSI",r4build.toString());
                values.put("Distance",d4build.toString());
                dbWrite.insert("person",null,values);
                values.clear();
        }
    };




    private class mThread extends Thread{
        @Override
        public void run(){
            while(true){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        obtainListInfo("ChinaNet","Tenda_54F060","TP-LINK_466C","CMCC-EDU");
                    }
                });
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private int obtainListInfo(String ssid,String sid,String Sid,String Ssid){

        editText1 = (EditText) findViewById(R.id.et1);
        editText2 = (EditText) findViewById(R.id.et2);
        editText3 = (EditText) findViewById(R.id.et3);
        editText4 = (EditText) findViewById(R.id.et4);
        editText5 = (EditText) findViewById(R.id.et5);
        //显示扫描到的所有wifi信息
        wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();

        int strength = wi.getRssi();
        int speed = wi.getLinkSpeed();
        String designation = wi.getSSID();

        String addr = wi.getBSSID();
        String unit = WifiInfo.LINK_SPEED_UNITS;

        if (wm.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            StringBuilder listinfo1 = new StringBuilder();
            StringBuilder listinfo2 = new StringBuilder();
            StringBuilder listinfo3 = new StringBuilder();
            StringBuilder listinfo4 = new StringBuilder();
            //搜索到的wifi列表信息
            List<ScanResult> scanResults = wm.getScanResults();

            for (ScanResult sr:scanResults){

                if(sr.SSID.equals(ssid)) {
                    listinfo1.append("wifi网络ID：");
                    listinfo1.append(sr.SSID);
                    listinfo1.append("\nwifi MAC地址：");
                    listinfo1.append(sr.BSSID);
                    listinfo1.append("\nwifi信号强度：");
                    listinfo1.append(sr.level);
                    int RSsi = Math.abs(sr.level);
                    Jlevel = (RSsi-35)/(10*2.1);
                    listinfo1.append(("\n距离"));
                    listinfo1.append(Jlevel+"\n\n");
                    JRssi = sr.level;
                   // d1build.append(mlevel);
                }
                if(sr.SSID.equals(Sid)) {
                    listinfo2.append("wifi网络ID：");
                    listinfo2.append(sr.SSID);
                    listinfo2.append("\nwifi MAC地址：");
                    listinfo2.append(sr.BSSID);
                    listinfo2.append("\nwifi信号强度：");
                    listinfo2.append(sr.level);
                    int RSsi = Math.abs(sr.level);
                    Wlevel = (RSsi-35)/(10*2.1);
                    listinfo2.append(("\n距离"));
                    listinfo2.append(Wlevel+"\n\n");
                    WRssi = sr.level;
                }
                if(sr.SSID.equals(sid)) {
                    listinfo3.append("wifi网络ID：");
                    listinfo3.append(sr.SSID);
                    listinfo3.append("\nwifi MAC地址：");
                    listinfo3.append(sr.BSSID);
                    listinfo3.append("\nwifi信号强度：");
                    listinfo3.append(sr.level);
                    int RSsi = Math.abs(sr.level);
                    Dlevel = (RSsi-35)/(10*2.1);
                    listinfo3.append(("\n距离"));
                    listinfo3.append(Dlevel+"\n\n");
                    DRssi = sr.level;
                }
                if(sr.SSID.equals(Ssid)) {
                    listinfo4.append("wifi网络ID：");
                    listinfo4.append(sr.SSID);
                    listinfo4.append("\nwifi MAC地址：");
                    listinfo4.append(sr.BSSID);
                    listinfo4.append("\nwifi信号强度：");
                    listinfo4.append(sr.level);
                    int RSsi = Math.abs(sr.level);
                    Alevel = (RSsi-35)/(10*2.1);
                    listinfo4.append(("\n距离"));
                    listinfo4.append(Alevel+"\n\n");
                    ARssi = sr.level;
                }
            }

            editText2.setText(listinfo1.toString());
            editText3.setText(listinfo2.toString());
            editText4.setText(listinfo3.toString());
            editText5.setText(listinfo4.toString());
            String curr_connected_wifi=null;
            curr_connected_wifi="Currently connecting WiFi \'"+designation+"\' \nRssi: "+strength+
                    "\nMac addr: "+addr+"\nspeed: "+speed+" "+unit;
            editText1.setText(curr_connected_wifi.toString());
        }
        return -1;
    }
}
