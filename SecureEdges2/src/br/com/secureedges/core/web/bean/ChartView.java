package br.com.secureedges.core.web.bean;
 
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.mysql.fabric.xmlrpc.base.Array;

import br.com.secureedges.core.dao.RelatorioDAO;
import br.com.secureedges.core.dao.SolicitacaoDAO;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Grafico;
import br.com.secureedges.domain.RelatorioSolicitacao;
import br.com.secureedges.domain.Solicitacao;

import org.primefaces.model.chart.ChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
    Grafico grafico =  new Grafico();
    private BarChartModel initBarModel() {
    	Map<Integer, Integer> graficoAprov = null;
    	Map<Integer, Integer> graficoReprov = null;
    	RelatorioDAO relatorioDAO = new RelatorioDAO();
    	SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
    	List<Solicitacao> solicitacoes = new ArrayList<>();
    	List<EntidadeDominio> solicitacoesRecebe = relatorioDAO.listar();
    	graficoAprov=  new HashMap<Integer , Integer >();
    	graficoReprov=  new HashMap<Integer , Integer >();
    	for(EntidadeDominio solicitacoesAux : solicitacoesRecebe){
    		Solicitacao sol = new Solicitacao();
    		sol = (Solicitacao) solicitacaoDAO.buscarPorCodigo(solicitacoesAux.getCodigo());
    		solicitacoes.add(sol);
    	}
    	//for reprovados
    	for(Solicitacao solicitacaoPercorre : solicitacoes){
    		int mes = solicitacaoPercorre.getData().getMonth();
    		if (solicitacaoPercorre.getStatus().equals("reprovada")){
    			
    			switch (mes) {
    			case 1:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 2:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 3:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 4:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 5:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 6:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 7:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 8:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 9:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 10:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 11:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 12:
    				graficoReprov.put(mes, solicitacaoPercorre.getQtde());
    				break;

    			default:
    				break;
    			}
    			
    		}
    		
    		
    	}
    	
    	
    	for(Solicitacao solicitacaoPercorre : solicitacoes){
    		int mes = solicitacaoPercorre.getData().getMonth();
    		if (solicitacaoPercorre.getStatus().equals("aprovada")){
    			
    			switch (mes) {
    			case 1:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 2:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 3:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 4:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 5:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 6:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 7:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 8:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 9:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 10:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 11:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;
    			case 12:
    				graficoAprov.put(mes, solicitacaoPercorre.getQtde());
    				break;

    			default:
    				break;
    			}
    			
    		}
    		
    		
    	}
    	
    	for( int a = 1 ; a <= 12 ; a ++){
    		System.out.println("mes" + a +":"+ graficoAprov.get(a));
    	}
    	
        BarChartModel model = new BarChartModel();
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Aprovado");
        boys.set("Janeiro", graficoAprov.get(1));
        boys.set("Fevereiro", graficoAprov.get(2));
        boys.set("Março", graficoAprov.get(3));
        boys.set("Abril", graficoAprov.get(4));
        boys.set("Maio", graficoAprov.get(5));
        boys.set("Junho", graficoAprov.get(6));
        boys.set("Julho", graficoAprov.get(7));
        boys.set("Agosto", graficoAprov.get(8));
        boys.set("Setembro", graficoAprov.get(9));
        boys.set("Outubro", graficoAprov.get(10));
        boys.set("Novembro", graficoAprov.get(11));
        boys.set("Dezembro", graficoAprov.get(12));
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Reprovado");
        girls.set("Janeiro", 120);
        girls.set("Fevereiro", 100);
        girls.set("Março", 44);
        girls.set("Abril", 150);
        girls.set("Maio", 25);
        girls.set("Junho", 150);
        girls.set("Julho", 25);
        girls.set("Agosto", 150);
        girls.set("Setembro", 25);
        girls.set("Outubro", 150);
        girls.set("Novembro", 25);
        girls.set("Dezembro", 150);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");        
    }
 
}