import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;

public class UserDocumentPresenter {

	private static final String[] ATTR_IDS = { "documentIdentifier", "documentTitle", "objectclass" };

	private final UserDocument userDocument;
	private final DirContext ctx;

	public UserDocumentPresenter(DirContext ctx) {
		this.ctx = ctx;
		userDocument = new UserDocument();
		userDocument.name = "documentIdentifier=document_XXX";
		userDocument.documentIdentifier = "document_XXX";
		userDocument.documentTitle = "Document Number XXX";
	}

	public void present() throws Exception {
		echo("All user documents:");
		displayAllUserDocuments();

		addUserDocument(userDocument);

		echo("All user documents(after add action):");
		displayAllUserDocuments();

		deleteUserDocument(userDocument);

		echo("All user documentss(after delete action):");
		displayAllUserDocuments();
	}

	public void displayAllUserDocuments() throws Exception {
		NamingEnumeration<NameClassPair> list = ctx.list("cn=Colleen Sullivan,ou=People");
		while (list.hasMore()) {
			NameClassPair nc = list.next();
			echo(UserDocument.build(ctx.getAttributes(nc.getName() + ",cn=Colleen Sullivan,ou=People", ATTR_IDS)).toString());
		}
	}

	public void addUserDocument(UserDocument userDocument) throws Exception {
		ctx.createSubcontext(userDocument.name + ",cn=Colleen Sullivan,ou=People", userDocument.buildAttributes());
	}

	public void deleteUserDocument(UserDocument userDocument) throws Exception {
		ctx.destroySubcontext(userDocument.name + ",cn=Colleen Sullivan,ou=People");
	}

	public void echo(String s) {
		System.out.println(s);
	}
}
