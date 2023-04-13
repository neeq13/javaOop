package ui;

import service.TreeService;
import ui.commands.*;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Option> commands;

    public Menu(TreeService treeService) {
        commands = new ArrayList<>();
        commands.add(new GetAll(treeService));
        commands.add(new Search(treeService));
        commands.add(new AddHuman(treeService));
        commands.add(new AddChild(treeService));
        commands.add(new Exit(treeService));
    }

    public void execute(int num){
        Option option = commands.get(num - 1);
        option.execute();
    }

    public String printMenu(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < commands.size(); i++){
            builder.append(i + 1);
            builder.append(": ");
            builder.append(commands.get(i).description());
            builder.append("\n");
        }
        return builder.toString();
    }
}
