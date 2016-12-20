<?xml version="1.0" ?>

<!-- Nome File: App01 con foglio XSL.xsl -->

<xsl:stylesheet
 version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">

		<HTML>

				<HEAD>

						<TITLE>Poeti Ottocenteschi</TITLE>
				</HEAD>

				<BODY>
				<H2>Elenco dei poeti</H2>

					<SPAN STYLE="font-style:italic">Nome: </SPAN>
					<xsl:value-of select="Poeta/Persona/Nome" /> <BR />

					<SPAN STYLE="font-style-italic">Cognome: </SPAN>
					<xsl:value-of select="Poeta/Persona/Cognome" /> <BR />

					<SPAN STYLE="font-style:italic">Eta': </SPAN>
					<xsl:value-of select="Poeta/Persona/Eta" /> <BR />
				
				

				</BODY>


		</HTML>

	</xsl:template>
 </xsl:stylesheet>
