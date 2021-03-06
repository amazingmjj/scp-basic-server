package org.zhd.basic.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.zhd.basic.entity.Address;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OriginAddressUtil {

    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file){
        StringBuffer result = new StringBuffer();
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String s = null;
                while((s = br.readLine())!=null){
                    result.append(s);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        return result.toString();
     }


    public static List<Address> findOriginAddressList(){
        String addressJsonStr = txt2String(new File(OriginAddressUtil.class.getClassLoader().getResource("area.text").getPath()));
        JSONArray allArray = JSON.parseArray(addressJsonStr);
        List<Address> returnList = new ArrayList<>();
        for (Object thisObj : allArray) {
            JSONObject prov = (JSONObject) thisObj;
            Address address = new Address();
            address.setCode((String) prov.get("value"));
            address.setName((String)prov.get("label"));
            address.setParentCode("-1");
            if (null!=prov.get("children")){
                findChildrenList((JSONArray)prov.get("children"),address.getCode(),returnList);
            }
            returnList.add(address);
        }
        return returnList;
    }

    private static void findChildrenList(JSONArray thisArray,String parentCode,List<Address> returnList){
        for (Object obj : thisArray) {
            JSONObject thisObj = (JSONObject) obj;
            Address address = new Address();
            address.setCode((String) thisObj.get("value"));
            address.setName((String)thisObj.get("label"));
            address.setParentCode(parentCode);
            if (null!=thisObj.get("children")){
                findChildrenList((JSONArray)thisObj.get("children"),address.getCode(),returnList);
            }
            returnList.add(address);
        }
    }
}
