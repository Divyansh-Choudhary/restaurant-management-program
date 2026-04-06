/**
 * Interface for billing operations
 * Includes methods for calculating total cost and printing receipts.
 */

package billing;

interface Billable {

  /**
   * Calculates the total of the given menu/order.
   *
   * @params menu - the menu of order containing the items
   * @return the total amount of all items
   */
  double calculateTotal(Menu menu);

  /*
   * Prints the receipt of the menu
   */
  void printReceipt();
}
