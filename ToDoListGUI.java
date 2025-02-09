import  java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

class Task{
    String desc;
    boolean isCompleted;

    public Task(String desc){
        this.desc = desc;
        this.isCompleted = false;
    }

    public void markCompleted(){
        this.isCompleted = true;
    }

    @Override
    public String toString(){
        return(isCompleted ? "[✓] ":"[ ] ") + desc;
    }
}

public class ToDoListGUI {
    private JFrame frame;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField taskInput;

    public ToDoListGUI(){
        frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList); 

        taskInput = new JTextField(); 
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e->addTask());

        JButton completeButton = new JButton("Mark Completed");
        completeButton.addActionListener(e->completeTask());
        
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e->removeTask());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(taskInput, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(completeButton);
        buttonPanel.add(removeButton);
        
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    private void addTask(){
        String desc = taskInput.getText().trim();
        if (!desc.isEmpty()) {
            taskListModel.addElement(new Task(desc));
            taskInput.setText("");
        }
    }

    private void completeTask(){
        int selectedIndex = taskList.getSelectedIndex(); 
        if (selectedIndex != -1) {
            Task task = taskListModel.getElementAt(selectedIndex);
            task.markCompleted();
            taskList.repaint();
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex(); 
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
} 