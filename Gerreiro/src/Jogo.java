import java.util.Random;
import java.util.Scanner;


public class Jogo {
    // Jogo principal ====================================================
    public static void main(String[] args) {
        // CRIAÇÃO DOS GERREIROS
        Gerreiro gerreiro_1 = warriorCreate();
        System.out.println("Aqui estão os status do seu Gerreiro: \n");
        gerreiro_1.Status();
        System.out.println("\n");
        Gerreiro gerreiro_2 = warriorCreate();
        System.out.println("Aqui estão os status do seu Gerreiro: \n");
        gerreiro_2.Status();

        // CRIAÇÃO DAS SPELLS PARA SEREM USADAS NAS BATALHAS
        Spells Segueira = spellsCreate("Segueira");
        Spells Queimadura = spellsCreate("Queimadura");

        // INICIO DA BATALHA
        Battle(gerreiro_1, gerreiro_2);
    }

    public static Spells spellsCreate(String spellsName) {
        Spells spell = new Spells(spellsName);
        return spell;
    }

    // METODO QUE CRIA O GERREIRO ========================================
    public static Gerreiro warriorCreate() {
        Scanner scanner = new Scanner(System.in); // Importa o Scanner

        System.out.println("Criar um Guerreiro");
        System.out.println("Digite o nome do Guerreiro: ");
        String nome1 = scanner.nextLine();
        Gerreiro warrior = new Gerreiro(nome1);
        System.out.println("\n");
        return warrior;
    }
    
    // METODO PARA FAZER A ESCOLHA DO MOVIMENTO
    public static int Movimento (Gerreiro gerreiro){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Faça seu movimento " + gerreiro.getNome());
        System.out.println("\n --- Movimentos --- ");
        System.out.println("[1] Atacar");
        System.out.println("[2] Defender");
        System.out.println("[3] Esquivar");
        System.out.println("[4] Ataque Especial");
        System.out.println("[5] Ataque Mágico");
        System.out.println("[6] Spells");
        
        System.out.println("Escolha: ");
        int answer = scanner.nextInt();
        return answer;
    }

    // METEDO DE BATALHA ===========================================================================
    public static void Battle(Gerreiro gerreiro_1, Gerreiro gerreiro_2){
        Gerreiro first;
        Gerreiro second;
        String winner = "none";
    
        while (winner == "none"){
            // DEFINE QUEM COMEÇA ATACANDO 
            if (gerreiro_1.getVelocidade() < gerreiro_2.getVelocidade()){
                first = gerreiro_1;
                second = gerreiro_2;
            }else if (gerreiro_1.getVelocidade() != gerreiro_2.getVelocidade()){
                first = gerreiro_2;
                second = gerreiro_1;
            }else if (gerreiro_1.getDefesa() > gerreiro_2.getDefesa()){
                first = gerreiro_1;
                second = gerreiro_2;
            }else {
                first = gerreiro_2;
                second = gerreiro_1;
            } 
            // PRIMEIRO MOVIMENTO
            // [1] Atacar
            // [2] Defender
            // [3] Esquivar
            // [4] Ataque Especial
            // [5] Ataque Mágico
            // [6] Spells
            int moveFirst = Movimento(first);
            int moveSecond = Movimento(second);
            
            switch (moveFirst){
                // ATAQUE
                case 1:
                String extra = "Nada";
                switch (moveSecond){
                    case 2:
                    extra = "Defesa";
                    break;
                    case 3:
                    extra = "Esquiva";
                    break;
                }
                AttackMove(first, second, extra);
                break;

                // ATAQUE ESPECIAL
                case 4:
                extra = "Nada";
                    switch (moveSecond){
                        case 3:
                            extra = "Esquiva";
                            break;
                    }
                EspecialAtackMove(first, second, extra);
                break;
                
                // ATAQUE MÁGICO
                case 5:
                extra = "Nada";
                    switch (moveSecond){
                        
                    }
                MagicAtackMove(first, second);
                break;

                // SPELLS
                case 6:
                    SpellsChoiceMove(first, second);
                    break;
            }

            // ATAQUES DO SEGUNDO JOGADOR
            switch (moveSecond){
                case 1:
                String extra = "Nada";
                switch (moveFirst){
                    case 2:
                    extra = "Defesa";
                    break;
                    case 3:
                    extra = "Esquiva";
                    break;
                }
                AttackMove(second, first, extra);
                break;
            
                case 4:
                extra = "Nada";
                    switch (moveFirst){
                        case 3:
                            extra = "Esquiva";
                            break;
                    }
                EspecialAtackMove(second, first, extra);
                break;

                // ATAQUE MÁGICO
                case 5:
                extra = "Nada";
                    switch (moveFirst){
                        
                    }
                MagicAtackMove(second, first);
                break;

            }
            

            // APLICAÇÃO DO DANO DOS STATUS DE EFEITO
            int statusDamage = first.getStatusEfectDamage();
            if (statusDamage > 0){
                first.setVida(statusDamage);
                first.setStatusEfectCount(first.getstatusEfectCount() - 1);
                System.out.println(first.getNome() + "sofreu" + statusDamage + "de dano com " + first.getStatusEfect());
            }


            statusDamage = second.getStatusEfectDamage();
            if (statusDamage > 0){
                second.setVida(statusDamage);
                second.setStatusEfectCount(second.getstatusEfectCount() - 1);
                System.out.println(second.getNome() + "sofreu" + statusDamage + "de dano com " + second.getStatusEfect());
            }



        
            // VERIFICAÇÃO DE VENCEDOR
            if(first.getVida() < 0){
                winner = second.getNome();
            }else if(second.getVida() < 0){
                winner = first.getNome();
            }
        }
        System.out.println(winner + " foi o vencedor desta batalha.\n ");
        
    }


