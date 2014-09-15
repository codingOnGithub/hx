package demo;

import javax.xml.bind.annotation.XmlType;


@XmlType(name = "User")
public class UserImpl implements IUser {
    String name;

    public UserImpl() {
    }
    public UserImpl(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }
}

