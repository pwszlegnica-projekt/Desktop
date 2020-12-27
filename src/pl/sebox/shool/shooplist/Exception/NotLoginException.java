package pl.sebox.shool.shooplist.Exception;

public class NotLoginException extends Exception {
    public NotLoginException(){
        super("Not login");
    }
    NotLoginException(String message){
        super(message);
    }
}
