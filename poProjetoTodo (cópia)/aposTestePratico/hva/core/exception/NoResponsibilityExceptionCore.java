package hva.core.exception;

public class NoResponsibilityExceptionCore extends Exception {
  
    /** Serial number for serialization */
    private static final long serialVersionUID = 7480881468928106245L;
    private String _key;
    private String _name;

    public NoResponsibilityExceptionCore(String key, String name) {
        _key = key;
        _name = name;
    }

    public String getKey() {
        return _key;
    }

    public String getName(){
      return _name;
    }
}