    // MOVIMENTO DE ATAQUE [1] ===================================================================================================================================
    public static void AttackMove (Gerreiro atacante, Gerreiro defensor, String extra){
        int defesa = defensor.getDefesa();
        int ataque = atacante.getDano();
        
        //  APLICAÇÃO DO SISTEMA DE DEFESA CASO O DEFENSOR TENHO FEITO O MOVIMENTO DE DEFESA
        if (extra == "Defesa"){
            defesa = DefenseMove(defensor);
            int defesaAdicional = defensor.getDefesa() + 4;
            defensor.setDefesa(defesaAdicional);
        }
        // APLICAÇÃO DO SISTEMA DE ESQUIVA
        if (extra == "Esquiva"){
            if (DodgeMove(atacante, defensor) == true){
                ataque = 0;
            }else{
                defesa /= 2;
            }
        }
        // APLICAÇÃO DA DEFESA
        ataque -= defesa;
        if (ataque < 0){
            ataque = 0;
        }
        // APLICAÇÃO DO DANO 
        int vidaRestante = defensor.getVida() - ataque;
        defensor.setVida(vidaRestante);
        
        // RETORNO DO FEEDBACK
        System.out.println("\n");
        System.out.println("O ataque de " + atacante.getNome() + " Causou um [DANO de " + ataque + "] contra a [DEFESA de " + defesa + "] de " + defensor.getNome());
        System.out.println("A vida de " + defensor.getNome() + " ficou em " + defensor.getVida() + "\n");
        System.out.println("\n");
    }

    // MOVIMENTO DE DEFESA [2] ===============================================================================================================================
    public static int DefenseMove (Gerreiro defensor){
        Random random = new Random();
        int defesa = 0;
        double multiplicador = random.nextDouble() + 1;
        defesa = (int)(multiplicador * defensor.getDefesa());
        System.out.println("A defesa total " + defensor.getNome() + " chegou a " + defesa + "\n ");
        return defesa;
    }
    
