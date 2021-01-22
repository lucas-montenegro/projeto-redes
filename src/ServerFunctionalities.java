import java.util.ArrayList;
import javafx.util.Pair; 

public class ServerFunctionalities {
    private ArrayList <Pair <String, Integer> > taskList;

    public ServerFunctionalities() {
        this.taskList = new ArrayList <Pair <String,Integer> > (); // initializing  taskList
    }

    public void Sort() {
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

    public String AddTask(String taskName, String taskPriority) {
        int taskPriorityToInteger = Integer.parseInt(taskPriority); // parse taskPriority to int
        this.taskList.add(new Pair <String,Integer> (taskName, taskPriorityToInteger)); // adding the new task with priority to the taskList

        Sort(); // sort taskList based on higher priorities

        return "Tarefa (Nome: " + taskName + ", Prioridade: " + taskPriority + ") adicionada com sucesso!";
    }

    public String ChangeTaskPriority(String taskPosition, String newTaskPriority) {
        int taskPositionToInteger = Integer.parseInt(taskPosition); // parse taskPosition to int
        int newTaskPriorityToInteger = Integer.parseInt(newTaskPriority); // parse newTaskPriority to int
        String taskName = this.taskList.get(taskPositionToInteger).getKey(); // gets the name of the task to add it later

        this.taskList.remove(taskPositionToInteger); // remove the task with old priority
        this.taskList.add(new Pair <String,Integer> (taskName, newTaskPriorityToInteger)); // add the removed task but now with the new priority

        Sort(); // sort taskList based on higher priorities

        return "Tarefa (Nome: " + taskName + ", Nova Prioridade: " + newTaskPriorityToInteger + ") alterada com sucesso!";
    }

    public String RemoveTask(String taskToRemovePosition) {
        int taskToRemovePositionToInteger = Integer.parseInt(taskToRemovePosition); // parse taskToRemovePosition to int
        this.taskList.remove(taskToRemovePositionToInteger); // remove the task
        String taskName = this.taskList.get(taskToRemovePositionToInteger).getKey(); // gets the name of the task to remove
        int taskPriority = this.taskList.get(taskToRemovePositionToInteger).getValue(); // gets the priority of the task to remove

        return "Tarefa (Nome: " + taskName + ", Prioridade: " + taskPriority + ") removida com sucesso!";
    }

    public String ShowTaskList() {
        String list = "";
        int i, n = this.taskList.size();

        for(i = 0; i < n; i++) {
            list += "Posicao: " + i + " -> " +
                    "Tarefa: " + this.taskList.get(i).getKey() +
                    " Prioridade: " + this.taskList.get(i).getValue() +
                    "#";
        }

        return list;
    }

    public String Quit() {
        return "Desconectado com sucesso";
    }
}
