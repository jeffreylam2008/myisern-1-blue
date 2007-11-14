<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>MyIsern (version 1.3)</title></head>
<body>

<table align="center">
	<tr align="center">
		<td><u><b>Add New Entry</b></u></td>
	</tr>
	<tr>
		<td>
		<stripes:form id="TypeForm" action="/MyIsern.action">
		  <stripes:select size="1" name="type">
            <stripes:option value="1">Collaboration</stripes:option>
            <stripes:option value="2">Organization</stripes:option>
            <stripes:option value="3">Researcher</stripes:option>
          </stripes:select>
          <stripes:submit value="GO!" name="addType"/>
		</stripes:form>	
		</td>
	<tr>
</table>
<hr align="center" width="300">
<table align="center">
  <tr>
   <td>
    <table align="center">
      <tr>
        <stripes:form action="/MyIsern.action">
           <td bgcolor="#666699" width="40"><stripes:submit name="collabLink" value="Collaboration"/></td>
        </stripes:form> 
        <stripes:form action="/MyIsern.action">
           <td bgcolor="#666699" width="40"><stripes:submit name="orgLink" value="Organization"/></td>
        </stripes:form> 
        <stripes:form action="/MyIsern.action">
           <td bgcolor="#666699" width="40"><stripes:submit name="resLink" value="Researcher"/></td>
        </stripes:form>
      </tr>
    </table>
    <table align="center" border="1">
      <tr>
        <td bgcolor="#99CCFF">Collaboration Name</td>  
      </tr> 
      <c:forEach var="element" items="${actionBean.collaborations}">
     <tr><td>${element}</td></tr>
   </c:forEach>
      </table>
   </td>
  </tr>
</table>
</body>
</html>