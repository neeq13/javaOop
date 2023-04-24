package ui;

import Presenter.Presenter;
import ui.commands.*;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Option> commands;

    public Menu(Presenter presenter) {
        commands = new ArrayList<>();
        commands.add(new GetAll(presenter));
        commands.add(new Sorted(presenter));
        commands.add(new Search(presenter));
        commands.add(new AddHuman(presenter));
        commands.add(new AddChild(presenter));
        commands.add(new Exit(presenter));
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
