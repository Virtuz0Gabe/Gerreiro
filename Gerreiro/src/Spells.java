
public class Spells {

    private String spellsName;
    private String spellsStatusEfect;
    private int spellsDamage;
    private int spellsDuration;
    private int manaConsume;


    public Spells(String spellsName) {
        // FAZ A PRECISÃO DO DEFENSOR ZERAR REDUZINDO 30% DO DANO DO DEFENSOR
        if(spellsName == "Segueira"){
            this.spellsName = spellsName;
            this.spellsStatusEfect = "Segueira";
            this.spellsDamage = 0;
            this.spellsDuration = 1;
            this.manaConsume = 5;
        }

        // CAUSA DANO CONTÍNUO POR 3 ROUNDS 
        if(spellsName == "Queimadura"){
            this.spellsName = spellsName;
            this.spellsStatusEfect = "Queimadura";
            this.spellsDamage = 10;
            this.spellsDuration = 3;
            this.manaConsume = 20;
        }




    }

    // GETTERS 
    public String getSpellsName() {
        return spellsName;
    }

    public String getSpellsStatusEfect() {
        return spellsStatusEfect;
    }

    public int getSpellsDamage() {
        return spellsDamage;
    }

    public int getSpellsDuration() {
        return spellsDuration;
    }

    public int getManaConsume() {
        return manaConsume;
    }

    // SETTERS
    public void setSpellsName(String spellsName) {
        this.spellsName = spellsName;
    }

    


    
}