package wolf_j.com.github.common.hsbc;

public class SalesInvoice extends Invoice {
	public static String formatId(String oldId) {
		return oldId + "_SalesInvoice";
	}

	public static void main(String[] args) {
		Invoice invoice1 = new SalesInvoice();
		System.out.println(invoice1.formatId("1234"));

		Invoice invoice3 = new Invoice();
		System.out.println(invoice3.formatId("1234"));

		SalesInvoice invoice4 = new SalesInvoice();
		System.out.println(Invoice.formatId("1234"));

		SalesInvoice invoice5 = new SalesInvoice();
		System.out.println(invoice5.formatId("1234"));
	}
}
