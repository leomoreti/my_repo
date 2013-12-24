/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author localuser
 */
import java.util.regex.PatternSyntaxException;

public class LetsSplit
{
    public static void main(String[] args)
    {
        //Dividindo com o método split() da classe String
        //
        //O método split() da classe String permite que você divida uma String em pedaços (tokens).
    
        //Criamos uma String, e colocamos um separador "/".
        String str = "O que resulta de azul + amarelo?/verde";
    
        //O método split() recebe como parâmtetro um separador (nesse caso uma "/"). Ele percorrerá
        //a string e quando encontrar esse símbolo separador, fará uma divisão. Quando o método terminar
        //de percorrer a string por completo, ele guardará todas estas divisões em uma array de Strings e
        //a retornará para você.
        String[] result = str.split("/");
    
        //Neste exemplo, vamos ter 2 tokens, ou seja, apenas 2 trechos de String, porque só havia 1 separador
        //na String.
        for(String token:result)
        {
           System.out.println(token);
        }
        
        str = "O que/resulta/de/azul + /amarelo?/verde";
        result = str.split("/");
        
        for(String token2:result)
        {
            System.out.println(token2);
        }
        
        str = "O que ( voce quer ) aqui ( cara?";
        
        try
        {
            result = str.split("(");
        
            for(String token3:result)
            {
                System.out.println(token3);
            }
           
        } catch(PatternSyntaxException psex)
          {
            System.out.println("Description: " + psex.getDescription());
            System.out.println("Message: " + psex.getMessage());
            System.out.println("Pattern: " + psex.getPattern());
          }
        
        str = "Vamos fazer/ agora com o/ loop for/ antigo!";
        result = str.split("/");
        
        for(int i = 0;i < result.length;i++)
        {
            System.out.println(result[i]);
        }
        
        for(String token4:result)
        {
            System.out.println(token4);
        }
        
        String[] result2 = new String[3];
        String [] result3 = new String[3];
        String result4[] = new String[3];
        String []result5 = new String[3];
        String[] result6 = new String[3];
        String[] result7 = new String[3];
        
    }
}
