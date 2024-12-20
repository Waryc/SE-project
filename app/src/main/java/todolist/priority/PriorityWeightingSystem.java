package todolist.priority;

import todolist.task.Attribute;
import todolist.task.Task;

// 优先级加权系统类
public class PriorityWeightingSystem {

    // 计算任务优先级的方法
    public int calculatePriority(Task task) {
        if (task == null || task.getAttribute() == null) {
            throw new IllegalArgumentException("任务或其属性不能为空");
        }
        Attribute attribute = task.getAttribute();
        Attribute.Importance importance = attribute.getImportance(); // 假设重要性范围是1-10
        int difficulty = attribute.getDifficulty(); // 假设难度范围是1-10

        // 简单的优先级计算公式，例如：优先级 = 重要性 * (10 - 难度)
        // 这个公式只是一个示例，你可以根据实际需求调整公式
        int priority = importance.ordinal() * (10 - difficulty);
        return priority;
    }
}
