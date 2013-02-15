import java.util.Arrays;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

public class User {

	String name;
	String cn;
	String sn;
	String userpassword;
	String telephoneNumber;

	@Override
	public String toString() {
		return "User [name=" + name + ", cn=" + cn + ", sn=" + sn + ", userpassword=" + userpassword + ", telephoneNumber=" + telephoneNumber + "]";
	}

	public Attributes buildAttributes() throws Exception {
		Attributes attrs = new BasicAttributes(true);
		Attribute objclass = new BasicAttribute("objectclass");
		objclass.add("top");
		objclass.add("person");
		attrs.put(objclass);
		attrs.put(new BasicAttribute("cn", cn));
		attrs.put(new BasicAttribute("sn", sn));
		attrs.put(new BasicAttribute("userpassword", userpassword));
		attrs.put(new BasicAttribute("telephoneNumber", telephoneNumber));
		return attrs;
	}

	public static User build(Attributes attrs) throws Exception {
		User user = new User();
		user.name = "cn=" + (String) attrs.get("cn").get();
		user.cn = (String) attrs.get("cn").get();
		user.sn = (String) attrs.get("sn").get();
		// user.userpassword = (String) attrs.get("userpassword").get().toString();
		// Bad implementation :)
		user.userpassword = Arrays.toString((byte[]) attrs.get("userpassword").get());
		user.telephoneNumber = (String) attrs.get("telephoneNumber").get();
		return user;
	}
}
