public class Protocol {

    public static String add(String[] parameters){
        if(parameters.length == 3){
            try {
                Integer.parseInt(parameters[2]);
            } catch (NumberFormatException e){
                return "400 Segundo parametro nao e inteiro";
            }
        }
        else{
            return "400 Numero inadequado de parametros";
        }
        return null;
    }

    public static String change(String[] parameters, int taskListSize){
        int intParam1;

        if(parameters.length == 3){
            try {
                intParam1 = Integer.parseInt(parameters[1]);
                Integer.parseInt(parameters[2]);
            } catch (NumberFormatException e){
                return "400 Primeiro ou segundo parametro nao e inteiro";
            }
        }
        else{
            return "400 Numero inadequado de parametros";
        }

        if(intParam1 >= taskListSize || intParam1 < 0) {
            return "400 Posicao invalida";
        }

        return null;
    }

    public static String remove(String[] parameters, int taskListSize){
        int intParam1;

        if(parameters.length == 2){
            try {
                intParam1 = Integer.parseInt(parameters[1]);
            } catch (NumberFormatException e){
                return "400 Primeiro parametro nao e inteiro";
            }
        }
        else{
            return "400 Numero inadequado de parametros";
        }

        if(intParam1 >= taskListSize || intParam1 < 0) {
            return "400 Posicao invalida";
        }

        return null;
    }

    public static String ValidMessage(){
        return "30 Mensagem Aceita";
    }

    public static String InvalidCommand(){
        return "400 Comando Invalido";
    }

    public static String TranslateResponseMessage(String serverResponse) {
        int i;
        String responseMessage = "";

        for(i = 0; i < serverResponse.length(); i++) {
            if(Character.compare(serverResponse.charAt(i), '#') == 0) {
                responseMessage += "\n"; // changes "#" to "\n"
            } else {
                responseMessage += serverResponse.charAt(i); // changes "#" to "\n"
            }
        }

        return responseMessage + "\n";
    }
}
