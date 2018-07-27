import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLUtil {
	private final static String DRIVER="";
	private final static String URL="";
	private final static String USER="";
	private final static String PWD="";
	
	public static void TestDonnection()
	{
		try
		{
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL, USER, PWD);
			DatabaseMetaData metadata = connection.getMetaData();
			boolean transactionSupport = metadata.supportsTransactions();
			System.out.println("Support transaction: "+transactionSupport);
			
			boolean isSupportLevel = metadata.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE);
			System.out.println("Transaction support Serializable : "+isSupportLevel);
			
			int defaultIsolation = metadata.getDefaultTransactionIsolation();
			System.out.println("default isolation: "+defaultIsolation);
			
			if(connection != null)
			{
				try
				{
					connection.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
