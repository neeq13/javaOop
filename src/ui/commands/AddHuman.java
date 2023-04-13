package ui.commands;

import service.TreeService;

public class AddHuman extends Command{

    public AddHuman(TreeService treeService) {
        super(treeService);
    }

    @Override
    public String description() {
        return "Добавить нового члена семьи";
    }

    @Override
    public void execute() {
        getTreeService().add();
    }
}
