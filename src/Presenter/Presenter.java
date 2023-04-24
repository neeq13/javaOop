package Presenter;

import human.Human;
import service.TreeService;
import ui.Console;

import java.util.List;

public class Presenter {
    private final TreeService treeService = new TreeService();

    public void add() {
        treeService.add(Console.addHuman());
    }

    public Human search() {
        return treeService.search(Console.search());
    }

    public void sorted() {
        Console.print(treeService.sorted(Console.sorted()));
    }

    public void exit() {
        treeService.save();
        Console.exit(false);
    }

    public void addChild() {
        treeService.addChild(Console.addChild());
    }

    public void all() {
        Console.print(treeService.all());
    }
}
