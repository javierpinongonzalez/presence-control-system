package utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class CommonExceptions extends Exception {
    private CommonExceptions(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidParameters extends CommonExceptions {
        public InvalidParameters(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class GenericError extends CommonExceptions {
        public GenericError(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFound extends CommonExceptions {
        public NotFound(String message) {
            super(message);
        }
    }
}
