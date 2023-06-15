import java.util.Random;

public class Gerreiro {
        
    private String nome;
    private int dano;
    private int vida;
    private int defesa;
    private int velocidade;
    private int precisao;
    
    private int danoMagico;
    private int resistenciaMagica;
    private int mana;
    private String statusEfect;
    private int statusEfectCount;
    private int statusEfectDamage;


    public Gerreiro(String nome) {
        Random random = new Random(); // Importa o Random

        this.nome = nome;
        this.dano = 20 + random.nextInt(31);
        this.vida = 100 + random.nextInt(71);
        this.defesa = 5 + random.nextInt(16);
        this.velocidade = 10 + random.nextInt(11);
        this.precisao = 3 + random.nextInt(8);
        this.danoMagico = 10 + random.nextInt(16);
        this.resistenciaMagica = 5 + random.nextInt(6);
        this.mana = 20;
        this.statusEfect = "None";


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

    public int getPrecisao(){
        return precisao;
    }

    public int getDanoMagico(){
        return danoMagico;
    }

    public int getResistenciaMagica(){
        return resistenciaMagica;
    }

    public int getMana(){
        return mana;
    }

    public String getStatusEfect(){
        return statusEfect;
    }

    public int getstatusEfectCount (){
        return statusEfectCount;
    }

    public int getStatusEfectDamage() {
        return statusEfectDamage;
    }


    public void Status(){
        
        System.out.println("--- Status ------" + this.nome + "-------");
        System.out.println("🗡️Dano: " +                         this.dano);
        System.out.println("💔Vida: " +                         this.vida);
        System.out.println("🛡️Defesa: " +                     this.defesa);
        System.out.println("🎯Precisão: " +                 this.precisao);
        System.out.println("💨Velocidade: " +             this.velocidade);
        System.out.println("🪄Dano Mágico: " +            this.danoMagico);
        System.out.println("🟡Resistencia Mágica: "+this.resistenciaMagica);
    }

    // SETTERS ===============================================================
    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setStatusEfect(String statusEfect) {
        this.statusEfect = statusEfect;
    }

    public void setStatusEfectCount(int statusEfectCount) {
        this.statusEfectCount = statusEfectCount;
    }

    public void setStatusEfectDamage(int statusEfectDamage) {
        this.statusEfectDamage = statusEfectDamage;
    }

    

    
    
}
