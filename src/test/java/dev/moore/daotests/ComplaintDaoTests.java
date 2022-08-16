package dev.moore.daotests;

import dev.moore.daos.ComplaintDAO;
import dev.moore.daos.ComplaintDaoPostgres;
import dev.moore.entities.Complaint;
import dev.moore.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintDaoTests {

    static ComplaintDAO complaintDAO = new ComplaintDaoPostgres();

//    @BeforeAll
//    static void setup(){
//        try(Connection conn = ConnectionUtil.createConnection()){
//            String sql = "drop table if exists complaint;\n" +
//                    "drop table if exists meeting;\n" +
//                    "drop table if exists app_user;\n" +
//                    "\n" +
//                    "create table app_user(\n" +
//                    "\tapp_user_id serial primary key,\n" +
//                    "\tusername varchar(40) not null,\n" +
//                    "\tpassword varchar(40) not null,\n" +
//                    "\tis_council_member boolean default false\n" +
//                    ");\n" +
//                    "\n" +
//                    "create table meeting(\t\n" +
//                    "\tmeeting_id serial primary key,\n" +
//                    "\tdescription varchar(400) not null,\n" +
//                    "\ttime int \n" +
//                    ");\n" +
//                    "\n" +
//                    "create table complaint(\n" +
//                    "\tcomplaint_id serial primary key,\n" +
//                    "\tdescription varchar(400) not null,\n" +
//                    "\tstatus varchar(15) not null,\n" +
//                    "\tmeeting_id int references meeting(meeting_id) default -1\n" +
//                    ");\n" +
//                    "\n" +
//                    "insert into meeting values (-1, 'NO MEETING ASSIGNED',0);";
//
//            Statement statement = conn.createStatement();
//            statement.execute(sql);
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }

    @Test
    @Order(1)
    void create_complaint_test(){
        Complaint complaint = new Complaint("Xanathar blew up a local orphanage (test complaint, set ignored)");
        Complaint savedComplaint = complaintDAO.createComplaint(complaint);
        Assertions.assertNotEquals(0,savedComplaint.getComplaintId());
    }
}
