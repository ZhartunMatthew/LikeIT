package edu.bsuir.likeit.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

class ChangeLanguageCommand implements Command {
    private static final String LANG_SESSION_ATTRIBUTE = "lang";
    private static final String LANG_REQUEST_PARAMETER = "lang";
    private static final String COMMAND = "changelanguage";
    private static final Logger LOG = LogManager.getLogger(ChangeLanguageCommand.class);

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new ChangeLanguageCommand();
        } else {
            return new SignUpCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {

        String requestLang = request.getParameter(LANG_REQUEST_PARAMETER);
        LOG.debug("CHANGE LANG " + requestLang);
        request.getSession(true).setAttribute(LANG_SESSION_ATTRIBUTE, requestLang);
        return Optional.of("/controller?command=showallquestions");
    }

    @Override
    public boolean needsRedirect() {
        return true;
    }
}