<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>MyIsern (version 1.3)</title></head>
<body>
<table align="center">
	<tr>
		<stripes:form id="TypeForm" action="/MyIsern.action">
		<td width="150"><u><b>Add New Entry</b></u></td>
		<td>
		  <stripes:select size="1" name="type">
            <stripes:option value="Collaboration">Collaboration</stripes:option>
            <stripes:option value="Organization">Organization</stripes:option>
            <stripes:option value="Researcher">Researcher</stripes:option>
          </stripes:select>         
		</td>
		<td><stripes:submit value="GO!" name="addType"/></td>
		</stripes:form>
	</tr>
	<tr>
	  <stripes:form action="/MyIsern.action">
		<td width="150"><u><b>Search</b></u></td>
		<td><stripes:text name="collabSearchField"/></td>
		<td><stripes:submit value="Search" name="findCollaboration"/></td>
	  </stripes:form>
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
<p>
<table align="center" id="ErrorMessageTable">
  <tr><td><font color="red">${actionBean.errorMessage}</font></td></tr>
</table>
</body>
</html>