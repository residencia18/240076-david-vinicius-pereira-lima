package aula1.Aula1.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Digite o verbo: ");
        Scanner sc = new Scanner(System.in);
        String verb = sc.nextLine();
        Verbo verbo = new Verbo(verb);
        verbo.conjugar();
        System.out.println("Verbo:"+verbo.stringVerbo()+"\nRadical:"+verbo.getRadical()+"\nSufixo:"+verbo.getSufixo());
        sc.close();
    }

}
