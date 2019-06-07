package entities;

public class User{

	private String nome;
	private double alphaScore;
	private double beetweennessScore;
	
	public User(String nome, double alphaScore, double beetweennessScore) {
		this.nome = nome;
		this.alphaScore = alphaScore;
		this.beetweennessScore = beetweennessScore;
	}

	public String getNome() {
		return nome;
	}

	public double getAlphaScore() {
		return alphaScore;
	}

	public double getBeetweennessScore() {
		return beetweennessScore;
	}
}
