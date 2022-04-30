import Models.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskAppTest {
    @Test
    public void addTask_addsANewTask_true(){
        Task task =  new Task("test","3/4/2022","test_task");
        TaskApp taskApp =  new TaskApp();
        taskApp.addTask(new ArrayList<>(),task);
        assertTrue(taskApp.getAllTask().contains(task));
    }

    @Test
    public void getAllTask_returnsAllTasks_3(){

        Task task1 =  new Task("test","3/4/2022","test_task");
        Task task2 =  new Task("test","3/4/2022","test_task");
        Task task3 =  new Task("test","3/4/2022","test_task");

        TaskApp taskApp =  new TaskApp();

        taskApp.addTask(taskApp.getAllTask(),task1);
        taskApp.addTask(taskApp.getAllTask(),task2);
        taskApp.addTask(taskApp.getAllTask(),task3);

        Integer expected = 3;
        assertEquals(expected,taskApp.getAllTask().size());
    }
}
