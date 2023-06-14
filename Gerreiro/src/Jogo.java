import java.util.Random;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;


public class Jogo {

    // Jogo principal ====================================================
    public static void main(String[] args) {
        Gerreiro gerreiro_1 = warriorCreate();
        System.out.println("Aqui estão os status do seu Gerreiro: \n");
        gerreiro_1.Status();

        System.out.println("\n");
        Gerreiro gerreiro_2 = warriorCreate();
        System.out.println("Aqui estão os status do seu Gerreiro: \n");
        gerreiro_2.Status();
        
        Battle(gerreiro_1, gerreiro_2);
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

    // METEDO DE BATALHA ===========================================================================
    public static void Battle(Gerreiro gerreiro_1, Gerreiro gerreiro_2){

        Gerreiro first;
        Gerreiro second;
        String winner = "none";
        
        while (winner == "none"){

            // DEFINE QUEM COMEÇA ATACANDO 
            if (gerreiro_1.getVelocidade() > gerreiro_2.getVelocidade()){
                first = gerreiro_1;
                second = gerreiro_2;
            }else if (gerreiro_1.getVelocidade() != gerreiro_2.getVelocidade()){
                first = gerreiro_2;
                second = gerreiro_1;
            }else if (gerreiro_1.getDefesa() < gerreiro_2.getDefesa()){
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
            int moveFirst = Movimento(first);
            int moveSecond = Movimento(second);
            
            switch (moveFirst){
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
                
            }
            
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
            }
            

        
            // VERIFICAÇÃO DE VENCEDOR
            if(first.getVida() < 0){
                winner = second.getNome();
            }else if(second.getVida() < 0){
                winner = first.getNome();
            }
        }
        System.out.println(winner + "foi o vencedor desta batalha.\n ");
        
    }

    public static int Movimento (Gerreiro gerreiro){
        Scanner scanner = new Scanner(System.in); 

        System.out.println("\n");
        System.out.println("Faça seu movimento " + gerreiro.getNome());
        System.out.println("\n --- Movimentos --- ");
        System.out.println("[1] Atacar");
        System.out.println("[2] Defender");
        System.out.println("[3] Esquivar");
        System.out.println("Escolha: ");
        int answer = scanner.nextInt();

        return answer;
    }

    // MOVIMENTO DE ATAQUE
    public static void AttackMove (Gerreiro atacante, Gerreiro defensor, String extra){
        
        //  APLICAÇÃO DO SISTEMA DE DEFESA CASO O DEFENSOR TENHO FEITO O MOVIMENTO DE DEFESA
        Random random = new Random();
        int defesa = defensor.getDefesa();
        if (extra == "Defesa"){
            double multiplicador = random.nextDouble() + 1;
            defesa = (int)(multiplicador * defensor.getDefesa());
            System.out.println("A defesa total " + defensor.getNome() + " chegou a " + defesa + "\n ");
        } // --------------------------------------------------------------------------------

        // APLICAÇÃO DO SISTEMA DE ESQUIVA
        int esquiva = 0;
        if (extra == "Esquiva"){
            double multiplicador = random.nextDouble() + 1;
            esquiva = (int) (multiplicador * defensor.getVelocidade());
            System.out.println("esquiva" + multiplicador);
            System.out.println("Esquiva calculadinha: " + esquiva);
            if (esquiva > 20){
                defesa = 200;
                System.out.println(defensor.getNome() + " foi ágil o sufiente para escapar do golpe\n");
            }else{
                defesa /= 2;
                System.out.println(defensor.getNome() + " não foi ágil o suficiente e sua guarda ficou aberta, [sua defesa caiu pela metade]"); 
            }
        }
        

        // CALCULO DO DANO =================
        int ataque = atacante.getDano();
        // APLICAÇÃO DA DEFESA
        ataque -= defesa;
        if (ataque < 0){
            ataque = 0;
        }
        
        // APLICAÇÃO DO DANO 
        int vidaRestante = defensor.getVida() - ataque;
        defensor.setVida(vidaRestante);

        // RETORNO DO FEEDBACK
        System.out.println("O ataque de " + atacante.getNome() + " Causou um [DANO de " + ataque + "] contra a [DEFESA de " + defesa + "] de " + defensor.getNome() + "\n");
        System.out.println("A vida de " + defensor.getNome() + " ficou: " + defensor.getVida());
    }

}
