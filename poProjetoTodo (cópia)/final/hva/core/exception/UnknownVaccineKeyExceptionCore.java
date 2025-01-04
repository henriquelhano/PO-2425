package hva.core.exception;

public class UnknownVaccineKeyExceptionCore extends Exception {
  
    /** Serial number for serialization */
    private static final long serialVersionUID = 7480881468928106245L;
    private String _key;

    public UnknownVaccineKeyExceptionCore(String key) {
        _key = key;
    }

    public String getKey() {
        return _key;
    }
}