package com.tubetv.exception;



public class UsernameOrPasswordInvalidException extends RuntimeException{

    public UsernameOrPasswordInvalidException(String message){
        super(message);
    }
    /*
    * Assim como tudo no Java exceptions também são um objeto, portanto podemos criar nossas próprias mensagens
    * de exceptions extendendo da classes basicas 'runtime' ou 'io' por exemplo
    * */


}
