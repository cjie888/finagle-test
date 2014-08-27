package cn.cjie.sql

import java.sql.{ResultSet, PreparedStatement, Connection, DriverManager}

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-27
 * Time: 下午8:53
 * To change this template use File | Settings | File Templates.
 */
object JDBCDemo extends App{
  Class.forName("org.apache.hive.jdbc.HiveDriver")
  val conn:Connection = DriverManager.getConnection("jdbc:hive2://server1:10000/default","cc","")
  val pstat:PreparedStatement = conn.prepareStatement("select wid,aid,times from word where times is not null limit 10")
  val rs:ResultSet = pstat.executeQuery()
  while(rs.next()) {
    println("wid:" + rs.getInt("wid") + ",aid:" + rs.getInt("aid") + ",times:" + rs.getInt("times"))
  }
  rs.close()
  pstat.close()
  conn.close()

}
