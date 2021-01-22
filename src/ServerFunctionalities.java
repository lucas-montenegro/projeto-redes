import java.util.ArrayList;
import javafx.util.Pair; 

public class ServerFunctionalities {
    private ArrayList <Pair <String, Integer> > taskList;

    public ServerFunctionalities() {
        this.taskList = new ArrayList <Pair <String,Integer> > (); // initializing  taskList
    }

    public void sort() {
        int i, j; // iterators
        int n = this.taskList.size(); // taskList size
        int priority_i, priority_j;
        Pair <String, Integer> swap;

        for(i = 0; i < n; i++) { // sort taskList based in descending order using the task priorities
            priority_i = taskList.get(i).getValue();

            for(j = i + 1; j < n; j++) {
                priority_j = taskList.get(j).getValue();
                if(priority_i < priority_j) {
                    swap = taskList.get(i);
                    taskList.set(i, taskList.get(j));
                    taskList.set(j, swap);
                }
            }
        }
    }

    public String addTask(String taskName, String taskPriority) {
        int taskPriorityToInteger = Integer.parseInt(taskPriority); // parse taskPriority to int
        this.taskList.add(new Pair <String,Integer> (taskName, taskPriorityToInteger)); // adding the new task with priority to the taskList

        sort(); // sort taskList based on higher priorities

        return "Tarefa (Nome: " + taskName + ", Prioridade: " + taskPriority + ") adicionada com sucesso!";
    }

    public String changeTaskPriority(String taskPosition, String newTaskPriority) {
        int taskPositionToInteger = Integer.parseInt(taskPosition); // parse taskPosition to int
        int newTaskPriorityToInteger = Integer.parseInt(newTaskPriority); // parse newTaskPriority to int
        String taskName = this.taskList.get(taskPositionToInteger).getKey(); // gets the name of the task to add it later

        this.taskList.remove(taskPositionToInteger); // remove the task with old priority
        this.taskList.add(new Pair <String,Integer> (taskName, newTaskPriorityToInteger)); // add the removed task but now with the new priority

        sort(); // sort taskList based on higher priorities

        return "Tarefa (Nome: " + taskName + ", Nova Prioridade: " + newTaskPriorityToInteger + ") alterada com sucesso!";
    }
}
