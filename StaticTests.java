/*
 * StaticTests.java
 *
 * Created on 7 de Julho de 2008, 22:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo Moreti
 */
public class StaticTests extends StaticSuper
{
    static int rand;   //Variável estática.
    
    static   //Bloco estático.
    {
        rand = (int) (Math.random() * 6);
        System.out.println("static block " + rand);
    }
    
    public StaticTests()   //Construtor da classe.
    {
        System.out.println("constructor");
    }
}