    // MOVIMENTO DE ESQUIVA [3] ==========================================================================================================================
    public static boolean DodgeMove (Gerreiro atacante, Gerreiro defensor){
        Random random = new Random();
        int esquiva = 0;
        double multiplicador = random.nextDouble() + 1; // APLICADOR DAS CANCHES DE ESQUIVA
        esquiva = (int) (multiplicador * defensor.getVelocidade());
        // RETORNO DO FEEDBACK CASO A ESQUIVA POSSUA SUCESSO [SER MAIOR QUE 20] OU FALHE [SER MENOR QUE 20]
        if (esquiva > 20){
            System.out.println("\n");
            System.out.println(defensor.getNome() + " foi ágil o sufiente para escapar do golpe.");
            return true;
        }else{
            System.out.println(defensor.getNome() + " não foi ágil o suficiente e sua guarda ficou aberta. [sua defesa caiu pela metade]"); 
            return false;
        }
    }

    // SISTEMA DE ATAQUE ESPECIAL [4] ==========================================================================================================================
    public static void EspecialAtackMove (Gerreiro atacante, Gerreiro defensor, String extra){
        Random random = new Random();
        int ataqueEspecial = atacante.getDano();
        int precisao = atacante.getPrecisao();

        // CALCULO DO DANO DO ATAQUE ESPECIAL =================
        ataqueEspecial *= (int) 1.5;
        
        // SISTEMA DE ESQUIVA
        if (extra == "Esquiva"){
            if (DodgeMove(atacante, defensor) == true)
            ataqueEspecial = 0;
        }

        // SISTEMA DE PRECISÃO DO ATAQUE ESPECIAL
        int chance = random.nextInt(10);
        if (chance > precisao){
            ataqueEspecial = 0;
        }
        
        // APLICAÇÃO DO DANO 
        int vidaRestante = defensor.getVida() - ataqueEspecial;
        defensor.setVida(vidaRestante);

        // RETORNO DO FEEDBACK
        System.out.println("\n");
        if (ataqueEspecial == 0){
            System.out.println(atacante.getNome() + " não foi capaz de acerta seu [ATAQUE ESPECIAL].");
        }else{
            System.out.println("O [ATAQUE ESPECIAL] de " + atacante.getNome() + " Causou um [DANO de " + ataqueEspecial + "] ignorando a defesa de " + defensor.getNome());
            System.out.println("A vida de " + defensor.getNome() + " ficou em " + defensor.getVida());
        }
    }

    // SISTEMA DE ATAQUE MÁGICO [5] ==========================================================================================================================
    public static void MagicAtackMove (Gerreiro atacante, Gerreiro defensor){
        int danoMagico = atacante.getDanoMagico();
        int defesaMagica = defensor.getResistenciaMagica();

        // CALCULO DO DANO MÁGICO
        danoMagico -= defesaMagica;
        defensor.setVida(danoMagico);
        System.out.println("O [ATAQUE MÁGICO] de " + atacante.getNome() + " Causou um [DANO MÁGICO de " + danoMagico + "] contra a [RESISTÊNCIA MÁGICA de " + defesaMagica + "] de " + defensor.getNome());
        System.out.println("A vida de " + defensor.getNome() + " ficou em " + defensor.getVida()); 
    }

    // SISTEMA DE ATAQUE MÁGICO [5] ==========================================================================================================================
    public static void SpellsChoiceMove(Gerreiro atacante, Gerreiro defensor){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha qual Spell vai usar: ");
        System.out.println("[1] Segueira");
        System.out.println("[2] Queimadura");
        int Escolha = scanner.nextInt();
        
        String spellEcolhida = "";
        switch (Escolha){
            case 1:
                spellEcolhida = "Segueira";
                break;
            
            case 2:
                spellEcolhida = "Queimadura";
                break;
        
        }

        SpellAtackMove(spellEcolhida, atacante, defensor);
    }


    public static void SpellAtackMove(String spellsName, Gerreiro atacante, Gerreiro defensor){
        Spells spell = new Spells(spellsName);
        defensor.setStatusEfect(spell.getSpellsStatusEfect());
        defensor.setStatusEfectCount(spell.getSpellsDuration());
        defensor.setStatusEfectDamage(spell.getSpellsDamage());

    }





}
