# Receivables

Receivables is an Android appplication that allows its users, mainly
small businesses, to record which transactions have not been fully paid
for yet. Its two main screens show summaries of all the customers that
the business has transacted with and which transactions are still left
unpaid. It also includes screens where the user can input new customer
entries and new transactions an old customer has made. All in all, this
app aims to make recording and summarizing such things easier compared
to manual and pen-and-paper methods that are mostly used in serving
these functions.

### Main Screen
MainActivity.java is where the databases for the customers and for the
payments under their transactions, and the main recycler view are
declared and built. It also provides the methods and objects needed
when going to the Customer Summary, Add New Customer, and Unpaid Summary
Screens. This file, along with MainAdapter.java, contains the code for
the recycler view in the main screen. activity_main.xml and
main_recycler_view_row.xml on the other hand are the files that build
the layout for the main screen.

### Add Customer/Entry Screen
NewCustomer.java declares the text views and buttons found in the Add
Customer/Entry Screen. It also contains the functionality of the buttons:
one for saving the data, another for returning to the Main Activity Screen,
and the last for returning to the Customer Summary Screen.
activity_new_customer.xml provides the layout for this screen.

### Unpaid Summary Screen
UnpaidSummary.java contains the declaration for the text views and recycler
view found on the Unpaid Summary Screen. UnpaidSummaryAdapter.java helps in
building the recycler view. The files activity_unpaid_summary.xml and
unpaid_summary_row.xml contain the layout for the screen and the recycler
view respectively.

### Customer Summary Screen
CustomerSummaryActivity.java contains the code that declares its recycler
view and other views (such as the text views) found in the screen. It also
provides a method to switch to the Add Entry screen. The file
CustomerSummaryAdapter.java helps it build the recycler view for the screen;
this file also contains the method for switching to the Customer Payments
screen. Meanwhile, activity_customer_summary.xml and customer_summary_row.xml
build the layout for the customer summary screen.

### Customer Payments Screen
CustomerPayments.java includes the declaration of the text views and recycler
view found on the screen. It also contains the method for going to the Add
Payment Screen. CustomerPaymentsAdapter.java helps build the recycler view
on the screen. The layout of the screen is built through the
activity_customer_payments.xml and customer_payments_row.xml files.

### Add Payment Screen
NewPayment.java has the declaration of text views, editable texts, and buttons
on the screen. It also allows for the return to the Customer Payments Screen and
the saving of data. activity_new_payment.xml is where the layout for the screen
is found.

> [!NOTE]
> The java files are found in `app\src\main\java\com\benerson\benersonreceivables`; and the xml files are found in `app\src\main\res\layout`
