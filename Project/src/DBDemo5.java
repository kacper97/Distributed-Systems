import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBDemo5 extends JFrame implements ActionListener {
    private JLabel labelHeading, labelSSn, labelDOB, labelName, labelAddress, labelSalary, labelGender;
    private JButton buttonPrev, buttonNext, buttonDelete, buttonAdd, buttonUpdate, buttonClear;
    private JTextField txtSSn, txtDOB, txtName, txtAddress, txtSalary, txtGender;
    private JPanel panelNorth, panelSouth, panelCenter, panelWest;
    private ResultSet rs = null;

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "test";
	
	/** The name of the table we are testing with */
	private final String tableName = "jdbc_test";
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		// Create ResultSet 
		try {
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM jdbc_test");
			rs = s.getResultSet ();
			System.out.println("Created a result set");
			rs.next();
			System.out.println(rs.getString("id"));

	    } catch (SQLException e) {
			System.out.println("ERROR: Could not access the table");
			e.printStackTrace();
			return;
		}
	
	}
	
	// what happens after you click the buttons
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == buttonPrev)
	{
		System.out.println("You pressed PREVIOUS");
           // Insert resultSet.previous() code here
			try {
				rs.previous();
				// Update Text fields
			   	txtSSn.setText(rs.getString("id"));
			   	txtDOB.setText(rs.getString("DOB"));
			   	txtName.setText(rs.getString("Name"));
			   	txtAddress.setText(rs.getString("Address"));
			   	txtSalary.setText(rs.getString("Salary"));
			   	txtGender.setText(rs.getString("Gender"));
			   	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}
    	if (e.getSource() == buttonNext)
	{
		System.out.println("You pressed NEXT");
           // Insert resultSet.next() code here
			try {
				rs.next();
				// Update Text fields
				txtSSn.setText(rs.getString("id"));
				txtDOB.setText(rs.getString("DOB"));
				txtName.setText(rs.getString("Name"));
			   	txtAddress.setText(rs.getString("Address"));
			   	txtSalary.setText(rs.getString("Salary"));
			   	txtGender.setText(rs.getString("Gender"));
			   	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
    	if (e.getSource() == buttonDelete)
	{
		System.out.println("You pressed DELETE");
           // Insert delete current record code here
	}
    	
    	
    }
    
	 public void setUpGUI() {

		// 3. Set up GUI

			// Labels
		    		labelHeading = new JLabel("EMPLOYEE DETAILS", JLabel.CENTER);
		        	labelSSn = new JLabel("SSn", JLabel.CENTER);
		        	labelDOB = new JLabel("DOB", JLabel.CENTER);
		        	labelName = new JLabel("Name",JLabel.CENTER);
		        	labelAddress = new JLabel("Address",JLabel.CENTER);
		        	labelSalary = new JLabel("Salary",JLabel.CENTER);
		        	labelGender = new JLabel("Gender",JLabel.CENTER);
		        	
			// TextFields
		        	try {
						txtSSn = new JTextField(rs.getString("id"));
			        	txtDOB = new JTextField(rs.getString("DOB"));
			        	txtName = new JTextField(rs.getString("Name"));
			        	txtAddress = new JTextField(rs.getString("Address"));
			        	txtSalary = new JTextField(rs.getString("Salary"));
			        	txtGender = new JTextField(rs.getString("Gender"));
			        	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      
			// Buttons
		        	buttonPrev = new JButton("Previous");
		        	buttonNext = new JButton("Next");
		        	buttonDelete = new JButton("Delete");  
		        	buttonAdd = new JButton("Add");
		        	buttonUpdate = new JButton("Update");
		        	buttonClear = new JButton("Clear");
		        	
		        	
			// Panels       
		        	panelNorth = new JPanel();
		        	panelNorth.add(labelHeading);

			 		panelSouth = new JPanel();
					panelSouth.add(buttonAdd);
					panelSouth.add(buttonDelete);
					panelSouth.add(buttonUpdate);
		    	
		    		panelWest = new JPanel();
		    		panelWest.add(labelSSn);
		    		panelWest.add(txtSSn);
		    		panelWest.add(labelDOB);
		    		panelWest.add(txtDOB);   	
		    		panelWest.add(labelName);
		    		panelWest.add(txtName);
		    		panelWest.add(labelAddress);
		    		panelWest.add(txtAddress);
		    		panelWest.add(labelSalary);
		    		panelWest.add(txtSalary);
		    		panelWest.add(labelGender);
		    		panelWest.add(txtGender);

			// Add Panels
		        	add(panelNorth, BorderLayout.NORTH);
		        	add(panelSouth, BorderLayout.SOUTH);
		        	add(panelWest, BorderLayout.WEST);
		        	
			// Add action listeners
		        	buttonPrev.addActionListener(this);
		        	buttonNext.addActionListener(this);
		        	buttonDelete.addActionListener(this);
		        	buttonAdd.addActionListener(this);
		        	buttonUpdate.addActionListener(this);
		        	buttonClear.addActionListener(this);
		        	
		    }
	 
	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		DBDemo5 app = new DBDemo5();
		app.run();
		app.setUpGUI();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setPreferredSize(new Dimension(400, 300));
        app.pack();
        app.setVisible(true);
	}
}