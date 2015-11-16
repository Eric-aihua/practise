package com.eric.spark.sql
import java.sql.{Connection, DriverManager, ResultSet}
/**
 * Created by Eric on 2015/11/7.
 */
object MysqlClient {
  def main(args: Array[String]) {

    Class.forName("com.mysql.jdbc.Driver")
    val conn = DriverManager.getConnection("jdbc:mysql://cloud25:3306/test", "root", "root")
    try {

      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE )
      val prep=conn.prepareStatement("insert into saledata (theyear,qty,amount) values (\"2003\",2,3)")
      prep.executeUpdate()
      val rs = statement.executeQuery("select theyear,qty,amount from saledata")
      while (rs.next) {
        val theyear = rs.getString("theyear")
        val qty = rs.getString("qty")
        println("theyear = %s, qtyname = %s".format(theyear, qty))
      }
    } catch {
      case e: Exception => e.printStackTrace
    }
    conn.close
  }
}
