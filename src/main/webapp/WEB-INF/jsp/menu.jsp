<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="index.html" title="...">Home</a>
<a href='<c:url value="/account"/>'>Accounts List</a> 
<a href='<c:url value="/transactions"/>'>Transactions List</a> 
<a href='<c:url value="/holderDisplay"/>'>Change user</a>
<a href='<c:url value="/transactionsCreation"><c:param name="accountId" value="${account.id}"/></c:url>'><c:out value="${account}"/>New Transaction</a>
<a href='<c:url value="/holderDisplay"></c:url>'>Change user</a>