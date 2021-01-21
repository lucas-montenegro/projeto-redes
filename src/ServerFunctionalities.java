import java.util.ArrayList;
import javafx.util.Pair; 

public class ServerFunctionalities {
    private ArrayList <Pair <String, Integer> > taskList;

    public ServerFunctionalities() {
        this.taskList = new ArrayList <Pair <String,Integer> > (); // initializing  taskList
    }

    public String addTask(String taskName, String taskPriority) {
        int taskPriorityToInteger = Integer.parseInt(taskPriority); // parse taskPriority to int

        this.taskList.add(new Pair <String,Integer> (taskName, taskPriorityToInteger)); // adding the new task with priority to the taskList

        // TODO // sort taskList based on higher priorities

        return "Tarefa (Nome: " + taskName + ", Prioridade: " + taskPriority + ") adicionada com sucesso!";
    }
}
