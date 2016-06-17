package com.gototongcheng.Presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.gototongcheng.application.R;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by admin on 16/6/17.
 */
public class CommonBaiduMapPresenter extends BasePresenter {

    private LocationClient mLocationClient;
    private TextView textView;
    public CommonBaiDuMapWidget commonBaiDuMapWidget;
    public CommonBaiduMapPresenter(){

    }
    public CommonBaiduMapPresenter(Activity activity){

        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        if(commonBaiDuMapWidget == null) {
            commonBaiDuMapWidget = new CommonBaiDuMapWidget();
        }
        commonBaiDuMapWidget.mvCommonBaiDu = (MapView)activity.findViewById(R.id.mv_common_baidu);
        mLocationClient = new LocationClient(activity);
        mLocationClient.registerLocationListener(new MyLocationListener());
        InitLocation();

    }
    public void startLoc(TextView textView){
        this.textView = textView;
        mLocationClient.start();
    }


    public void onDestroy(){
        mLocationClient.stop();
    }

    /**
     * 实现实位回调监听
     */
    public class MyLocationListener implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            Toast.makeText(activity,"正在定位中",Toast.LENGTH_LONG).show();

            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("error code : ");
            sb.append(location.getLocType());
            sb.append("latitude : ");
            sb.append(location.getLatitude());
            sb.append("lontitude : ");
            sb.append(location.getLongitude());
            //       Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
            //       tv_test.setText("" + location.getAddrStr());
            sb.append("radius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation){
                sb.append("speed : ");
                sb.append(location.getSpeed());
                sb.append("satellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("direction : ");
                sb.append("addr : ");
                sb.append(location.getAddrStr());
                //      tv_test.setText(location.getAddrStr());
                sb.append(location.getDirection());
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                sb.append("addr : ");
                sb.append(location.getAddrStr());
                //        tv_test.setText(""+location.getAddrStr());
                //运营商信息
                sb.append("operationers : ");
                sb.append(location.getOperators());
            }
            String address = location.getAddrStr();
        //    latitude = "" + location.getLatitude();
       //     longitude = "" + location.getLongitude();
            textView.setText(address);
            onDestroy();
            //          LocationResult.setText(sb.toString());
            Log.i("dwtedx", sb.toString());

            System.out.println("this is background");

        }
    }
    private void InitLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);//设置定位模式
        option.setCoorType("gcj02"); // 设置坐标类型 bd09ll
        // 需要地址信息，设置为其他任何值（string类型，且不能为null）时，都表示无地址信息。
        option.setAddrType("all");
        // 设置是否返回POI的电话和地址等详细信息。默认值为false，即不返回POI的电话和地址信息。
        //       option.setPoiExtraInfo(true);
        // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        option.setProdName("通过GPS定位我当前的位置");
        // 禁用启用缓存定位数据
        option.disableCache(true);
        option.setOpenGps(true);
        // 设置最多可返回的POI个数，默认值为3。由于POI查询比较耗费流量，设置最多返回的POI个数，以便节省流量。
        //    option.setPoiNumber(3);
        // 设置定位方式的优先级。
        // 当gps可用，而且获取了定位结果时，不再发起网络请求，直接返回给用户坐标。这个选项适合希望得到准确坐标位置的用户。如果gps不可用，再发起网络请求，进行定位。
        option.setPriority(LocationClientOption.GpsFirst);
  //      int span=5000;
 //       option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setIgnoreKillProcess(true);
        option.setLocationNotify(true);
        mLocationClient.setLocOption(option);


        /*
                option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
         */
    }

    public class CommonBaiDuMapWidget{
        public MapView mvCommonBaiDu;
    }
}
