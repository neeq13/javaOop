package ui.commands;

import Presenter.Presenter;
import service.TreeService;

public abstract class Command implements Option {
    private final Presenter presenter;

    public Command(Presenter presenter) {
        this.presenter = presenter;
    }

    public Presenter getPresenter() {
        return presenter;
    }
}
