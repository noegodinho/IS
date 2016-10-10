<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>

            <body>
                <h2>Olympic Medals</h2>
                <table border="1">


                    <xsl:for-each select="olympics/country">
                        <tr bgcolor="#9acd32">
                            <th>Position</th>
                            <th>Name</th>
                            <th>Abbreviation</th>
                            <th>Gold</th>
                            <th>Silver</th>
                            <th>Bronze</th>
                            <th>Total</th>
                        </tr>

                        <tr>
                            <td><xsl:value-of select="position" /></td>
                            <td><xsl:value-of select="name" /></td>
                            <td><xsl:value-of select="abbreviation" /></td>
                            <td><xsl:value-of select="gold" /></td>
                            <td><xsl:value-of select="silver" /></td>
                            <td><xsl:value-of select="bronze" /></td>
                            <td><xsl:value-of select="total" /></td>
                                <tr bgcolor="#0F607B">
                                    <th>Medal</th>
                                    <th>Name</th>
                                    <th colspan = "5">Modality</th>
                                </tr>
                                <xsl:for-each select="athlete">
                                    <tr>
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
