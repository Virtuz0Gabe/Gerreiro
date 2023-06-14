import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicButtonListener;

public class Jogo {

    // Gerador de Gerreiro
    public static void main(String[] args) {
        Gerreiro gerreiro_1 = warriorCreate();
        System.out.println("Aqui estão os status do seu Gerreiro: \n");
        gerreiro_1.Status();

        System.out.println("\n");
        Gerreiro gerreiro_2 = warriorCreate();
        System.out.println("Aqui estão os status do seu Gerreiro: \n");
        gerreiro_2.Status();
        
        //Battle();
    }

    public static Gerreiro warriorCreate() {

        Scanner scanner = new Scanner(System.in); // Importa o Scanner

        System.out.println("Criar um Guerreiro");
        System.out.println("Digite o nome do Guerreiro: ");
        String nome1 = scanner.nextLine();
        Gerreiro warrior = new Gerreiro(nome1);
        
        System.out.println("\n");

        return warrior;
    }

    public static void Battle(Gerreiro gerreiro_1, Gerreiro gerreiro_2){

        Gerreiro first;
        Gerreiro second;
        
        if (gerreiro_1.getVelocidade() > gerreiro_2.getVelocidade()){
            first = gerreiro_1;
            second = gerreiro_2;
        }else{
            first = gerreiro_2;
            second = gerreiro_1;
        }
        
    }
}
