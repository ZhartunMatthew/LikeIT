package edu.bsuir.likeit.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

class EmptyCommand implements Command {
    private static final String URL_INDEX = "/index.jsp";
    private static final String COMMAND="empty";

    public Command getCommand(HttpServletRequest request) throws CommandException {
        return new EmptyCommand();
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        return Optional.of(URL_INDEX);
    }

    @Override
    public boolean needsRedirect() {
        return true;
    }
}
