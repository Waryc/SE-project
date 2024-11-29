package todolist.action;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import todolist.task.*;

public class Listener {

    private UserAction userAction = new UserAction();
    private Scanner scanner;

    private String commandPrompt = 
        "请输入命令:\n"+
        "a 添加任务\n"+
        "d 删除任务\n"+
        "u 更新任务\n"+
        "p 查询任务\n"+
        "q 退出\n";

    public void startListening(String inputFilePath) {
        try {
            scanner = new Scanner(new File(inputFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(commandPrompt);
        while (true) {
            if (scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                System.out.println("command: " + command);
                switch (command) {
                    case "a":
                        addTask();
                        break;
                    case "d":
                        deleteTask();
                        break;
                    case "u":
                        updateTask();
                        break;
                    case "p":
                        queryTask();
                        break;
                    case "q":
                        System.out.println("退出程序。");
                        scanner.close();
                        return;
                    default:
                        System.out.println("无效的命令，请重新输入。");
                }
                System.out.println(commandPrompt);
            }
        }
    }

    private void addTask() {
        System.out.println("输入任务ID：");
        int id = scanner.nextInt();
        System.out.println("输入任务名称：");
        scanner.nextLine(); // 消耗换行符
        String name = scanner.nextLine();
        System.out.println("输入任务描述：");
        String description = scanner.nextLine();
        // 假设Attribute, Category, Tag等都是通过某种方式输入或预设的
        Task task = new Task(id, name, description, new Attribute(1,1), new Category("default"), new ArrayList<>());
        userAction.addTask(task);
        System.out.println("任务添加成功。");
    }

    private void deleteTask() {
        System.out.println("输入要删除的任务ID：");
        int id = scanner.nextInt();
        Task task = userAction.queryTask(id);
        if (task != null) {
            userAction.deleteTask(task);
            System.out.println("任务删除成功。");
        } else {
            System.out.println("未找到任务。");
        }
    }

    private void updateTask() {
        System.out.println("输入要更新的任务ID：");
        int id = scanner.nextInt();
        Task task = userAction.queryTask(id);
        if (task != null) {
            System.out.println("输入新的任务名称：");
            scanner.nextLine(); // 消耗换行符
            task.setName(scanner.nextLine());
            System.out.println("任务更新成功。");
        } else {
            System.out.println("未找到任务。");
        }
    }

    private void queryTask() {
        System.out.println("输入要查询的任务ID：");
        int id = scanner.nextInt();
        Task task = userAction.queryTask(id);
        if (task != null) {
            System.out.println("任务ID: " + task.getId());
            System.out.println("任务名称: " + task.getName());
            System.out.println("任务描述: " + task.getDescription());
            // 输出其他属性...
        } else {
            System.out.println("未找到任务。");
        }
    }

    public static void main(String[] args) {
        Listener listener = new Listener();
        listener.startListening("input.txt");
    }
}