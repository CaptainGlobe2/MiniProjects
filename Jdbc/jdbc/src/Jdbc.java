import java.sql.*;
import java.util.Arrays;

public class Jdbc {
    public static void main(String[] args) throws Exception {
    //readRecord();
    //insertRecord();
         //insertRecWithPreparedStatement();
        //delete();
        //update();
        //callableStatement();
        //callableStatementWithPara();
        //callableStatementWithParawithOutput();
        //commitDemo();
        //bathcDemo();
        bathcUsingCommitDemo();

    }
    public static void readRecord() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";
        String query = "select * from employee";
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            System.out.println("id is"+rs.getInt(1));
            System.out.println("Name is "+rs.getString(2));
            System.out.println("salary is "+rs.getInt(3));
            System.out.println();
        }
        con.close();
    }

    public static void insertRecord() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";
        String query = "insert into employee values(4,'abd',10001)";
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int row = st.executeUpdate(query);
        System.out.println("Number of rows affected "+row);
        con.close();
    }

    public static void insertRecWithVar() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";
        int id = 5;
        String name = "dfc";
        int salary = 5432;
        String query = "insert into employee values("+id+","+name+","+salary+")";
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int row = st.executeUpdate(query);
        System.out.println("Number of rows affected "+row);
        con.close();
    }

    public static void insertRecWithPreparedStatement() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";
        int id = 7;
        String name = "dfc";
        int salary = 5432;
        String query = "insert into employee values(?,?,?)";
        Connection con = DriverManager.getConnection(url,userName,passWord);
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        pst.setString(2,name);
        pst.setInt(3,salary);
        int rows = pst.executeUpdate();
        System.out.println("Number of rows affected "+rows);
        con.close();
    }

    public static void delete() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";
        int id =4;

        String query = "delete from employee where emp_id ="+id;
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int row = st.executeUpdate(query);
        System.out.println("Number of rows affected "+row);
        con.close();
    }


    public static void update() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";


        String query = "update employee set salary = 15000 where emp_id=1";
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int row = st.executeUpdate(query);
        System.out.println("Number of rows affected "+row);
        con.close();
    }
//types of statement
    //normal , prepared ???,callable

    public static void  callableStatement() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";

        Connection con = DriverManager.getConnection(url,userName,passWord);
        CallableStatement cst = con.prepareCall("{call GetEmp()}");
        ResultSet rs = cst.executeQuery();

        while(rs.next()){
            System.out.println("id is"+rs.getInt(1));
            System.out.println("Name is "+rs.getString(2));
            System.out.println("salary is "+rs.getInt(3));
            System.out.println();
        }

        con.close();
    }

    //calling stored procedure with input parameter

    public static void  callableStatementWithPara() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";

        int id = 3;

        Connection con = DriverManager.getConnection(url,userName,passWord);
        CallableStatement cst = con.prepareCall("{call GetEmpId(?)}");
        cst.setInt(1,id);
        ResultSet rs = cst.executeQuery();

        while(rs.next()){
            System.out.println("id is"+rs.getInt(1));
            System.out.println("Name is "+rs.getString(2));
            System.out.println("salary is "+rs.getInt(3));
            System.out.println();
        }

        con.close();
    }


    public static void  callableStatementWithParawithOutput() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";

        int id = 3;

        Connection con = DriverManager.getConnection(url,userName,passWord);
        CallableStatement cst = con.prepareCall("{call GetEmpNameById(?,?)}");
        cst.setInt(1,id);
        cst.registerOutParameter(2,Types.VARCHAR);
        cst.executeUpdate();

        System.out.println(cst.getString(2));

        con.close();
    }

    //commit vs autocommit

    public static void  commitDemo() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";

       String query1="update employee set salary = 40000 where emp_id=1";
       String query2="update employee set salary =400001 where emp_id=2";

        Connection con = DriverManager.getConnection(url,userName,passWord);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        int rows1  = st.executeUpdate(query1);
        System.out.println("rows affected"+rows1);
        int rows2 = st.executeUpdate(query2);
        System.out.println("rows affected "+rows2);

        if(rows1>0&&rows2>0){//this will commit only the both are working properly
            con.commit();
        }

        con.close();
    }

    public static void  bathcDemo() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";

        String query1="update employee set salary = 40000 where emp_id=1";
        String query2="update employee set salary =40000 where emp_id=2";
        String query3="update employee set salary =40000 where emp_id=3";
        String query4="update employee set salary =40000 where emp_id=7";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        st.addBatch(query4);

        int[] result= st.executeBatch();
        System.out.println(Arrays.toString(result));
        con.close();
    }


    public static void  bathcUsingCommitDemo() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "rootmysql";

        String query1="update employee set salary = 50000 where emp_id=1";
        String query2="update employee set salary =50000 where emp_id=2";
        String query3="update employee set salary =40000 where emp_id=3";
        String query4="update employee set salary =40000 where emp_id=7";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        st.addBatch(query4);

        int[] result= st.executeBatch();
        for(int i : result){
            if(i>0){
                continue;
            }
            else {
                con.rollback();
            }
        }
        con.commit();
        con.close();
    }

}