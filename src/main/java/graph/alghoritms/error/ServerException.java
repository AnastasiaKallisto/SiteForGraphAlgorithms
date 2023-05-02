package graph.alghoritms.error;

public class ServerException extends Exception {
    private ErrorCode error;
    public ServerException(ErrorCode error) {
        super(error.getErrorString());
        this.error = error;
    }

    public ErrorCode getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return error.getErrorString();
    }
}
