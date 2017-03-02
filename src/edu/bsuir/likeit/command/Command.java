package edu.bsuir.likeit.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by Victoria on 01.02.2017.
 */
public interface Command {
    Command getCommand(HttpServletRequest request)
            throws CommandException;
    Optional<String> execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException;

    default boolean needsRedirect() {
        return false;
    }
}
