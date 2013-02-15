import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Main {

	public static void main(String[] args) throws Exception {
		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389/o=myldap");
		env.put(Context.SECURITY_AUTHENTICATION, "none");

		DirContext ctx = new InitialDirContext(env);

		new TenantPresenter(ctx).present();

		new UserPresenter(ctx).present();

		new UserDocumentPresenter(ctx).present();

		ctx.close();
	}

}
