import java.util.Arrays;

public class Protocol {

    public static String add(String[] parameters){
        if(parameters.length == 3){
            int intParam;
            try {
                intParam = Integer.parseInt(parameters[2]);
            } catch (NumberFormatException e){
                return "ERRO 400: Segundo parâmetro não é inteiro.";
            }
            if (intParam < 0)
                return "ERRO 400: Segundo parâmetro não é inteiro positivo.";
        }
        else{
            return "ERRO 400: Número inadequado de parâmetros.";
        }
        return null;
    }

    public static String change(String[] parameters){
        if(parameters.length == 3){
            int intParam;
            try {
                Integer.parseInt(parameters[1]);
                intParam = Integer.parseInt(parameters[2]);
            } catch (NumberFormatException e){
                return "ERRO 400: Primeiro ou segundo parâmetro não é inteiro.";
            }
            if (intParam < 0)
                return "ERRO 400: Segundo parâmetro não é inteiro positivo.";
        }
        else{
            return "ERRO 400: Número innadequado de parâmetros.";
        }
        return null;
    }

    public static String remove(String[] parameters){
        if(parameters.length == 2){
            try {
                Integer.parseInt(parameters[1]);
            } catch (NumberFormatException e){
                return "ERRO 400: Primeiro parâmetro não é inteiro.";
            }
        }
        else{
            return "ERRO 400: Número innadequado de parâmetros.";
        }
        return null;
    }

    public static String show(String[] parameters){
        if(parameters.length == 2){
            try {
                Integer.parseInt(parameters[1]);
            } catch (NumberFormatException e){
                return "ERRO 400: Primeiro parâmetro não é inteiro.";
            }
        }
        else{
            return "ERRO 400: Número innadequado de parâmetros.";
        }
        return null;
    }

}
