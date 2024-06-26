import java.util.ArrayList;
import java.util.PriorityQueue;

public class Scheduler {
    private final PriorityQueue<Task> waitingTask;
    private final ArrayList<Processor> processors;

    public Scheduler(int numberOfProcessors) {
        waitingTask = new PriorityQueue<>();
        processors = new ArrayList<>();
        for (int i = 0; i < numberOfProcessors; ++i) {
            processors.add(new Processor(i + 1));
        }
    }

    public void setWaitingTask(Task task) {
        waitingTask.add(task);
    }

    public void moveToProcessor(int currentCycle) {
        for (Processor processor : processors) {
            if (processor.isAvailable() && !(waitingTask.isEmpty())) {
                processor.setWorkingTask(waitingTask.poll());
                System.out.println("Moving task " + processor.getWorkingTask().getId() + " to processor "
                        + processor.getId());
                processor.getWorkingTask().setEnterTime(currentCycle);
            }
        }
    }

    public void removeFromProcessor(int currentCycle) {
        for (Processor processor : processors) {
            if (isTaskFinishHisWork(currentCycle, processor)) {
                System.out.println("Task " + processor.getWorkingTask().getId() + " exit");
                processor.setWorkingTask(null);
            }
        }
    }

    private Boolean isTaskFinishHisWork(int currentCycle, Processor processor) {
        return (processor.getWorkingTask() != null) &&
                (processor.getWorkingTask().getExitTime(currentCycle));
    }

    public void printRunningTasks() {
        for (Processor processor : processors) {
            if (!processor.isAvailable()) {
                System.out.println("Task " + processor.getWorkingTask().getId() + " running in processor " +
                        processor.getId());
            }
        }
        System.out.println();

    }
}