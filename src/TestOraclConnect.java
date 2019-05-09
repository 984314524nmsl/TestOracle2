import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TestOraclConnect {
    public static void main(String[] args) throws IOException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
         /*   Properties properties=new Properties();
            try {
               // InputStream  in=new FileInputStream(TestOraclConnect.class.getClassLoader().getResourceAsStream("test.properties"));
                try {
                    properties.load(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String start = properties.getProperty("start");
            String end = properties.getProperty("end");*/
            InputStream in = TestOraclConnect.class.getResourceAsStream("/test.properties");
            Properties properties = new Properties();
            properties.load(in);
            String bucket=properties.getProperty("bucket");
            String start = properties.getProperty("start");
            String end=properties.getProperty("end");
            int start1=Integer.parseInt(start);
            int ead1=Integer.parseInt(end);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String dbURL = "jdbc:oracle:thin:@192.168.20.201:1521:orcl";


            for(int i=start1;i<ead1;i++){
                conn = DriverManager.getConnection(dbURL, "CARDC", "CARDC");
/*

                String sql2=" insert into TI_LES_INNER_SHEET (TI_LES_INNER_SHEET_ID, PLANT_NO, WORKSHOP_NO, LINE_NO, ORGANIZE, PART_NO, PART_NAME_C, SUPPL_NO, REQ_BOXNUM, REQ_QTY, RECEIVE_PACKAGE_NUM, PO_PACKAGE_NO, SHEET_NO, DELIVERY_SEND, DELIVERY_SEND_NAMEC, DELIVERY_REC, DELIVERY_REC_NAMEC, SHEET_CREATE_TIME, LAST_REC_REQURIE_TIME, IS_EMERGENT, NOTE1, NOTE2, NOTE3, NOTE4, NOTE5, NOTE6, NOTE7, NOTE8, NOTE9, CREATE_TIME, HOLD_FLAG)" +
                        "values ("+i+", 'CA5', 'Z2', 'P12', 'CA5', '5010100-U07', '离合软管总成(分泵-总泵)', 'S33671A', 1.000, 100.000, 100.000, 'RD-H', 'M2017040900000012', 'RDC', '民生物流RDC', 'AS002', '总装二车间', systimestamp, systimestamp, 0, null, null, null, null, null, null, null, null, null, systimestamp, 'N');";
*/

                String sql="insert into TI_LES_STOCK_INBOUND (TI_LES_STOCK_INBOUND_ID, PLANT_NO, WORKSHOP_NO, LINE_NO, ORGANIZE, PART_NO, PART_NAME_C, PACKAGE_NO, SUPPL_NO, DELIVERY_SEND, DELIVERY_SEND_NAMEC, DELIVERY_REC, DELIVERY_REC_NAMEC, WAREHOUSE_NO, DLOC_NO, STOCK_PART_LOT_NO, INB_SERIAL_NO, INBOUND_QTY, INBOUND_TIME, NOTE2, NOTE3, NOTE4, NOTE5, CREATE_TIME, HOLD_FLAG, TRAY_CARD_LOT_NO) " +
                        "values ("+i+", 'CA5', 'Z1', 'P12', 'CA5', '5010100-U07', '转向支撑总成', 'LJ-9', 'S31500', 'S31500', '重庆驰骋轻型汽车部件股份有限公司', 'RDC', '民生物流RDC', 'RA', 'RA-D', 'LOT190117182233581', 'S201612290"+i+"$5010100-U07', 12.000, systimestamp, null, null, null, null, systimestamp , 'N', '"+bucket+"-2-1-1')";
                String sql2="insert into TI_LES_STOCK_INBOUND (TI_LES_STOCK_INBOUND_ID, PLANT_NO, WORKSHOP_NO, LINE_NO, ORGANIZE, PART_NO, PART_NAME_C, PACKAGE_NO, SUPPL_NO, DELIVERY_SEND, DELIVERY_SEND_NAMEC, DELIVERY_REC, DELIVERY_REC_NAMEC, WAREHOUSE_NO, DLOC_NO, STOCK_PART_LOT_NO, INB_SERIAL_NO, INBOUND_QTY, INBOUND_TIME, NOTE2, NOTE3, NOTE4, NOTE5, CREATE_TIME, HOLD_FLAG, TRAY_CARD_LOT_NO) " +
                        "values ("+i+", 'CA5', 'Z1', 'P12', 'CA5', '880999-U07', '转向支撑总成', 'LJ-9', 'S31500', 'S31500', '重庆驰骋轻型汽车部件股份有限公司', 'RDC', '民生物流RDC', 'RA', 'RA-D', 'LOT190117182233581', 'wangS20190411"+i+"$880999-U07', 12.000, systimestamp, null, null, null, null, systimestamp , 'N', '"+bucket+"-2-1-1')";

                PreparedStatement preparedStatement = conn.prepareStatement(sql2);
                int i1 = preparedStatement.executeUpdate();
                if(i1==1){
                    System.out.println("success 物料卡号："+"S20190411"+i+"$880999-U07");
                   // System.out.println("success 物料卡号："+"S201612290"+i+"$5010100-U07");
                }
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            }
            //String sql="insert into TI_LES_STOCK_INBOUND (TI_LES_STOCK_INBOUND_ID, PLANT_NO, WORKSHOP_NO, LINE_NO, ORGANIZE, PART_NO, PART_NAME_C, PACKAGE_NO, SUPPL_NO, DELIVERY_SEND, DELIVERY_SEND_NAMEC, DELIVERY_REC, DELIVERY_REC_NAMEC, WAREHOUSE_NO, DLOC_NO, STOCK_PART_LOT_NO, INB_SERIAL_NO, INBOUND_QTY, INBOUND_TIME, NOTE2, NOTE3, NOTE4, NOTE5, CREATE_TIME, HOLD_FLAG, TRAY_CARD_LOT_NO) values (342, 'CA5', 'Z1', 'P06', 'CA5', '5010100-U07', '转向支撑总成', 'LJ-9', 'S31500', 'S31500', '重庆驰骋轻型汽车部件股份有限公司', 'RDC', '民生物流RDC', 'RA', 'RA-D', 'LOT190117182233581', 'S20161229056378$5010100-U07', 12.000, systimestamp, null, null, null, null, systimestamp , 'N', '123456')";
           // PreparedStatement preparedStatement = conn.prepareStatement(sql);
           // int i = preparedStatement.executeUpdate();
          //  System.out.println(i);
          /*  ResultSet rs1 = rs;
            rs1 = preparedStatement.executeQuery();
            if(rs1.next()){
                String lineNo = rs1.getString("lineNo");
                System.out.println(lineNo);
            }*/
          /*  Array lineNo =
            System.out.println("连接成功"+rs1);*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}