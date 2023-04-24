package ui.commands;

import Presenter.Presenter;

public class AddHuman extends Command{

    public AddHuman(Presenter presenter) {
        super(presenter);
    }

    @Override
    public String description() {
        return "Добавить нового члена семьи";
    }

    @Override
    public void execute() {
        getPresenter().add();
    }
}
