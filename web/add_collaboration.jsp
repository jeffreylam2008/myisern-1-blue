<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Add Collaboration</title></head>
<body>
  <table>
	<tr>
	  <td><u><b>Add New Collaboration</b></u></td>
	</tr>
	<tr>
	  <td>Name : </td>
	  <td><stripes:text name="collabName"></td>
	<tr>
  </table>
</body>
</html>