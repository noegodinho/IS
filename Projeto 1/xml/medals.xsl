<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>

            <head>
                <script src="code.js">//</script>
            </head>


            <body>
                <h2>Olympic Medals</h2>

                <span onClick="toggle();">Show Athletes</span><br /><br />
                <table border="1">
                    <tr bgcolor="#9acd32" class="athletes" style="display:table-row;">
                        <th>Position</th>
                        <th>Name</th>
                        <th>Abbreviation</th>
                        <th>Gold</th>
                        <th>Silver</th>
                        <th>Bronze</th>
                        <th>Total</th>
                    </tr>
                    <xsl:for-each select="olympics/country">
                        <tr bgcolor="#9acd32" class="athletes" style="display:none;">
                            <th>Position</th>
                            <th>Name</th>
                            <th>Abbreviation</th>
                            <th>Gold</th>
                            <th>Silver</th>
                            <th>Bronze</th>
                            <th>Total</th>
                        </tr>
                        <tr bgcolor="#BDBDBD">
                            <td><xsl:value-of select="position" /></td>
                            <td><xsl:value-of select="name" /></td>
                            <td><xsl:value-of select="abbreviation" /></td>
                            <td><xsl:value-of select="gold" /></td>
                            <td><xsl:value-of select="silver" /></td>
                            <td><xsl:value-of select="bronze" /></td>
                            <td><xsl:value-of select="total" /></td>
                                <tr bgcolor="#0F607B" class="athletes" style="display:none;">
                                    <th>Medal</th>
                                    <th>Name</th>
                                    <th colspan = "5">Modality</th>
                                </tr>
                                <xsl:for-each select="athlete">
                                    <tr bgcolor="#E6E6E6" class="athletes" style="display:none;">
                                        <td><xsl:value-of select="medal"/></td>
                                        <td><xsl:value-of select="name"/></td>
                                        <td colspan = "5"><xsl:value-of select="modality"/></td>
                                    </tr>
                                </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>

            </body>

        </html>


    </xsl:template>

</xsl:stylesheet>
