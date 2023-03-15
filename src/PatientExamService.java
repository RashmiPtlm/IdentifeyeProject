import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class PatientExamService {
    List<Patient> ptRecord = new ArrayList<>();
    List<Integer> examRecord = new ArrayList<>();

    public static void main(String[] args) {
        PatientExamService obj = new PatientExamService();

        try {
            // Read notepad file
            String dir = System.getProperty("user.dir");
            File file = new File(dir + "//src//input.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String ipStr;
            while ((ipStr = br.readLine()) != null) {
                String[] inpArr = ipStr.split(" ");

                if (inpArr[0].equalsIgnoreCase("ADD")) {        // If keyword is ADD then call respective function based on second keyword PATIENT/EXAM
                    if (inpArr[1].equalsIgnoreCase("PATIENT")) {
                        obj.addPatient(Integer.parseInt(inpArr[2]), String.join(" ", inpArr[3], inpArr[4]));
                    } else if (inpArr[1].equalsIgnoreCase("EXAM")) {
                        obj.addExam(Integer.parseInt(inpArr[3]), Integer.parseInt(inpArr[2]));
                    }
                } else if (inpArr[0].equalsIgnoreCase("DEL")) {     // If keyword is DEL then call respective function based on second keyword PATIENT/EXAM
                    if (inpArr[1].equalsIgnoreCase("PATIENT")) {
                        obj.deletePatient(Integer.parseInt(inpArr[2]));
                    } else if (inpArr[1].equalsIgnoreCase("EXAM")) {
                        obj.deleteExam(Integer.parseInt(inpArr[2]));
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Exception occurred:");
            System.out.println(e.getMessage());
        }

        obj.printOutput();
    }

    /**
     * Prints all patient details and exam count
     */
    public void printOutput() {
        for (Patient patient : ptRecord) {
            String name = patient.getName();
            int pid = patient.getPid();
            int examCount = patient.getEid().size();

            System.out.println("NAME: " + name + ", Id: " + pid + ", " + "Exam Count: " + examCount);
        }
    }

    /**
     * Add patient into the system if not present
     * @param pid patient id
     * @param name name of the patient
     */
    public void addPatient(int pid, String name) {
        if (!isPatientPresent(Integer.toString(pid))) {
            Patient obj = new Patient(pid, name);

            ptRecord.add(obj);
        }
    }

    /**
     * Checks if patient is already present in the system
     * @param pid patient id
     * @return
     */
    private boolean isPatientPresent(String pid) {
        boolean isFound = false;
        for (Patient patient : ptRecord) {
            String actPid = Integer.toString(patient.getPid());
            if (actPid.equalsIgnoreCase(pid)) {
                isFound = true;
                break;
            }
        }

        return isFound;
    }

    /**
     * Deletes the patient
     * @param pid patient id
     */
    public void deletePatient(int pid) {
        if (isPatientPresent(Integer.toString(pid))) {
            for (int i = 0; i < ptRecord.size(); i++) {
                String actPid = Integer.toString(ptRecord.get(i).getPid());
                if (actPid.equalsIgnoreCase(Integer.toString(pid))) {
                    ptRecord.remove(i);
                }
            }
        }
    }

    /**
     * Adds the exam to exam record and patient record
     * @param eid exam id
     * @param pid patient id
     */
    public void addExam(int eid, int pid) {
        if (isPatientPresent(Integer.toString(pid))) {
            if (!checkIfExamPresent(eid)) {
                examRecord.add(eid);

                for (Patient patient : ptRecord) {
                    String actPid = Integer.toString(patient.getPid());
                    if (actPid.equalsIgnoreCase(Integer.toString(pid))) {
                        patient.getEid().add(eid);
                    }
                }
            }
        }
    }

    /**
     * Checks if exam is present in the system
     * @param eid
     * @return exam id
     */
    private boolean checkIfExamPresent(int eid) {
        boolean isFound = false;
        for (int exam : examRecord) {
            if (exam == eid)
                return true;
        }
        return isFound;
    }

    /**
     * Deletes the exam from exam record and patient record
     * @param eid exam id
     */
    public void deleteExam(int eid) {

        for (int i = 0; i < examRecord.size(); i++) {
            if (examRecord.get(i) == eid)
                examRecord.remove(i);
        }

        for (Patient patient : ptRecord) {
            for (int j = 0; j < patient.getEid().size(); j++) {
                int examId = patient.getEid().get(j);
                if (examId == eid) {
                    patient.getEid().remove(j);
                }
            }
        }
    }
}
