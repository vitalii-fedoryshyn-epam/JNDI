import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

public class Tenant {

	String name;
	String ou;

	@Override
	public String toString() {
		return "Tenant [name=" + name + ", id=" + ou + "]";
	}

	public Attributes buildAttributes() throws Exception {
		Attributes attrs = new BasicAttributes(true);
		Attribute objclass = new BasicAttribute("objectclass");
		objclass.add("top");
		objclass.add("organizationalUnit");
		attrs.put(objclass);
		attrs.put(new BasicAttribute("ou", ou));
		return attrs;
	}

	public static Tenant build(Attributes attrs) throws Exception {
		Tenant tenant = new Tenant();
		tenant.ou = (String) attrs.get("ou").get();
		tenant.name = "ou=" + tenant.ou;
		return tenant;
	}

}
