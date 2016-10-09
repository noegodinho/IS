<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>

            <!-->
            ESTA PARTE DO CSS FOI ROUBADO DO TRABALHO DO TOMAS, DEPOIS TEMOS DE MUDAR ISTO EHEH
            PARA TESTAR
            <link href='https://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'/>
            <style>
                body {
                    background-color: #111;
                    font-family: 'PT Sans', sans-serif;
                }

                table {
                    margin: 0 20px;
                    border: 1px solid black;
                    border-radius: 5px;
                    -moz-border-radius: 5px;
                }

                td {
                    text-align: center;
                    color: white;
                }

                tr:nth-child(even) {
                    background-color: #0F607B;
                }

                tr:nth-child(odd) {
                    background-color: #374C53;
                }

                .title {
                    text-align: center;
                    padding: 20px;
                    color: white;
                }

                p {
                    text-align: center;
                    color: white;
                }


            </style>

            <-->

            <body>
                <h2>Olympic Medals</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Position</th>
                        <th>Name</th>
                        <th>Abbreviation</th>
                        <th>Gold</th>
                        <th>Silver</th>
                        <th>Bronze</th>
                        <th>Total</th>
                    </tr>
                    <xsl:for-each select="olympics/country">
                        <tr>
                            <td><xsl:value-of select="position" /></td>
                            <td><xsl:value-of select="name" /></td>
                            <td><xsl:value-of select="abbreviation" /></td>
                            <td><xsl:value-of select="gold" /></td>
                            <td><xsl:value-of select="silver" /></td>
                            <td><xsl:value-of select="bronze" /></td>
                            <td><xsl:value-of select="total" /></td>
                        </tr>
                    </xsl:for-each>
                </table>
                <table border="1">
                    <tr bgcolor="#0F607B">
                        <th>Medal</th>
                        <th>Name</th>
                        <th>Modality</th>
                    </tr>
                    <xsl:for-each select="olympics/country/athlete">
                        <tr>
                        <td><xsl:value-of select="medal"/></td>
                        <td><xsl:value-of select="name"/></td>
                        <td><xsl:value-of select="modality"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>

        </html>


    </xsl:template>

</xsl:stylesheet>
