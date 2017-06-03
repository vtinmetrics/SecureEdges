<%@page import="br.com.secureedges.domain.EntidadeDominio"%>
<%@page import="br.com.secureedges.domain.Dispositivo"%>
<%@page import="br.com.secureedges.domain.Mes"%>
<%@page import="br.com.secureedges.domain.Solicitacao"%>
<%@page import="br.com.secureedges.domain.Grafico"%>
<%@page import="br.com.secureedges.domain.RelatorioSolicitacao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	Grafico bean = new Grafico();
	RelatorioSolicitacao solicitacao = (RelatorioSolicitacao) bean.relatorios.get(0);
	List<EntidadeDominio> dispositivos = bean.dispositivos;
	boolean existe = false;
%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>



	<div id="chart1" style="width: 650px; height: 600px;">
		<script type="text/javascript">
			// Load the Visualization API and the corechart package.
			google.charts.load('current', {
				'packages' : [ 'corechart' ]
			});
			google.charts.setOnLoadCallback(drawChart);

			function drawChart() {
				// Some raw data (not necessarily accurate)

				var row = new Array();

				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Month');
		<%for (EntidadeDominio disp : dispositivos) {
				Dispositivo temp_disp = (Dispositivo) disp;
				out.print("data.addColumn('number','" + temp_disp + "');\n");
			}

			for (Mes mt : solicitacao.getListMes()) {
				//String javaCode = "data.addRow('"+mt.getName()+"',";
				out.print("row.push('" + mt.getNome() + "');\n");
				//int i = 0 ;
				for (EntidadeDominio disp : dispositivos) {
					existe = false;
					Dispositivo temp_disp = (Dispositivo) disp;
					for (Solicitacao sol : mt.getListDispositivo()) {
						if (temp_disp.getCodigo() == sol.getDispositivo().getCodigo()) {
							out.print("row.push('" + sol.getQtde() + "');\n");
							existe = true;
						}
					}
					if (existe != true) {
						out.print("row.push('" + 0 + "');\n");
					}

				}
				//javaCode += ");\n";
				out.print("data.addRow(row);");
				out.print("row = [];");
				//out.print(javaCode);
			}%>
			var options = {
					title :
		<%out.print("'" + solicitacao.getTitulo() + "'");%>
			,
					pointSize : 5
				};

				var chart = new google.visualization.LineChart(document
						.getElementById('chart1'));

				chart.draw(data, options);

			}
		</script>
	</div>

</body>
</html>