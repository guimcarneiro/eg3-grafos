package logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.alg.scoring.AlphaCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import comparators.AlphaScoreComparator;
import comparators.BeetweennessComparator;
import entities.User;

/**
 * Classe responsavel pela logica do EP3 da disciplina de Teoria dos Grafos.
 */
public class pratica03 {
	
	public static double truncate(Double valor, int precisao) {
		BigDecimal bd = BigDecimal.valueOf(valor);
		bd = bd.setScale(precisao, BigDecimal.ROUND_DOWN);

		return bd.doubleValue();
	}
	
	private static SimpleGraph<String, DefaultEdge> importaGrafo() {
		
		SimpleGraph<String, DefaultEdge> grafoBase = new SimpleGraph<>(DefaultEdge.class);

		grafoBase = (SimpleGraph<String, DefaultEdge>) MyJGraphTUtil.importDefaultGraphGML(
				grafoBase, 
				"ep03outro.gml");	//importa GML
		
		return grafoBase;
	}
	
	//Reordena a lista com os devidos Comparators criados (AlphaScoreComparator e BeetweennessScoreComparator
	//para obter os mais influentes e os bons disseminadores, assim como pedido nas questoes
	
	//User guarda as informacoes de cada vertice: ID, alphaScore, BetweennessScore
	private static List<User> listaUsuarios(SimpleGraph<String, DefaultEdge> grafo){
		
		List<User> usuarios = new ArrayList<User>();
		
		AlphaCentrality<String, DefaultEdge> ac = new AlphaCentrality<String, DefaultEdge>(grafo);
		BetweennessCentrality<String, DefaultEdge> bc = new BetweennessCentrality<String, DefaultEdge>(grafo, true);

		for(String vertice: grafo.vertexSet()) {
			
			double alphaScore = ac.getVertexScore(vertice);
			double beetScore = bc.getVertexScore(vertice);
			
			usuarios.add(new User(vertice, alphaScore,beetScore));
		}
		//Collections.sort(usuarios, new AlphaScoreComparator());
		return usuarios;
	}
	
	public static void main(String[] args) {
		
		List<User> usuarios = listaUsuarios(importaGrafo());
		
		//Imprime de maneira decrescente com base no AlphaScore de cada User os 5 vertices mais influentes
		System.out.println("5 VERTICES MAIS INFLUENTES:\n");
		Collections.sort(usuarios, new AlphaScoreComparator());
		for(int i=0; i<5; i++) {
			System.out.println("#ID: " + usuarios.get(i).getNome() + "\n**********");
		}
		
		System.out.println("--------------------------------------------------");
		
		//Imprime de maneira decrescente com base no BeetweennessScore de cada User os 10 maiores vertices disseminadores
		System.out.println("10 VERTICES MELHORES DISSEMINADORES:\n");
		Collections.sort(usuarios, new BeetweennessComparator());
		for(int i=0; i<5; i++) {
			System.out.println("§ID: " + usuarios.get(i).getNome() + "\n**********");
		}
	}
	
}
