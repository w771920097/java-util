/**   
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wangsihong@hztianque.com   
 * @date 2019年3月15日 上午11:19:20 
 * @version V1.0   
 */
package util;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import trans.Constant;
import trans.TransApi;

/** 
 * 翻译
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wangsihong@hztianque.com   
 * @date 2019年3月15日 上午11:19:20 
 *  
 */
public class 翻译 {

  public static void main(String[] args) throws UnsupportedEncodingException {

    String query = "Height 600 meters";
    
    String result = tran(query);
    System.out.println( result );

  }
  
  //翻译
  public static String tran(String query) {

    TransApi api = new TransApi(Constant.APP_ID, Constant.SECURITY_KEY);
    
    //auto 自动 ， zh 中文， en   英语
    String result = api.getTransResult(query, "auto", "zh");
    
    JSONObject jSONObject = testparse(result);
    
    JSONArray arr = (JSONArray) jSONObject.getJSONArray("trans_result");
    
    StringBuilder sb = new StringBuilder();
    for (Object object : arr) {
      JSONObject transResult = testparse(object.toString());
      
      String tran = transResult.getString("dst");
      sb.append(tran);
    }
    return sb.toString();
  }
  
  public static JSONObject testparse(String str){
    return JSONObject.parseObject(str);
}

}
