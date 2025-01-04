package hva.core;

import java.io.Serial;
import java.io.Serializable;

public abstract class HotelEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 202208091753L;
  private String _id;
  private String _name;

  protected HotelEntity(String id, String name) {
    _id = id;
    _name = name;
  }

  protected String getID() {
    return _id;
  }

  protected String getName() {
    return _name;
  }

  public int hashCode() {
    int result = 17;
    result = 31 * result + (_id == null ? 0 : _id.hashCode());
    result = 31 * result + (_name == null ? 0 : _name.hashCode());
    return result;
  }

  
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    HotelEntity otherEntity = (HotelEntity) obj;
    return (_id == null ? otherEntity._id == null : _id.equals(otherEntity._id)) &&
        (_name == null ? otherEntity._name == null : _name.equals(otherEntity._name));
  }
}
