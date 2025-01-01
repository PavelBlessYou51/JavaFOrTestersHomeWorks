package ru.stqa.mantis.manager.developer;

import ru.stqa.mantis.model.DeveloperMailUser;

public record AddUserResponce(Boolean success, Object errors, DeveloperMailUser result) {


}
