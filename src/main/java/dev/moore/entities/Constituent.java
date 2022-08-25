package dev.moore.entities;

public class Constituent {
    private int constituentId;
    private String fname = "";
    private String lname = "";
    private String username = "";
    private String password = "";
    private boolean isCouncilMember = false;
    private boolean isRegistered = false;

    public Constituent() {
    }

    public Constituent(String fname, String lname, String username, String password, boolean isCouncilMember, boolean isRegistered) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.isCouncilMember = isCouncilMember;
        this.isRegistered = isRegistered;
    }

    public int getConstituentId() {
        return constituentId;
    }

    public void setConstituentId(int constituentId) {
        this.constituentId = constituentId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCouncilMember() {
        return isCouncilMember;
    }

    public void setCouncilMember(boolean councilMember) {
        isCouncilMember = councilMember;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    @Override
    public String toString() {
        return "Constituent{" +
                "constituentId=" + constituentId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isCouncilMember=" + isCouncilMember +
                ", isRegistered=" + isRegistered +
                '}';
    }
}
