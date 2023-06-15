<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Details</title>
</head>
<body>
    <a href='index.jsp'/>Return to Order Look up Page</a>
<%
// Use a try-catch block to catch any errors
try {
    // Create a connection to the database
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels",
            "root", "admin");

    // Create a new statement using the database connection
    Statement statement = connection.createStatement();

    // Execute a SELECT query to retrieve a resultset containing the order details
    ResultSet resultset = statement.executeQuery("SELECT orders.orderDate, orders.shippedDate, orderDetails.productCode, orderDetails.quantityOrdered, orderDetails.priceEach FROM Orders, OrderDetails WHERE orders.ordernumber = orderdetails.ordernumber AND orders.ordernumber = '" + request.getParameter("OrderNumber") + "';");

    // Loop through the records in the result set - in case there are multiple products in the order
    while (resultset.next()) {
%>

<table>
    
    <tr>
        <td>Order Date: </td>
        <td><% out.print(resultset.getString("orderdate")); %></td>
    </tr>
    <tr>
        <td>Shipped Date: </td>
        <td><% out.print(resultset.getString("shippeddate")); %></td>
    </tr>
    <tr>
        <td>Product Code: </td>
        <td><% out.print(resultset.getString("productcode")); %></td>
    </tr>
    <tr>
        <td>Quantity Ordered: </td>
        <td><% out.print(resultset.getString("quantityordered")); %></td>
    </tr>
    <tr>
        <td>Price Each: </td>
        <td><% out.print(resultset.getString("priceeach")); %>
    </tr>
</table>
<%
    }//end while

    // Close the connection to the database
    connection.close();

} catch (Exception e) {
    // Print the error message for any exception that is caught
    e.printStackTrace();
}//end try catch
%>
</body>
</html>
