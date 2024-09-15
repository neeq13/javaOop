package ui.commands;

import Presenter.Presenter;

public class Exit extends Command{
    public Exit(Presenter presenter) {
        super(presenter);
    }

    @Override
    public String description() {
        return "Выход";
    }

    @Override
    public void execute() {
        getPresenter().exit();
    }
}
