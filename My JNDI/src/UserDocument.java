import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

public class UserDocument {

	String name;
	String documentIdentifier;
	String documentTitle;

	@Override
	public String toString() {
		return "UserDocument [name=" + name + ", documentIdentifier=" + documentIdentifier + ", documentTitle=" + documentTitle + "]";
	}

	public Attributes buildAttributes() throws Exception {
		Attributes attrs = new BasicAttributes(true);
		Attribute objclass = new BasicAttribute("objectclass");
		objclass.add("top");
		objclass.add("document");
		attrs.put(objclass);
		attrs.put(new BasicAttribute("documentIdentifier", documentIdentifier));
		attrs.put(new BasicAttribute("documentTitle", documentTitle));
		return attrs;
	}

	public static UserDocument build(Attributes attrs) throws Exception {
		UserDocument userDocument = new UserDocument();
		userDocument.documentIdentifier = (String) attrs.get("documentIdentifier").get();
		userDocument.documentTitle = (String) attrs.get("documentTitle").get();
		userDocument.name = "documentIdentifier=" + userDocument.documentIdentifier;
		return userDocument;
	}

}
