import Models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskApp {
   private List<Task> taskList = new ArrayList<>();

   public void addTask(List<Task> list,Task task){
      if(list!=null) {
         taskList = list;
      }else {
         list = new ArrayList<>();
      }
      task.setId(list.size());
      taskList.add(task);
   }

   public void removeTask(List<Task> list,int id){
      if(list!=null) {


      }else {
         list.remove(id);
      }
      taskList = list;
   }

   public List<Task> getAllTask(){
      return taskList;
   }

   public void markAsComplete(List<Task> list,int id){
      if(list!=null){
        list.get(id).setComplete(true);
        taskList = list;
      }
   }



}
