package edu.bsuir.likeit.command;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final Logger LOG = LogManager.getLogger(CommandFactory.class);
    private static final String REQUEST_PARAM_COMMAND = "command";

    private CommandFactory() {
    }

    public static Command defineCommand(HttpServletRequest request)
            throws CommandException {
        String command = request.getParameter(REQUEST_PARAM_COMMAND);
        if (command != null) {
            try {
                LOG.info("Command: " + command);
                return new ShowQuestionsCommand().getCommand(request);
            } catch (IllegalArgumentException e) {
                throw new CommandException("Wrong command name", e);
            }
        } else {
            LOG.info("Null command");
            return new EmptyCommand();
        }
    }
}


