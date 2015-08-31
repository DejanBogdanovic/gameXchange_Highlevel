package ch.bbcag.gamexchange.highlevel.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import ch.bbcag.gamexchange.highlevel.data.MySqlDB;

public class User extends MySqlDB implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String lastname;
	private String userEmail;
	private String userPassword;
	private String userPasswordRepeat;
	private String userName;
	private Double balance = 0.0;
	private String dateOfBirth;
	private String domicile;
	private String postcode;
	private String country;

	public User() {

	}

	public User(String email, String password) {
		this.userEmail = email;
		this.userPassword = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getUserId() {
		return id;
	}

	public void setUserId(int id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String useremail) {
		this.userEmail = useremail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userpassword) {
		this.userPassword = userpassword;
	}

	public String getUserPasswordRepeat() {
		return userPasswordRepeat;
	}

	public void setUserPasswordRepeat(String userPasswordRepeat) {
		this.userPasswordRepeat = userPasswordRepeat;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
		return "[" + getUserId() + "," + getUserEmail() + "]";
	}
	
	public boolean getUserById(int id) {
		ResultSet resultSet;
		Boolean success = false;

		String sqlQuery;
		try {

			sqlQuery = "SELECT userId, name, lastname, userEmail, userPassword, userName, balance, dateOfBirth, domicile, postcode, country " 
			        + "  FROM user"
					+ " WHERE (userId=?)";
			String[] values = { Integer.toString(id) };

			resultSet = sqlSelect(sqlQuery, values);

			if (resultSet.next()) {
				setId(resultSet.getInt("userId"));
				setName(resultSet.getString("name"));
				setLastname(resultSet.getString("lastname"));
				setUserEmail(resultSet.getString("userEmail"));
				setUserPassword(resultSet.getString("userPassword"));
				setUserName(resultSet.getString("userName"));
				setBalance(resultSet.getDouble("balance"));
				setDateOfBirth(resultSet.getString("dateOfBirth"));
				setDomicile(resultSet.getString("domicile"));
				setPostcode(resultSet.getString("postcode"));
				setCountry(resultSet.getString("country"));
				
				success = true;
			}

		} catch (SQLException ex) {
			printSQLException(ex);
		} finally {
			sqlClose();
		}
		return success;
	}
	
	public boolean getUserByEmail(String email) {
		ResultSet resultSet;
		Boolean success = false;

		String sqlQuery;
		try {

			sqlQuery = "SELECT userId, name, lastname, userEmail, userPassword, userName, balance, dateOfBirth, domicile, postcode, country " 
			        + "  FROM user"
					+ " WHERE (userEmail=?)";
			String[] values = { email };

			resultSet = sqlSelect(sqlQuery, values);

			if (resultSet.next()) {
				setId(resultSet.getInt("userId"));
				setName(resultSet.getString("name"));
				setLastname(resultSet.getString("lastname"));
				setUserEmail(resultSet.getString("userEmail"));
				setUserPassword(resultSet.getString("userPassword"));
				setUserName(resultSet.getString("userName"));
				setBalance(resultSet.getDouble("balance"));
				setDateOfBirth(resultSet.getString("dateOfBirth"));
				setDomicile(resultSet.getString("domicile"));
				setPostcode(resultSet.getString("postcode"));
				setCountry(resultSet.getString("country"));
				
				success = true;
			}

		} catch (SQLException ex) {
			printSQLException(ex);
		} finally {
			sqlClose();
		}
		return success;
	}
	
	public boolean getUserForLogin(String email, String password) {
		ResultSet resultSet;
		Boolean success = false;

		String sqlQuery;
		try {

			sqlQuery = "SELECT userId, name, lastname, userEmail, userPassword, userName, balance, dateOfBirth, domicile, postcode, country " 
			        + "  FROM user"
					+ " WHERE userEmail=? "
					+ " AND   userPassword=?";
			String[] values = { email, password };

			resultSet = sqlSelect(sqlQuery, values);

			if (resultSet.next()) {
				setId(resultSet.getInt("userId"));
				setName(resultSet.getString("name"));
				setLastname(resultSet.getString("lastname"));
				setUserEmail(resultSet.getString("userEmail"));
				setUserPassword(resultSet.getString("userPassword"));
				setUserName(resultSet.getString("userName"));
				setBalance(resultSet.getDouble("balance"));
				setDateOfBirth(resultSet.getString("dateOfBirth"));
				setDomicile(resultSet.getString("domicile"));
				setPostcode(resultSet.getString("postcode"));
				setCountry(resultSet.getString("country"));
				
				success = true;
			}

		} catch (SQLException ex) {
			printSQLException(ex);
		} finally {
			sqlClose();
		}
		return success;
	}

	public String insertUser(User user) {
		String userId = null;
		String sqlQuery;
		try {

			sqlQuery = "INSERT INTO user " 
		             + "   SET name=?, "
		             + "       lastname=?, "
		             + "       userEmail=?, "
		             + "       userPassword=?, "
		             + "       userName=?, "
		             + "       balance=?, "
		             + "       dateOfBirth=?, "
		             + "       domicile=?, "
		             + "       postcode=?, "
					 + "       country=?";
			String[] values = { user.getName(), user.getLastname(), user.getUserEmail(), user.getUserPassword(), user.getUserName(), 
					Double.toString(user.getBalance()), user.getDateOfBirth(), user.getDomicile(), user.getPostcode(), 
					user.getCountry() };

			userId = sqlInsert(sqlQuery, values);
		} catch (Exception ex) {
			if (ex.getMessage().equals("CONSTRAINT")) {
				userId = null;
			} else if (ex.getMessage().equals("TRUNCATE")) {
				userId = "0";
			} else {
				throw new RuntimeException();
			}
		} finally {
			sqlClose();

		}
		
		return userId;
	}
	
	public boolean updateUser(User user) {
		Boolean success = false;
		String sqlQuery;
		try {
			sqlQuery = "UPDATE user " 
		             + "   SET name=?, "
		             + "       lastname=?, "
		             + "       userEmail=?, "
		             + "       userPassword=?, "
		             + "       userName=?, "
		             + "       balance=?, "
		             + "       dateOfBirth=?, "
		             + "       domicile=?, "
		             + "       postcode=?, "
					 + "       country=?" + " WHERE (userId=?)";
			String[] values = { user.getName(), user.getLastname(), user.getUserEmail(), user.getUserPassword(), user.getUserName(), 
					Double.toString(user.getBalance()), user.getDateOfBirth(), user.getDomicile(), user.getPostcode(), 
					user.getCountry(), Integer.toString(user.getId()) };

			success = sqlUpdate(sqlQuery, values);
		} catch (Exception ex) {
			if (ex.getMessage().equals("CONSTRAINT")) {
				success = false;
			} else if (ex.getMessage().equals("TRUNCATE")) {
				success = true;
			} else {
				throw new RuntimeException();
			}
		} finally {
			sqlClose();
		}
		return success;
	}

	public boolean deleteUser(User user) {
		Boolean success = false;

		String sqlQuery;
		try {
			sqlQuery = "DELETE FROM user " 
		             + " WHERE (userEmail=?)";
			String[] values = { user.getUserEmail() };
			success = sqlDelete(sqlQuery, values);
		} catch (Exception ex) {
			if (ex.getMessage().equals("CONSTRAINT")) {
				success = false;
			} else {
				throw new RuntimeException();
			}
		} finally {
			sqlClose();
		}
		return success;
	}
}
