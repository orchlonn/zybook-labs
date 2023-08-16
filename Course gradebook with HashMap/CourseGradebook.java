import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CourseGradebook extends Gradebook {

    private HashMap<String, HashMap<Integer, Double>> gradebookData;

    public CourseGradebook() {
        gradebookData = new HashMap<>();
    }

   public HashMap<Integer, Double> getAssignmentScores(String assignment) {
   HashMap<Integer, Double> studentScores = gradebookData.get(assignment);
   if (studentScores == null) {
   return new HashMap<>();
   }
   return new HashMap<>(studentScores);
   }

   public ArrayList<String> getSortedAssignmentNames() {
   TreeSet<String> assignmentNames = new TreeSet<>(gradebookData.keySet());
   return new ArrayList<>(assignmentNames);
   }

   public ArrayList<Integer> getSortedStudentIDs() {
   TreeSet<Integer> studentIDs = new TreeSet<>();
   for (Map<Integer, Double> scores : gradebookData.values()) {
   studentIDs.addAll(scores.keySet());
   }
   return new ArrayList<>(studentIDs);
   }

   public HashMap<String, Double> getStudentScores(int studentID) {
   HashMap<String, Double> studentScores = new HashMap<>();
   for (String assignment : gradebookData.keySet()) {
   Double score = getScore(assignment, studentID);
   if (!score.isNaN()) {
   studentScores.put(assignment, score);
   }
   }
   return studentScores;
   }
  

   public HashMap<String, Double> getStudentScores(Integer studentID) {
   return getStudentScores(studentID.intValue());
   }
	@Override
	public double getScore(String assignmentName, Integer studentID) {
		HashMap<Integer, Double> studentScores = gradebookData.get(assignmentName);
		if (studentScores == null) {
		return Double.NaN;
		}
		return studentScores.getOrDefault(studentID, Double.NaN);
	}
	@Override
	public void setScore(String assignmentName, Integer studentID, Double score) {
        HashMap<Integer, Double> studentScores = gradebookData.get(assignmentName);
        if (studentScores == null) {
            studentScores = new HashMap<>();
            gradebookData.put(assignmentName, studentScores);
        }
        studentScores.put(studentID, score);
	}

}