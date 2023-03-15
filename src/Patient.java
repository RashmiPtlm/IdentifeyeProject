import java.util.*;

public class Patient {
    int pid;
    String name;
    List<Integer> eid;

    Patient() {

    }

    Patient(int pid, String name) {
        this.pid = pid;
        this.name = name;
        eid = new ArrayList<>();
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getEid() {
        return eid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEid(List<Integer> eid) {
        this.eid = eid;
    }
}
