package demo;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class UserAdapter extends XmlAdapter<UserImpl, IUser> {
    public UserImpl marshal(IUser v) throws Exception {
        if (v instanceof UserImpl) {
            return (UserImpl)v;
        }
        return new UserImpl(v.getName());
    }

    public IUser unmarshal(UserImpl v) throws Exception {
        return v;
    }
}

