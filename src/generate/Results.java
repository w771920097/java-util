package generate;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Results
 * @author wangsihong@hztianque.com
 * @date 2018年10月26日 下午4:08:41 
 *
 */
public class Results {

  public static void main(String[] args) {
    
    String filePath = "C:\\Users\\n-340\\Desktop\\equipment\\";
    String author = "wangsihong@hztianque.com";
    String classPath = "com.tianque.ome.equipment";
    //zk_custom_info    deploy_sys_
    
    String tableName = "deploy_sys_equipment_information";
    String domainName = "equipmentInformation";
    Map<String, String> columnsMap = new LinkedHashMap<String, String>(){
      
      
      private static final long serialVersionUID = 904293757682197882L;

      {
        //columns    type
        put("id", "Long");
        put("name", "String");
        put("equipment_type", "String");
        put("manufacturer", "String");
        put("equipment_model", "String");
        put("supplier", "String");
        put("ip", "String");
        put("login_name", "String");
        put("login_pwd", "String");
        put("ssh", "String");
        put("ecs_alias", "String");
        put("isv", "String");
        put("tenant", "String");
        put("os", "String");
        put("remark", "String");
        put("hosts", "String");
        put("description", "String");
        
        put("mem", "Double");
        put("system_disk", "Double");
        put("data_disk", "Double");
        put("equipment_state", "Integer");
        put("cpu_core", "Integer");
        put("purchaser_id", "Long");
        put("computer_room_id", "Long");
        put("purchase_date", "Date");
        put("over_insurance_date", "Date");
        put("create_date", "Date");
        put("update_date", "Date");
      }
    };
    
    
    File file = new File(filePath);
    if (!file.exists()){
      file.mkdirs();
    }
    
    String str = null;
    XmlMapperGenerater xmlMapperGenerater = new XmlMapperGenerater(classPath, columnsMap, tableName, domainName);
    str = xmlMapperGenerater.build();
    Util.generateFiles(str, filePath + xmlMapperGenerater.getUpperCaseDomainName() + "Mapper.xml");
    
    JavaMapperGenerater javaMapperGenerater = new JavaMapperGenerater(domainName, classPath, author);
    str = javaMapperGenerater.build();
    Util.generateFiles(str, filePath + javaMapperGenerater.getUpperCaseDomainName() + "Mapper.java");
    
    ServiceImplGenerater serviceImplGenerater = new ServiceImplGenerater(classPath, domainName, author);
    str = serviceImplGenerater.build();
    Util.generateFiles(str, filePath + serviceImplGenerater.getUpperCaseDomainName() + "ServiceImpl.java");
    
    ServcieGenerater servcieGenerater = new ServcieGenerater(classPath, author, domainName);
    str = servcieGenerater.build();
    Util.generateFiles(str, filePath + servcieGenerater.getUpperCaseDomainName() + "Service.java");
    
    DomainGenerater domainGenerater = new DomainGenerater(classPath, author, domainName, columnsMap); 
    str = domainGenerater.build();
    Util.generateFiles(str, filePath + domainGenerater.getUpperCaseDomainName() + ".java");
    
    VoGenerater voGenerater = new VoGenerater(classPath, domainName, author);
    str = voGenerater.build();
    Util.generateFiles(str, filePath + voGenerater.getUpperCaseDomainName() + "VO.java");
    
    ControllerGenerater controllerGenerater = new ControllerGenerater(classPath, author, domainName);
    str = controllerGenerater.build();
    Util.generateFiles(str, filePath + controllerGenerater.getUpperCaseDomainName()+"Controller.java");
    System.out.println("成功 生成文件, 地址 > " + filePath);
    

  }
}
