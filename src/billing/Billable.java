package billing;

import catalog.Menu;
import people.Customer;

public interface Billable {
  double calculateTotal(Menu menu);
  void printReceipt(Menu menu, Customer customer);
}
