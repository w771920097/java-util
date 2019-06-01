package util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/** 
 * ObjectToMap 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wangsihong@hztianque.com   
 * @date 2019年3月19日 下午5:18:40 
 *  
 */
public class ObjectToMap {

  public static void main(String[] args) {

    
    ObjectToMap objectToMap = new ObjectToMap();

    Metric metric = objectToMap.new Metric();
    
    
    metric.setMetric("metric");
    metric.setValue("value");
    
    Map<String, Object> tags = new HashMap<String, Object>();

    tags.put("key1", "value1");
    tags.put("key2", "value2");
    tags.put("key3", "value3");
    
    
    metric.setTags(null);
    
    
    try {
      Map<String, Object> metricMap = objectToMap.objectToMap(metric);
      tags.putAll(metricMap);
      
      System.out.println(tags);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public static Map<String, Object> objectToMap(Object obj) throws Exception { 
    if(obj == null) 
    return null; 
    
    Map<String, Object> map = new HashMap<String, Object>(); 

    BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass()); 
    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors(); 
    for (PropertyDescriptor property : propertyDescriptors) { 
    String key = property.getName(); 
    if (key.compareToIgnoreCase("class") == 0) { 
    continue; 
    } 
    Method getter = property.getReadMethod(); 
    Object value = getter!=null ? getter.invoke(obj) : null; 
    if(value == null)
      continue;
    
    map.put(key, value); 
    } 

    return map; 
    } 
  
  public class Metric{
    
    String metric;
    
    String value;
    
    Map<String, Object> tags;
    
    public String getMetric() {
      return metric;
    }

    public void setMetric(String metric) {
      this.metric = metric;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public Map<String, Object> getTags() {
      return tags;
    }

    public void setTags(Map<String, Object> tags) {
      this.tags = tags;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Metric [metric=");
      builder.append(metric);
      builder.append(", value=");
      builder.append(value);
      builder.append(", tags=");
      builder.append(tags);
      builder.append("]");
      return builder.toString();
    }
    
  }

}
