<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match = "/">
<html>
	<body>
	<table>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Roll No</th>
		</tr>
		<xsl:for-each select="students/student">
			<tr>
				<td><xsl:value-of select="firstname"/></td>
				<td><xsl:value-of select="lastname"/></td>
				<td><xsl:value-of select="rollno"/></td>
			</tr>
		</xsl:for-each>					
	</table>		
	</body>
</html>
</xsl:template>
</xsl:stylesheet>