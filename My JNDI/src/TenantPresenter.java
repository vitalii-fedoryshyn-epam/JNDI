import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;

public class TenantPresenter {

	private static final String[] ATTR_IDS = { "ou", "objectclass" };

	private final Tenant tenant;
	private final DirContext ctx;

	public TenantPresenter(DirContext ctx) {
		this.ctx = ctx;
		tenant = new Tenant();
		tenant.ou = "aaa";
		tenant.name = "ou=aaa";
	}

	public void present() throws Exception {
		echo("----- Start tenants presenting -----");
		echo("All tenants:");
		displayAllTenants();

		addTenant(tenant);

		echo("All tenants(after add action):");
		displayAllTenants();

		deleteTenant(tenant);

		echo("All tenants(after delete action):");
		displayAllTenants();
		echo("----- End tenants presenting -----");
	}

	public void displayAllTenants() throws Exception {
		NamingEnumeration<NameClassPair> ncs = ctx.list("");
		while (ncs.hasMore()) {
			NameClassPair nc = ncs.next();
			echo(Tenant.build(ctx.getAttributes(nc.getName(), ATTR_IDS)).toString());
		}
		// // alternative implementation
		// NamingEnumeration<Binding> bindings = ctx.listBindings("");
		// while (bindings.hasMore()) {
		// Binding bd = bindings.next();
		// echo(Tenant.build(((javax.naming.directory.DirContext) bd.getObject()).getAttributes("", ATTR_IDS)).toString());
		// }
	}

	public void addTenant(Tenant tenant) throws Exception {
		ctx.createSubcontext(tenant.name, tenant.buildAttributes()).close();
	}

	public void deleteTenant(Tenant tenant) throws Exception {
		ctx.destroySubcontext(tenant.name);
	}

	public void echo(String s) {
		System.out.println(s);
	}

}
