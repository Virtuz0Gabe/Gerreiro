import java.util.Random;

public class Gerreiro {
        
    private String nome;
    private int dano;
    private int vida;
    private int defesa;
    private int velocidade;

    public Gerreiro(String nome) {
        Random random = new Random(); // Importa o Random

        this.nome = nome;
        this.dano = 20 + random.nextInt(31);
        this.vida = 100 + random.nextInt(71);
        this.defesa = 5 + random.nextInt(16);
        this.velocidade = 10 + random.nextInt(11);
    }

    // GETTERS =============================================================
    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    public int getVida() {
        return vida;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void Status(){
        System.out.println("--- Status -------------------");
        System.out.println("Nome: " +             this.nome);
        System.out.println("Dano: " +             this.dano);
        System.out.println("Vida: " +             this.vida);
        System.out.println("Defesa: " +         this.defesa);
        System.out.println("Velocidade: " + this.velocidade);
    }

    // SETTERS ===============================================================
    public void setVida(int vida) {
        this.vida = vida;
    }

    
    
}
