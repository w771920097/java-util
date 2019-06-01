package util;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
//import org.apache.activemq.broker.jmx.BrokerViewMBean;
//import org.apache.activemq.broker.jmx.QueueViewMBean;
//import org.apache.activemq.broker.jmx.TopicViewMBean;

/**
 * 鐩戞帶ActiveMQ鐨勫悇绉嶄俊鎭疊roker,Connection,Queue,Topic鐨勬暟閲忓拰鍘嬫爤鍜屽嚭鏍�
 * 
 * @author longgangbai
 * 
 */
public class ActiveMQMonitor {

  public static void main(String[] args) throws Exception {
//    JMXServiceURL url =
//        new JMXServiceURL("service:jmx:rmi:///jndi/rmi://192.168.110.246:51515/jmxrmi");
//    JMXConnector connector = JMXConnectorFactory.connect(url, null);
//    connector.connect();
//    MBeanServerConnection connection = connector.getMBeanServerConnection();
//    ObjectName name = new ObjectName("org.apache.activemq:brokerName=localhost,type=Broker");
//    BrokerViewMBean mbean = MBeanServerInvocationHandler.newProxyInstance(connection, name,
//        BrokerViewMBean.class, true);
//    System.out.println("**************");
//    System.out.println("getBrokerId:" + mbean.getBrokerId());
//    System.out.println("getBrokerName:" + mbean.getBrokerName());
//    System.out.println("getTotalMessageCount:" + mbean.getTotalMessageCount());
//    System.out.println("getTotalEnqueueCount:" + mbean.getTotalEnqueueCount());
//    System.out.println("getTotalDequeueCount:" + mbean.getTotalDequeueCount());
//    System.out.println("**************");
//    System.out.println("==============");
//    ObjectName[] queueNames = mbean.getQueues();
//    System.out.println("getQueues count:" + queueNames.length);
//    for (ObjectName queueName : queueNames) {
//
//      QueueViewMBean queueMbean = MBeanServerInvocationHandler.newProxyInstance(connection,
//          queueName, QueueViewMBean.class, true);
//      System.out.println("------------");
//      System.out.println("queueName:" + queueMbean.getName());
//      System.out.println("queuesize:" + queueMbean.getQueueSize());
//      System.out.println("getEnqueueCount:" + queueMbean.getEnqueueCount());
//      System.out.println("getDequeueCount:" + queueMbean.getDequeueCount());
//    }
//    System.out.println("==============");
//    System.out.println("++++++++++++++");
//    ObjectName[] topicNames = mbean.getTopics();
//    System.out.println("topic count:" + topicNames.length);
//    for (ObjectName topicName : topicNames) {
//      TopicViewMBean topicMBean = MBeanServerInvocationHandler.newProxyInstance(connection,
//          topicName, TopicViewMBean.class, true);
//      System.out.println("------------");
//      System.out.println("topivName:" + topicMBean.getName());
//      System.out.println("topivSize:" + topicMBean.getQueueSize());
//      System.out.println("getEnqueueCount:" + topicMBean.getEnqueueCount());
//      System.out.println("getDequeueCount:" + topicMBean.getDequeueCount());
//    }
//    System.out.println("++++++++++++++");

  }

}
