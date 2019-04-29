package Res;

public class DataContainer {
    private String id, fName, sName, eMail, pass; //Info about user (Info DB)

    public DataContainer(String id, String fName, String sName, String eMail, String pass) {
        this.id = id;
        this.fName = fName;
        this.sName = sName;
        this.eMail = eMail;
        this.pass  = pass;
    }

    public String getId()    { return id;    }
    public String getFName() { return fName; }
    public String getSName() { return sName; }
    public String getEMail() { return eMail; }
    public String getPass()  { return pass;  }
}
