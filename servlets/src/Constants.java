
public interface Constants {

	public final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    public  final String DB_URL="jdbc:mysql://localhost/TEST";

     //  Database credentials
    public final String USER = "root";
    public final String PASS = "root";

    public final String MYSQL_SELECT_CONTACT = "SELECT phone,name FROM contacts";
    public final String MYSQL_UPDATE_CONTACTS = "Insert into contacts (phone, name) VALUES(?,?)";
    public final String MYSQL_SELECT_TRANSACTION = "Select name, time, otp FROM transactions";
    public final String MYSQL_UPDATE_TRANSACTION = "Insert into transactions (name,time,otp) VALUES(?,?,?)";
    
}
