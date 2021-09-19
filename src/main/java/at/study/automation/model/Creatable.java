package at.study.automation.model;

import at.study.automation.model.user.Entity;

public interface Creatable<T extends Entity> {

    T create();

}
