package com.anji.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class WebUtil{
  //private static final String NAMESPACE = "http://service.functions.com";
	private static final String NAMESPACE = "http://services.web.uc.saic.com";
	private static final String BRANDURL = "http://180.166.157.114:8080/saic-uc-web/services/brandService";//第3个参数场次ID必输入，无数据
	private static final String BIDDINGSESSIONURL = "http://180.166.157.114:8080/saic-uc-web/services/biddingSessionService";//输入城市，必输入，无数据
	private static final String CITYURL = "http://180.166.157.114:8080/saic-uc-web/services/cityService";//OK
	private static final String LOGINURL = "http://10.32.1.247:8080/saic-uc-web/services/loginWebService?wsdl";//OK
	private static final String CURRENTPRICEURL = "http://180.166.157.114:8080/saic-uc-web/services/currentPriceService";//竞品ID
	private static final String OFFERDATAURL="http://180.166.157.114:8080/saic-uc-web/services/offerDataService";//竞品ID和价钱   出价接口
	private static final String BIDDINGLISTURL = "http://180.166.157.114:8080/saic-uc-web/services/biddingListService";//无方法调用
	private static final String BIDDINGDELURL = "http://180.166.157.114:8080/saic-uc-web/services/biddingDelService"; //输入竞品ID，用户ID必输入，无数据      竞品详情
	private static final String AUCTIONITEMSINFORURL = "http://180.166.157.114:8080/saic-uc-web/services/auctionItemsInfoService";//输入竞品ID, 车辆检查项目
	private static final String BOUGHTACTIONLISTURL = "http://180.166.157.114:8080/saic-uc-web/services/boughtAuctionListService";//无方法调用
//获取城市1
    public static List<Map<String,String>> getCities(){
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "getCities");
    		
			AndroidHttpTransport ht = new AndroidHttpTransport(CITYURL);
			ht.debug = true;

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"getCities", envelope);
			
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("getCities resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("getCities property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    //登入2
    public static List<Map<String,String>> login(String UserName,String UserPwd){
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "login");
    		rpc.addProperty("str","{\"UserName\":\"user1\",\"UserPwd\":\"666666\"}");
			AndroidHttpTransport ht = new AndroidHttpTransport(LOGINURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"login", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("login resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("login property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    //获取场次3
    public static List<Map<String,String>> getAuctionRooms(String City){
    	System.out.println("=-=-=--333+++获取场次");
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "getAuctionRooms");
    		rpc.addProperty("City",City);
			AndroidHttpTransport ht = new AndroidHttpTransport(BIDDINGSESSIONURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"getAuctionRooms", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("getAuctionRooms resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("getAuctionRooms property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    //获取品牌4
    public static List<Map<String,String>> getBrand(String City){
     	System.out.println("=-=-=--44444");
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "getBrand");
    		//rpc.addProperty("str","{\"UserName\":\"user1\",\"UserPwd\":\"666666\"}");
			AndroidHttpTransport ht = new AndroidHttpTransport(BRANDURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"getBrand", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("getBrand resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("getBrand property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    //获取场次竞品列表5
    public static List<Map<String,String>> GetAuctionListByRoom(String PageSize,String CurPage,String RoomId,String MinPrice,String MaxPrice,String MinPeriod,String MaxPeriod,String Brand,String AuctionStatus,String SortPrice,String SortBrand){
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "getAuctionListByRoom");
    		rpc.addProperty("str","{\"PageSize\":\""+PageSize+"\",\"CurPage\":\""+CurPage+"\",\"RoomId\":\""+RoomId+"\",\"MinPrice\":\""+MinPrice+"\",\"MaxPrice\":\""+MaxPrice+"\",\"MinPeriod\":\""+MinPeriod+"\",\"MaxPeriod\":\""+MaxPeriod+"\",\"Brand\":\""+Brand+"\",\"AuctionStatus\":\""+AuctionStatus+"\",\"SortPrice\":\""+SortPrice+"\",\"SortBrand\":\""+SortBrand+"\"}");

			AndroidHttpTransport ht = new AndroidHttpTransport(BRANDURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"getAuctionListByRoom", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("getBrand resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("getBrand property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    //获取竞品详情6
    public static List<Map<String,String>> GetAuctionById(String AuctionId,String userId){
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "GetAuctionById");
    		rpc.addProperty("str","{\"AuctionId\":\""+AuctionId+"\",\"userId\":\""+userId+"\"}");

			AndroidHttpTransport ht = new AndroidHttpTransport(BIDDINGDELURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"GetAuctionById", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("GetAuctionById resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("GetAuctionById property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    /*
     * AuctionId
UserId

     * */
    //获取车辆检查项目7
    public static List<Map<String, String>> getAuctionItemsInfo(String AuctionId,String UserId){
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "getAuctionItemsInfo");
    		rpc.addProperty("str","{\"AuctionId\":\""+AuctionId+"\",\"UserId\":\""+UserId+"\"}");
    		
			AndroidHttpTransport ht = new AndroidHttpTransport(AUCTIONITEMSINFORURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"getAuctionItemsInfo", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("getAuctionItemsInfo resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("getAuctionItemsInfo property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    /*
     * AuctionId
Price
IPAddress
UserId
     * */
    
   //出价接口8
    public static List<Map<String,String>> bid(String AuctionId,String Price,String IPAddress,String UserId){
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "GetAuctionById");
    		rpc.addProperty("str","{\"AuctionId\":\""+AuctionId+"\",\"Price\":\""+Price+"\",\"IPAddress\":\""+IPAddress+"\",\"UserId\":\""+UserId+"\"}");
			AndroidHttpTransport ht = new AndroidHttpTransport(OFFERDATAURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"GetAuctionById", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("GetAuctionById resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("GetAuctionById property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;
    }
    /*
     * Id
CurrentPrice
CurrentUser

     * */
    //获取某竞品当前价9
    public static List<Map<String,String>> getAuctionCurrentPrice(String Id,String CurrentPrice,String CurrentUser){
    	List<Map<String,String>> resList = null;
    	String resultJson="";
    	Map<String,String> resMap = new HashMap<String,String>();
    	try{
    		SoapObject rpc = new SoapObject(NAMESPACE, "getAuctionCurrentPrice");
    		rpc.addProperty("str","{\"Id\":\""+Id+"\",\"Id\":\""+Id+"\",\"CurrentUser\":\""+CurrentUser+"\"}");
			AndroidHttpTransport ht = new AndroidHttpTransport(CURRENTPRICEURL);
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			ht.call(NAMESPACE+"getAuctionCurrentPrice", envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			Log.v("getAuctionCurrentPrice resstr is", result.toString()+"----");
			resultJson=result.getProperty(0).toString();
			Log.v("getAuctionCurrentPrice property is", result.getProperty(0).toString());
			resList = JsonUtil.decodeJson(resultJson);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return resList;//获取MasterData  买到的竞品接口 输入用户Id
    }
}
