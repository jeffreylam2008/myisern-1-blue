<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>MyIsern (version 1.3)</title></head>
<body>
<stripes:form id="LoginForm" action="/MyIsern.action">
<table align="center" border="1" bordercolor="#0033CC">
   <tr>
   <td align="center" height="10" bgcolor="#0033CC"><font color="#FFFFFF">Login to MyIsern</font></td>
   </tr>
   <tr>
    <td>
   <table id="loginTable" border="0" align="center">
    <tr align="center">
      <td width="50">
        Username :
      </td>
      <td width="100">
          <stripes:text name="username"/>
      </td>
    </tr>
    <tr align="center">
      <td width="50">
        Password :
      </td>
      <td width="100">
          <stripes:password name="password"/>
      </td>
    </tr>
   </table>
   <table align="center">
    <tr align="center">
      <td>
      <stripes:submit value="Login" name="login"/>
      <stripes:reset value="Reset" name="reset"/>
    </td>
    </tr>
   </table>
  </td>
   </tr>
</table>
</stripes:form>
<p>
<table align="center" id="ErrorMessageTable">
  <tr><td><font color="red">${actionBean.errorMessage}</font></td></tr>
</table>


</body>
</html>

