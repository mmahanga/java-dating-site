import java.util.List;

import db.entities.*;
import db.dao.*;
import db.session.Facade;
import db.session.TransactionAction;



public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Facade.doInTransaction(new TransactionAction() {

			public void execute() {
				UserDAO userDAO = UserDAOFactory.getUserDAO();
				List<User> findAll = userDAO.getAllUsers();
				System.out.println(findAll);
				System.out.println(findAll.get(0).getPicture());
				System.out.println(findAll.get(0).getInterests());
			}
			
		});
		
		Facade.doInTransaction(new TransactionAction() {

			public void execute() {
				UserDAO userDAO = UserDAOFactory.getUserDAO();
				User usr = userDAO.getUserByName("josh");
				System.out.println(usr.getFullName());
				System.out.println(usr.getPassword());
				System.out.println(usr.getPicture());
				System.out.println(usr.getInterests());
			}
			
		});
		
		
		System.out.println("DONE!");
	}

}
