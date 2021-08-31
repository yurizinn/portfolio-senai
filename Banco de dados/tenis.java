public class Tenis {
	
	public int marca_id;
	public String nome;

	public Tenis(int marca_id, String nome) {
		this.marca_id = marca_id;
		this.nome = nome;
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	pubic int getMarcaId(){
		return marca_id;
	}

	public void setMarcaId(int marca_id){
		this.marca_id = marca_id;
	}	
}