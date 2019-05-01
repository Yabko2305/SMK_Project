package Res;

public class PillContainer {        //PillUsageDB
    private String p1, p2, p3;      //Name of pills inside of the SMK
    private int  n1, n2, n3;        //Count of pills |n1|=|p1|...
    String eMail;                   //To identify

    public PillContainer(String p1, String p2, String p3,
                         int n1, int n2, int n3, String eMail) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.eMail = eMail;
    }

    public String getP1() { return p1; }
    public String getP2() { return p2; }
    public String getP3() { return p3; }

    public int getN1() { return n1; }
    public int getN2() { return n2; }
    public int getN3() { return n3; }

    public void setP1(String p1) { this.p1 = p1; }
    public void setP2(String p2) { this.p2 = p2; }
    public void setP3(String p3) { this.p3 = p3; }

    public void setN1(int n1) { this.n1 = n1; }
    public void setN2(int n2) { this.n2 = n2; }
    public void setN3(int n3) { this.n3 = n3; }

    public String getEMail() { return eMail; }
    public void setEMail(String eMail) { this.eMail = eMail; }
}
