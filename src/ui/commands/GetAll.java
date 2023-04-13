package ui.commands;

import service.TreeService;

public class GetAll extends Command{
    public GetAll(TreeService treeService) {
        super(treeService);
    }

    @Override
    public String description() {
        return "Вывести всё генеалогическое древо";
    }

    @Override
    public void execute() {
        getTreeService().sorted();
    }
}
