package ru.stqa.mantis.manager.developer;
import java.util.List;

public record GetIdsResponce(Boolean success, Object errors, List<String> result) {

}
