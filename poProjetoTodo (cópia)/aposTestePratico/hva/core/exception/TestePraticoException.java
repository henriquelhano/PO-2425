package hva.core.exception;

public class TestePraticoException extends Exception{

    /** Serial number for serialization */
    private static final long serialVersionUID = 7480881468928106245L;
    private String _key;

    public TestePraticoException() {
        _key = "numero de caracteres excedidos.";
    }

    public String getKey() {
        return _key;
    }
}
