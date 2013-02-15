import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;

public class UserPresenter {

	private static final String[] ATTR_IDS = { "cn", "sn", "userpassword", "telephoneNumber", "objectclass" };

	private final User user;
	private final DirContext ctx;

	public UserPresenter(DirContext ctx) {
		this.ctx = ctx;
		user = new User();
		user.name = "cn=VaSja JasJa";
		user.cn = "VaSja JasJa";
		user.sn = "JasJa";
		user.userpassword = "pass111";
		user.telephoneNumber = "123321";
	}

	public void present() throws Exception {
		echo("All users:");
		displayAllUsers();

		addUser(user);

		echo("All users(after add action):");
		displayAllUsers();

		deleteUser(user);

		echo("All users(after delete action):");
		displayAllUsers();
	}

	public void displayAllUsers() throws Exception {
		NamingEnumeration<NameClassPair> list = ctx.list("ou=People");
		while (list.hasMore()) {
			NameClassPair nc = list.next();
			echo(User.build(ctx.getAttributes(nc.getName() + ",ou=People", ATTR_IDS)).toString());
		}
	}

	public void addUser(User user) throws Exception {
		ctx.createSubcontext(user.name + ",ou=People", user.buildAttributes());
	}

	public void deleteUser(User user) throws Exception {
		ctx.destroySubcontext(user.name + ",ou=People");
	}

	public void echo(String s) {
		System.out.println(s);
	}

}
